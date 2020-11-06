package com.venns.dao;

import com.venns.pojo.User;
import org.apache.ibatis.type.Alias;

import java.util.List;
import java.util.Map;

@Alias("user")
public interface UserMapper {
    //根据id查询用户
    User getUserById(int id);

    //分页
    List<User> getUserByLimit(Map<String,Integer> map);

    //分页2
    List<User> getUserByRowBounds();

}
