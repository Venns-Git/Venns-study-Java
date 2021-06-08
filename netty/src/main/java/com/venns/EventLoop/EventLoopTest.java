package com.venns.EventLoop;

import io.netty.channel.nio.NioEventLoopGroup;

import java.util.concurrent.TimeUnit;

/**
 * @author: Venns
 * @create: 2021/6/8 21:29
 **/
public class EventLoopTest {
    public static void main(String[] args) {
        // 1. 创建事件循环组
        NioEventLoopGroup group = new NioEventLoopGroup(); //io事件, 普通任务, 定时任务 参数为指定线程数量
        // DefaultEventLoopGroup group1 = new DefaultEventLoopGroup(); // 普通任务 定时任务

        // 2. 读取下一个事件循环对象
        System.out.println(group.next());

        // 3. 执行普通任务
        /* group.next().execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });*/

        // 4. 执行定时任务
        group.next().scheduleAtFixedRate(() -> {
            System.out.println("定时任务执行");
        }, 0, 1, TimeUnit.SECONDS); // 参数分别为 任务 初始间隔 执行一次任务的间隔 时间单位
    }
}
