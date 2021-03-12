package com.venns;

import redis.clients.jedis.Jedis;

/**
 * @author: Venns
 * @create: 2021/3/12 21:08
 **/
public class TestPing {
    public static void main(String[] args) {

        // 1.创建Jedis对象,连接服务器,需要先打开Redis服务,开放端口等等
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        // 2.测试连接
        System.out.println(jedis.ping());

        // 3. 关闭连接
        jedis.close();
    }
}
