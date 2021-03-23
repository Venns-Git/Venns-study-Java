package com.venns.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Venns
 * @CreateDate 2021/2/4
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker // 添加对熔断的支持
public class DeptProvider_Hystrix_8001 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider_Hystrix_8001.class,args);
    }
}
