package com.venns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestfulController {

    //原来的：  http://localhost:8080/add?a=1&b=2
    //Restful：http://localhost:8080/add/a/b
    @RequestMapping("/add/{a}/{b}")
    public String test(@PathVariable int a,@PathVariable int b, Model model){
        int res = a + b;
        model.addAttribute("msg","结果为"+res);
        return "test";
    }
}
