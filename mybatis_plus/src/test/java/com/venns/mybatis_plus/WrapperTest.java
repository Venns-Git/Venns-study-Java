package com.venns.mybatis_plus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.venns.mybatis_plus.mapper.UserMapper;
import com.venns.mybatis_plus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: Venns
 * @create: 2022/6/20 16:12
 **/
@SpringBootTest
public class WrapperTest {

    @Autowired
    private UserMapper userMapper;


    /**
     * 查询名字非空，年龄大于12的数据
     */
    @Test
    void wrapperQueryTest(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("name").ge("age",12);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

}
