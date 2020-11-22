package com.venns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String hello(Model model){
        //封装数据
        model.addAttribute("msg","hello springMVCAnnotation");


        return "hello"; //会被视图解析器处理 /WEB-INF/jsp/hello.jsp
    }
}
