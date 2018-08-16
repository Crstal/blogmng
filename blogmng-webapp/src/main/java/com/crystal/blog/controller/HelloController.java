package com.crystal.blog.controller;

import com.crystal.blog.dao.model.User;
import com.crystal.blog.service.user.UserSefrvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @Autowired
    private UserSefrvice userSefrvice;

    @GetMapping("/hello")
    public String hello() {
        User user = userSefrvice.selectUser(1);
        return "front/index";
    }
}
