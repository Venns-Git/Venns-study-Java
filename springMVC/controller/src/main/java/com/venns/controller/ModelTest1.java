package com.venns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ModelTest1 {

    //转发
    @RequestMapping("/m1/t1")
    public String test1(Model model){
        model.addAttribute("msg","ModelTest1");
        return "test";
    }

    //重定向
    @RequestMapping("/m1/t2")
    public String test2(Model model){
        model.addAttribute("msg","ModelTset2");
        return "redirect:/index.jsp";
    }
}
