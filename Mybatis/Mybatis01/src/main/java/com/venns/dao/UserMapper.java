package com.venns.dao;

import com.venns.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    //查询全部用户
    List<User> getUserList();

    List<User> getUserListLike(String value);

    //根据id查询用户
    User getUserById(int id);

    //增加用户
    int addUser(User user);

    //万能的map
    User addUser2(Map<String,Object> map);

    //修改用户
    int updateUser(User user);

    //删除一个用户
    int deleteUser(int id);

}
