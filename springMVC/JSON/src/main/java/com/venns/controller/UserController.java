package com.venns.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.venns.pojo.User;
import com.venns.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
// @RestController 不走视图解析器
public class UserController {


    @RequestMapping("/j1")
    @ResponseBody//不会走视图解析器，会直接返回一个字符串
    public String json1() throws JsonProcessingException {
        //jackson ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        //创建一个对象
        User user = new User("venns",3,"男");

        String str = mapper.writeValueAsString(user);

        return str;
    }

    //返回日期
    @RequestMapping("/j2")
    @ResponseBody
    public String json2() throws JsonProcessingException {

        User user1 = new User("venns1", 3, "男");
        User user2 = new User("venns2", 3, "男");
        User user3 = new User("venns3", 3, "男");
        User user4 = new User("venns4", 3, "男");
        List<User> userList = new ArrayList<User>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        return JsonUtils.getJson(userList);
    }

    //返回日期
    @RequestMapping("/j3")
    @ResponseBody
    public String json3() throws JsonProcessingException {
        Date date = new Date();
        return JsonUtils.getJson(date);
    }

    @RequestMapping("/j4")
    @ResponseBody
    public String fastjson(){
        User user1 = new User("venns1", 3, "男");
        User user2 = new User("venns2", 3, "男");
        User user3 = new User("venns3", 3, "男");
        User user4 = new User("venns4", 3, "男");
        List<User> userList = new ArrayList<User>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        String string = JSON.toJSONString(userList);
        return string;
    }

}
