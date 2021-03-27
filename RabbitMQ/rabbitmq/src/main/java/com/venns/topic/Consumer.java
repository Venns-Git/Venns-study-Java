package com.venns.topic;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author: Venns
 * @create: 2021/3/27 13:50
 **/
public class Consumer {
    public static void main(String[] args) {
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
            // 5. 接收消息
            channel.basicConsume("queue", true, new DeliverCallback() {
                @Override
                public void handle(String s, Delivery delivery) throws IOException {
                    System.out.println("收到的消息是-> " + new String(delivery.getBody(), "UTF-8"));
                }
            }, new CancelCallback() {
                @Override
                public void handle(String s) throws IOException {
                    System.out.println("消息接收失败");
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            // 6. 关闭连接
            if (channel != null && channel.isOpen()){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            // 7. 关闭通道
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
