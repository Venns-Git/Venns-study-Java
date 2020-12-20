package com.venns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

//在templates目录下的所有页面，只能通过controller来跳转
//这个需要模板引擎的支持
@Controller
public class IndexController {

    @RequestMapping("/test")
    public String index(Model model){
        model.addAttribute("msg","<h1>hello,springboot</h1>");
        model.addAttribute("users", Arrays.asList("venns1","venns2"));
        return "test";
    }
}
