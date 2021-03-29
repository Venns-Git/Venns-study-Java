package com.venns.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author: Venns
 * @create: 2021/3/28 0:11
 **/
@Service
public class OrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @Description 模拟用户下单
     * @param userId 用户id
     * @param produceId 产品id
     * @param num 数量
     */
    public void makeOrder(String userId,String produceId,int num){

        // 1. 根据商品id查询库存是否充足
        // 2. 保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生成成功:" + orderId);
        // 3. 通过MQ来完成消息的分发
        /**
         * @param1 交换机
         * @param2 路由key / 队列名称
         * @param3 消息内容
         */
        String exchangeName = "fanout_order_exchange";
        String routingKey = "";
        rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId);
    }

    public void makeOrderDirect(String userId,String produceId,int num){

        // 1. 根据商品id查询库存是否充足
        // 2. 保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生成成功:" + orderId);
        // 3. 通过MQ来完成消息的分发
        /**
         * @param1 交换机
         * @param2 路由key / 队列名称
         * @param3 消息内容
         */
        String exchangeName = "direct_order_exchange";
        rabbitTemplate.convertAndSend(exchangeName,"message",orderId);
        rabbitTemplate.convertAndSend(exchangeName,"email",orderId);
    }

    public void makeOrderTopic(String userId,String produceId,int num){

        // 1. 根据商品id查询库存是否充足
        // 2. 保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生成成功:" + orderId);
        // 3. 通过MQ来完成消息的分发
        /**
         * @param1 交换机
         * @param2 路由key / 队列名称
         * @param3 消息内容
         */
        String exchangeName = "topic_order_exchange";
        rabbitTemplate.convertAndSend(exchangeName,"message",orderId);
        rabbitTemplate.convertAndSend(exchangeName,"email",orderId);
    }
}
