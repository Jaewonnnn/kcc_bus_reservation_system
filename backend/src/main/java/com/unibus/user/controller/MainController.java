package com.unibus.user.controller;

import com.unibus.user.domain.Member;
import com.unibus.user.domain.NonMember;
import com.unibus.user.mapper.UserMapper;
import com.unibus.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final HttpSecurity httpSecurity;

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) Boolean error, Model model){
        if(Boolean.TRUE.equals(error)) {
            log.info("login error = {}", error);
            model.addAttribute("error", error);
            model.addAttribute("exception", "회원 정보를 찾을 수 없습니다.");
        }
        return "login";
    }

    @PostMapping("/loginProcess")
    public String login(@RequestBody Member member) {
        String role = userService.getMemberRole(member.getMemberId());

        if(role.equals("ROLE_ADMIN")) return "redirect:/admin";
        else if(role.equals("ROLE_USER")) return "redirect:/main";
        else return "redirect:/login?error";
    }

    @GetMapping("/join")
    public String join() {
        return "membership";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute Member member) throws Exception{
        int result = userService.join(member);

        if (result == 1) {
            return "redirect:/login";
        } else {
            throw new Exception("join failed");
        }
    }

    @GetMapping("/duplicated/{id}")
    public ResponseEntity<String> duplicated(@PathVariable String id) {

        if(userService.isMemberIdDuplicate(id)){
            return new ResponseEntity<>("사용 가능한 아이디입니다.", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("중복된 아이디입니다.", HttpStatus.CONFLICT);
        }
    }
}
