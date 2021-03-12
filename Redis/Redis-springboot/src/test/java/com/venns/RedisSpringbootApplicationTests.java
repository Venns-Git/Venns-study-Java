package com.venns;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisSpringbootApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {

        // RedisTemplate
        /*
            opsForValue 操作字符串，类似String
            opsForList 操作list 类似List
            opsForSet
            opsFor...
         */
        // 除了基本的操作，我们常用的方法都可以直接通过redisTemplate操作，比如事务和基本的CRUD


        // 获取Redis连接对象
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        connection.flushDb();
        connection.flushDb();

        redisTemplate.opsForValue().set("name","venns");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }

}
