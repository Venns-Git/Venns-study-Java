package com.venns.dao;

import com.venns.pojo.User;

public interface UserMapper {
    //根据id查询用户
    User getUserById(int id);

}
