package com.venns.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author: Venns
 * @create: 2021/3/15 5:22
 **/
@SpringBootApplication
@EnableEurekaServer //服务端启动类，可以接收别人注册进来
public class EurekaServer_7003 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer_7003.class,args);
    }
}
