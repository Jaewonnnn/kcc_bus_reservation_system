package com.unibus.user.controller;

import com.unibus.user.domain.Member;
import com.unibus.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<Member> getUserList() {
        return userService.getUserList();
    }

    @GetMapping("/test")
    public String test() {
        return "hello";
    }

}
