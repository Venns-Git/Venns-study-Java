package com.venns.controller;

import com.venns.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/t1")
    public String test1(@RequestParam("username") String name, Model model){
        //1.接受前端参数
        System.out.println("接受到的参数为:"+name);

        //2.返回结果给前端
        model.addAttribute("msg",name);

        //3.跳转视图
        return "test";
    }

    /*
        1.接收请求，判断参数的名字，假设名字直接在方法上，可以直接使用
        2.假设传递的是一个对象User，匹配User对象中的字段名，如果名字一致则OK，分则匹配不到
     */
    @GetMapping("/t2")
    public String test2(User user){
        System.out.println(user);
        return "test";
    }

    @GetMapping("/t3")
    public String test3(ModelMap modelMap){
        
        return "test";
    }
}
