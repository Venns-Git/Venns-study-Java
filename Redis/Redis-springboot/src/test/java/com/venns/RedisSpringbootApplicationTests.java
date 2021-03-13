package com.venns;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.venns.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest
class RedisSpringbootApplicationTests {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws JsonProcessingException {

        //真实开发一般都使用json来传递对象
        User user = new User("venns", 18);
        // String jsonUser = new ObjectMapper().writeValueAsString(user);
        // 如果需要保存对象，则实体类需要实现序列化接口
        redisTemplate.opsForValue().set("user",user);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }
}
