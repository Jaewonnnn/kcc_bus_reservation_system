package com.unibus.admin.controller;

import com.unibus.admin.dto.AdminTerminalDto;
import com.unibus.admin.dto.UpdateTerminalDto;
import com.unibus.admin.dto.UserDto;
import com.unibus.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/")
@Slf4j
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/")
    public String getAdminPage() {
        return "admin_user";
    }

    @GetMapping("/users")
    @ResponseBody
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<List<UserDto>>(adminService.getUserList(), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    @ResponseBody
    public ResponseEntity<UserDto> getOneUser(@PathVariable int userId) {
        return new ResponseEntity<UserDto>(adminService.getUserById(userId), HttpStatus.OK);
    }

    @PatchMapping("/user/{userId}")
    public String updateUser(@PathVariable int userId, @RequestBody UserDto userDto) throws Exception{
        int result = adminService.updateUser(userId, userDto);
        if(result == 1){
            return "redirect:/admin/";
        }else {
            throw new Exception("update fail");
        }
    }

    @PatchMapping("/user/delete/{userId}")
    public String deleteUser(@PathVariable int userId) throws Exception{
        int result = adminService.deleteUser(userId);
        if(result == 1) {
            return "redirect:/admin/";
        }else{
            throw new Exception("delete fail");
        }
    }

    @GetMapping("/terminal")
    public String getTerminalPage(Model model){
        model.addAttribute("terminalList", adminService.getTerminalList());
        return "admin_terminal";
    }

    @GetMapping("/terminal/{terminal_id}")
    @ResponseBody
    public AdminTerminalDto getTerminal(@PathVariable String terminal_id){
        return adminService.getTerminalById(terminal_id);
    }

    @PostMapping("/terminal")
    public String createTerminal(@RequestBody AdminTerminalDto terminalDto){
        adminService.createTerminal(terminalDto);
        return "redirect:/admin/terminal";
    }

    @PatchMapping("/terminal/{terminal_id}")
    public String updateTerminal(@PathVariable String terminal_id, @RequestBody UpdateTerminalDto updateTerminalDto){
        log.info("updateTerminalDto = {}", updateTerminalDto + " " + new Date());
        adminService.updateTerminal(terminal_id, updateTerminalDto);
        return "redirect:/admin/terminal";
    }

    @PatchMapping("/terminal/delete/{terminal_id}")
    public String deleteTerminal(@PathVariable String terminal_id) throws Exception{
        int result = adminService.deleteTerminal(terminal_id);
        if(result == 1)
            return "redirect:/admin/terminal";
        else
            throw new Exception("delete terminal failed");
    }
}
