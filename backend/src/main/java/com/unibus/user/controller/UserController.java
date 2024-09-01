package com.unibus.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/userLogin")
    public String userLogin() {
        return "login";
    }

    @GetMapping("/userJoin")
    public String userJoin() {
        return "membership";
    }
}
