package com.venns.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author: Venns
 * @create: 2021/3/25 4:38
 **/
public class Producer {
    public static void main(String[] args) {
        // 所有的中间件技术都是基于tcp/ip 协议之上构建的新型协议规范，rabbitmq 遵循的是amqp
        // ip port

        // 1. 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost"); // 主机号
        connectionFactory.setPort(5672); // 端口号
        connectionFactory.setUsername("admin"); // 账号名
        connectionFactory.setPassword("admin"); // 密码
        connectionFactory.setVirtualHost("/"); // 访问节点
        // 2. 创建连接
        Connection connection = null;
        Channel channel = null;
        try {
            connection = connectionFactory.newConnection("生产者");
            // 3. 通过连接获取通道
            channel = connection.createChannel();
            // 4. 通过通道创建交换机 声明队列 绑定关系 路由key 发送消息 接收消息
            String queueName = "queue1";
            /**
             * @Params1 队列名
             * @Params2 是否持久化
             * @Params3 排他性，是否是一个独占队列
             * @Params4 是否自动删除，最后一个消费者消费完毕消息 是否把队列自动删除
             * @Params5 携带附属参数
             */
            channel.queueDeclare(queueName,false,false,false,null);
            // 5. 准备消息内容
            String message = "Hello World!";
            // 6. 发送消息给队列queue
            /**
             * @Params1 交换机 虽然没有指定交换机，但是一定会存在一个默认的交换机
             * @Params2 队列，路由key
             * @Params3 消息的状态控制
             * @Params4 消息主体
             */
            channel.basicPublish("",queueName,null,message.getBytes(StandardCharsets.UTF_8));
            System.out.println("消息发送成功!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            // 7. 关闭连接
            if (channel != null && channel.isOpen()){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            // 8. 关闭通道
            if (connection != null && connection.isOpen()){
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
