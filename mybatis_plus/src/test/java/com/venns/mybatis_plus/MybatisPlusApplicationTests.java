package com.venns.mybatis_plus;

import com.venns.mybatis_plus.mapper.UserMapper;
import com.venns.mybatis_plus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);

    }

    @Test
    void insertTest(){
        User user = new User();
        user.setName("venns");
        user.setAge(18);
        user.setEmail("123456@qq.com");
        int insert = userMapper.insert(user);
        System.out.println(insert); // 受影响行数
        System.out.println(user); // 发现自动生成了一个id
    }

    @Test
    void updateTest(){
        User user = new User();
        //通过条件自动拼接动态sql
        user.setId(5);
        user.setName("venns");
        int i = userMapper.updateById(user);
        System.out.println(i);
    }
}
