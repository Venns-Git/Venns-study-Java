package com.venns;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class Springboot04DataApplicationTests {

    @Autowired
    DataSource dataSource;


    @Test
    void contextLoads() throws SQLException {
        //查看默认数据源 class com.zaxxer.hikari.HikariDataSource
        System.out.println(dataSource.getClass());


        //获得数据库连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);


        //关闭
        connection.close();
    }

}
