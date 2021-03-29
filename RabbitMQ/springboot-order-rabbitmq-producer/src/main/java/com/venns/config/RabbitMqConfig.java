package com.venns.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Venns
 * @create: 2021/3/28 0:48
 **/
@Configuration
public class RabbitMqConfig {

    // 1. 声明注册fanout模式的交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout_order_exchange",true,false);// 交换机名称 是否持久化 是否自动删除
    }
    // 2. 声明队列 sms.fanout.queue email.fanout.queue message.fanout.queue
    @Bean
    public Queue smsQueue(){
        return new Queue("sms.fanout.queue",true); // 队列名称 是否持久化
    }
    @Bean
    public Queue emailQueue(){
        return new Queue("email.fanout.queue",true); // 队列名称 是否持久化
    }
    @Bean
    public Queue messageQueue(){
        return new Queue("message.fanout.queue",true); // 队列名称 是否持久化
    }
    // 3. 完成绑定关系(队列和交换机)
    @Bean
    public Binding smsBinding(){
        return BindingBuilder.bind(smsQueue()).to(fanoutExchange());
    }
    @Bean
    public Binding emailBinding(){
        return BindingBuilder.bind(emailQueue()).to(fanoutExchange());
    }
    @Bean
    public Binding messageBinding(){
        return BindingBuilder.bind(messageQueue()).to(fanoutExchange());
    }

}
