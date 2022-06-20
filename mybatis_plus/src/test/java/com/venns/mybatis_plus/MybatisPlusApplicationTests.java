package com.venns.mybatis_plus;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.venns.mybatis_plus.mapper.UserMapper;
import com.venns.mybatis_plus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

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

    @Test
    void optimisticLockerTest(){
        // 1.查询用户信息
        User user1 = userMapper.selectById(1);

        // 2.修改用户信息
        user1.setName("Venns1");

        // 4.模拟另外一个线程进行操作
        User user2 = userMapper.selectById(1);
        user2.setName("venns2");
        userMapper.updateById(user2);

        // 3.执行更新操作,由于存在乐观锁,会操作失败
        userMapper.updateById(user1);
    }

    @Test
    void  paginationTest(){
        /**
         * @cureent 当前页
         * @size 页面大小
         */
        Page<User> page = new Page<>(1,5);
        userMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println);
    }

}
