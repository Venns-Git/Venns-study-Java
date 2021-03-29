package com.venns.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Venns
 * @create: 2021/3/28 2:37
 **/
@Configuration
public class DirectRabbitMqConfig {
    // 1. 声明注册direct模式的交换机
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("direct_order_exchange",true,false);// 交换机名称 是否持久化 是否自动删除
    }
    // 2. 声明队列 sms.fanout.queue email.fanout.queue message.fanout.queue
    @Bean
    public Queue smsQueue(){
        return new Queue("sms.direct.queue",true); // 队列名称 是否持久化
    }
    @Bean
    public Queue emailQueue(){
        return new Queue("email.direct.queue",true); // 队列名称 是否持久化
    }
    @Bean
    public Queue messageQueue(){
        return new Queue("message.direct.queue",true); // 队列名称 是否持久化
    }
    // 3. 完成绑定关系(队列和交换机)
    @Bean
    public Binding smsBinding(){
        return BindingBuilder.bind(smsQueue()).to(directExchange()).with("sms");
    }
    @Bean
    public Binding emailBinding(){
        return BindingBuilder.bind(emailQueue()).to(directExchange()).with("email");
    }
    @Bean
    public Binding messageBinding(){
        return BindingBuilder.bind(messageQueue()).to(directExchange()).with("message");
    }
}
