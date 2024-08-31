package com.unibus.admin.controller;

import com.unibus.admin.dto.UserDto;
import com.unibus.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public List<UserDto> getUsers() {
        log.info("AdminController getUsers() called " + new Date());
        return adminService.getUserList();
    }

}
