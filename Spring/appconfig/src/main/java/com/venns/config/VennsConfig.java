package com.venns.config;

import com.venns.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//这个注解也会被Spring托管，注册到容器中，本身就是一个@Component
//@Configuration代表这就是一个配置类，和bean.xml一样
@Configuration
@ComponentScan("com.venns.pojo")
public class VennsConfig {

    //注册一个bean，相当于我们之前写的一个bean标签
    //bean的id为方法的名字
    //bean的class为方法的返回值
    @Bean
    public User User(){
        return new User();
    }
}
