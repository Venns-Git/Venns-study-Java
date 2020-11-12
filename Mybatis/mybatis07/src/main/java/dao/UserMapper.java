package dao;


import org.apache.ibatis.annotations.Param;
import pojo.User;

public interface UserMapper {

    //根据id查询用户
    User queryUserById(@Param("id") int id);

    //修改用户
    int updateUser(User user);
}
