package com.venns;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @author: Venns
 * @create: 2021/3/12 21:09
 **/
public class TestTX {
    public static void main(String[] args) {

        // 1.连接Redis
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        // 2.创建命令
        jedis.flushDB();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello", "world");
        jsonObject.put("name", "venns");

        // 3.开启事务
        Transaction multi = jedis.multi();

        // 4.执行命令
        String result = jsonObject.toJSONString();
        //jedis.watch(result); //添加监控
        try {
            multi.set("user1", result);
            multi.set("user2", result);
            // int i = 1 / 0; //代码抛出异常，执行失败
            //如果成功则执行事务
            multi.exec();
        } catch (Exception e) {
            // 如果失败，则放弃事务
            multi.discard();
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            // 5.关闭连接
            jedis.close();
        }
    }
}
