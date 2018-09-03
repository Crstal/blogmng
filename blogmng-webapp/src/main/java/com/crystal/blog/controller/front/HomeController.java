package com.crystal.blog.controller.front;

import com.alibaba.druid.mock.MockArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class HomeController {

    @GetMapping("")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("front/index");

        return modelAndView;
    }
}
