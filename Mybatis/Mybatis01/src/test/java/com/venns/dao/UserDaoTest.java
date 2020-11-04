package com.venns.dao;

import com.venns.pojo.User;
import com.venns.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import java.util.List;

public class UserDaoTest {
    @Test
    public void test(){
        //第一步：获得SqlSession
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //方式一：执行SQL
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.getUserList();
        //方式二：
        //sqlSession.selectList("com.venns.dao.UserDao.getUserList");
        for (User user:userList){
            System.out.println(user);
        }

        //关闭SqlSession
        sqlSession.close();
    }
}
