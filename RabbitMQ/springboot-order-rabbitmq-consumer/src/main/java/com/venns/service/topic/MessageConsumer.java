package com.venns.service.topic;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @author: Venns
 * @create: 2021/3/28 2:18
 **/
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "message.topic.queue",declare = "true",autoDelete = "false"),
        exchange = @Exchange(value = "topic_order_exchange",type = ExchangeTypes.TOPIC),
        key = "*.message.#"
))
@Service
public class MessageConsumer {

    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("message topic -- 接收到了订单信息是：" + message);
    }
}
