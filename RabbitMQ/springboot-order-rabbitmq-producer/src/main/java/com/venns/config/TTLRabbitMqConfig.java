package com.venns.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Venns
 * @create: 2021/3/30 0:00
 **/
@Configuration
public class TTLRabbitMqConfig {

    // 1. 声明注册direct模式的交换机
    @Bean
    public DirectExchange ttldirectExchange(){
        return new DirectExchange("ttl_direct_order_exchange",true,false);
    }

    // 2. 设置队列过期时间
    @Bean
    public Queue directTTLqueue(){
        Map<String,Object> args = new HashMap<>();
        args.put("x-message-ttl",5000); //一定是int类型
        return new Queue("ttl.direct.queue",true);
    }

    // 3. 将队列绑定到交换机
    @Bean
    public Binding directsmsBingding(){
        return BindingBuilder.bind(directTTLqueue()).to(ttldirectExchange()).with("ttl");
    }
}
