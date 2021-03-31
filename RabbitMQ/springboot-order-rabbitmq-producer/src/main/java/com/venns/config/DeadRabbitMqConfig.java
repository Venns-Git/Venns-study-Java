package com.venns.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Venns
 * @create: 2021/3/31 0:49
 **/
@Configuration
public class DeadRabbitMqConfig {

    // 1. 注册死信交换机 为direct模式
    @Bean
    public DirectExchange deadDirectExchange(){
        return new DirectExchange("dead_direct_exchange",true,true);
    }

    // 2. 设置队列
    @Bean
    public Queue deadQueue(){
        return new Queue("dead.direct.queue",true);
    }

    // 3. 交换机绑定队列
    public Binding deadBinding(){
        return BindingBuilder.bind(deadQueue()).to(deadDirectExchange()).with("dead");
    }
}
