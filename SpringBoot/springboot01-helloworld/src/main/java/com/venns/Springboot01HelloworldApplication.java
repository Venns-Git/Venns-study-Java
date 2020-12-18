package com.venns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot01HelloworldApplication {

    //该方法返回一个ConfigurableApplicationContext对象
    //参数一：应用入口类     参数类：命令行参数
    public static void main(String[] args) {
        SpringApplication.run(Springboot01HelloworldApplication.class, args);
    }

}
