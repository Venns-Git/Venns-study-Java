package com.venns.service.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author: Venns
 * @create: 2021/3/28 2:18
 **/
@RabbitListener(queues = "message.direct.queue")
@Service
public class MessageConsumer {

    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("message direct -- 接收到了订单信息是：" + message);
    }
}
