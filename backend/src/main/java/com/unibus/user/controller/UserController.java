package com.unibus.user.controller;

import com.unibus.config.PrincipalDetail;
import com.unibus.reservation.dto.ReservationSummaryDTO;
import com.unibus.reservation.service.ReservationService;
import com.unibus.user.domain.Member;
import com.unibus.user.dto.ValidPassword;
import com.unibus.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/user/")
@Slf4j
public class UserController {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ReservationService reservationService;

    @GetMapping("/mypage")
    public String mypage(Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        Member member = userService.userInfo(principalDetail.getUsername());
        List<ReservationSummaryDTO> dto = reservationService.findReservationsByMember(principalDetail.getUsername());
        model.addAttribute("reservation", dto);
        model.addAttribute("member", member);
        return "mypage";
    }

    @GetMapping("/mypage/edit")
    public String edit(Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        Member member = userService.userInfo(principalDetail.getUsername());
        model.addAttribute("member", member);
        return "mypage_edit";
    }

    @GetMapping("/{user_id}")
    @ResponseBody
    public ResponseEntity<Member> getUserInfo(@PathVariable("user_id") String memberId) {
        Member member = userService.userInfo(memberId);
        return member == null ? new ResponseEntity<>(member, HttpStatus.INTERNAL_SERVER_ERROR) :
                new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PatchMapping("/{user_id}")
    @ResponseBody
    public ResponseEntity<Integer> changeUserInfo(@RequestBody Member member,
                                                  @PathVariable("user_id") String memberId) {
        Member member2 = userService.userInfo(memberId);
        member2.setMemberEmail(member.getMemberEmail());
        member2.setMemberName(member.getMemberName());
        member2.setMemberTel(member.getMemberTel());
        int flag = userService.updateMember(member2);
        return flag > 0 ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawUser(@RequestBody Member member) {
        boolean flag = userService.updateWithdraw(member.getMemberPass(), member.getMemberId());
        if (flag) {
            return new ResponseEntity<>("success",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("fail",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/memberPass")
    public ResponseEntity<String> getMemberPass(@RequestBody ValidPassword password) {
        boolean flag = userService.getMemberPass(password.getPass(), password.getId());
        return flag == false ? new ResponseEntity<>("fail", HttpStatus.NOT_FOUND)
                : new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PatchMapping("/memberPass")
    public ResponseEntity<Boolean> changeMemberPass(@RequestBody Member member) {
        member.setMemberPass(bCryptPasswordEncoder.encode(member.getMemberPass()));
        boolean flag = userService.updatePassword(member);
        return flag == false ? new ResponseEntity<>(false, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(true, HttpStatus.OK);
    }



}
