package com.venns.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {


    //配置了Swagger的Docket的bean实例
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //RequestHandlerSelectors 配置要扫描接口的方式
                /*
                    basePackage 扫描指定包
                    any 扫描全部
                    none 都不扫描
                    withClassAnnotation 扫描类上的注解
                    withMethodAnnotation 扫描方法上的注解
                 */
                .apis(RequestHandlerSelectors.basePackage("com.venns.controller"))
                //paths 指定路径
                //.paths(PathSelectors.ant("/venns/**"))
                .build();
    }

    //配置Swagger信息 == apiInfo
    private ApiInfo apiInfo(){
        Contact contact = new Contact("venns","http://venns.cn/","2396177829@qq.com");
        return new ApiInfo("Venns\'Swagger",
                "Hello Swagger",
                "v1.0",
                "http://venns.cn/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
