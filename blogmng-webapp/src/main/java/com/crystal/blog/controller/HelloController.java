package com.crystal.blog.controller;

import com.crystal.blog.common.exception.BussinessRuntimeException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {


    @GetMapping("/hello")
    public String hello() {
        return "front/index";
    }

    @GetMapping("/simple")
    public String simple() {
        return "write_article";
    }

    @GetMapping("/article")
    public String article() {
        return "front/article";
    }


    @GetMapping("/message")
    public String message() {
        return "front/message";
    }

    @GetMapping("/history")
    public String history() {
        return "front/history";
    }


    @GetMapping("/404")
    public String noPage() {
        return "front/404";
    }

    @GetMapping("/500")
    public String error() {
        throw new BussinessRuntimeException("", "");
    }
}
