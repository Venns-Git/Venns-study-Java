package com.venns;

import java.sql.*;

public class TestJdbc {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //配置信息
        //解决中文乱码 useUnicode=true&characterEncoding=utf-8
        String url = "jdbc:mysql://localhost:3306/?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "123456";

        //1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.连接数据库,代表数据库
        Connection connection = DriverManager.getConnection(url, username, password);

        //3.向数据库发送sql的对象 statement : CRUD
        Statement statement = connection.createStatement();

        //4.编写SQL
        String sql = "select * from users";

        //受影响的行数，增删改都是用executeUpdate
        //int i = statement.executeUpdate(sql);

        //5.执行查询SQL,返回结果集
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            System.out.println("id"+resultSet.getObject("id"));
            System.out.println("name"+resultSet.getObject("name"));
            System.out.println("birth"+resultSet.getObject("birth"));
        }
        //6.关闭连接,释放连接,先开后关
        statement.close();
        connection.close();
    }
}
