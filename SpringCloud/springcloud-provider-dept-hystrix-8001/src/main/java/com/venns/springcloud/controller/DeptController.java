package com.venns.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.venns.springcloud.pojo.Dept;
import com.venns.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Venns
 * @CreateDate 2021/2/4
 * @comments:提供Restful服务
 */
@ResponseBody
@Controller
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/dept/get/{id}")
    @HystrixCommand(fallbackMethod = "hystrixQueryById")
    public Dept queryById(@PathVariable("id") long id){
        Dept dept = deptService.queryById(id);

        if (dept == null) throw new RuntimeException("id => " + id + ",不存在该用户");
        return dept;
    }

    // 备选方法
    public Dept hystrixQueryById(@PathVariable("id") long id){
        return new Dept()
                .setDeptno(id)
                .setDname("id => " + id + ",不存在该用户 -- @Hystrix")
                .setDb_source("no this database in mysql");
    }
}
