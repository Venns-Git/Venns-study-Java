package com.venns.springcloud.controller;

import com.venns.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Venns
 * @CreateDate 2021/2/6
 */
@RestController
public class DeptConsumerController {
    /*
     * 消费者调用服务者的server层
     * RestTemplate:提供了多种便捷访问远程http服务的方法，简单restful服务模板
     * RestTemplate 注册到spring中
     * (url,实体：map，Class<T> responseType)
     */

    @Autowired
    private RestTemplate restTemplate;

    private static final String REST_URL_PREFIX = "http://localhost:8081";

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") long id){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/get"+id,Dept.class);
    }

    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX+"/dept/add",dept,Boolean.class);
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list(){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/list",List.class);
    }
}
