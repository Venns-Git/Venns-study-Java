package com.venns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerTest2 {
    @RequestMapping("/t2") //url地址
    public String test1(Model model){
        model.addAttribute("msg","ControllerTest2");
        return "test";//视图
    }
}
