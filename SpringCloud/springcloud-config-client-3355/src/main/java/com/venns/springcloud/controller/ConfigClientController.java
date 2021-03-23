package com.venns.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Venns
 * @create: 2021/3/24 6:44
 **/
@RestController
public class ConfigClientController {

    // 读取远程git上的配置文件中的值

    @Value("$(spring.application.name)")
    private String applicationName;

    @Value("$(eureka.client.service-url)")
    private String eurekaServer;

    @Value("$(server.port)")
    private String port;


    @RequestMapping("/config")
    public String getConfig(){
        return "spring.application.name: " + applicationName +
                "eureka.client.service-url: " + eurekaServer +
                "server.port: " + port;

    }
}
