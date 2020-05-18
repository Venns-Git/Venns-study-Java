# 使用JDBC操作MySQL
步骤
- 加载驱动
- 连接数据库
- 操作数据库（增删改查）
- 关闭结果集，操作，数据库
## 准备工作
- java连接MySQL的jar包
## 加载数据库驱动
```java
public class LoadDriver(){
    String final DbDriver = "com.mysql.jdbc.Driver";
    public static void main(String[] args){
        try{
            Class.forName(DbDriver);
        }catch (ClassNotFoundException e){
            e.printStackTeace();
        }
    }  
}
```
- DbDriver : MySQL驱动
## 连接数据库
```java
public class ConDb{
    String final DbUrl = "jdbc:mysql:///test";
    String final DbUser = "root";
    String final DbPass = "root";
    Connection con = null;
    public static void main(String[] args){
        try{
            con = DriverManager.getConnection(DbUrl,DbUser,Dbpass);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
```
- DBurl:数据库地址 jdbc:mysql//mysql地址(本机localhost):端口号（默认3306）/数据库名字,可简写jdbc:mysql:///数据库名字
- DbUser:用户名
- Dbpass:密码
## 操作数据库
```java
public class OpDb{
    Statement stmt = null;
    ResultSet rs = null;
    //sql插入语句
    String insertSQL = "insert into user(id,name,age) values(1,'XiaoMing',18)";
    //sql修改语句
    String alterSQL = "update user set name = 'Venns' where id = 1";
    //sql删除语句
    String selectSQL = "delete from user where id = 1";
    //sql查询语句
    String selectSQL = "select id,name,age from user"
    try{
        //实例化Statement对象
        stmt = con.createStatement();
        //执行数据更新操作
        stmt.executeUpdate(inserSQL);
        stmt.executrUpdate(alterSQL);
        stmt.executeUpdate(deleteSQL);
        //执行数据库查询操作
        rs = stmt.executeQuery(seleteSQL);
        while(rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            System.out.print("id"+id+" ");
            System.out.print("name"+name+" ");
            System.out.println("age"+age+" ");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
```
## 关闭结果集，操作，数据库
```java
public class closeDb{
    public static void main(String[] args){
        try{
            //关闭结果集
            assert rs != null;
            rs.close();
            //关闭操作
            stmt.close();
            //关闭操作
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
```
## 完整代码:
```java
import java.sql.*;
public class JdbcDemo{
    //定义MySQL的数据库驱动程序
    public static final String DbDriver = "com.mysql.jdbc.Driver";
    //定义MySQL数据库的连接地址
    public static final String DbUrl = "jdbc:mysql:///test";
    //MySQL数据库的连接用户名
    public static final  String DbUser = "root";
    //mysql数据库的连接密码
    public static final  String DbPass = "root";
    public static void main(String[] args){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        //数据库插入语句
        String insertSQL = "insert into user(id,name,age) values(1,'XiaoMing',18)";
        //数据库修改语句
        String alterSQL = "update user set name = 'Venns' where id = 1";
        //数据库删除语句
        String deleteSQL = "delete from user where id = 1";
        //数据库查询语句
        String selectSQL = "select id,name,age from user";
        try {
            //加载驱动程序
            Class.forName(DbDriver);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try {
            //连接数据库
            con = DriverManager.getConnection(DbUrl,DbUser,DbPass);
            //实例化Statement对象
            stmt = con.createStatement();
            //执行数据库更新操作
            stmt.executeUpdate(insertSQL);
            stmt.executeUpdate(alterSQL);
            stmt.executeUpdate(deleteSQL);
            //执行数据库查询操作
            rs = stmt.executeQuery(selectSQL);
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.print("id"+id+" ");
                System.out.print("name:"+name+" ");
                System.out.println("age" + age);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(con);
        try{
            //关闭结果集
            assert rs != null;
            rs.close();
            //关闭操作
            stmt.close();
            //关闭数据库
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
```