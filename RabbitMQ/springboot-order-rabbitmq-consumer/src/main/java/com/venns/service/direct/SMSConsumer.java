package com.venns.service.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author: Venns
 * @create: 2021/3/28 2:17
 **/
@RabbitListener(queues = "sms.direct.queue")
@Service
public class SMSConsumer {
    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("sms direct -- 接收到了订单信息是：" + message);
    }
}
