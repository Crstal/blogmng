package com.crystal.blog.controller;

import com.alibaba.fastjson.JSON;
import com.crystal.blog.service.user.UserSefrvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private UserSefrvice userSefrvice;

    @GetMapping("/hello")
    public String hello() {
        User user = userSefrvice.selectUser(1);
        return JSON.toJSONString(user);
    }
}
