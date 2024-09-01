package com.unibus.user.controller;

import org.springframework.stereotype.Controller;

@Controller

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
