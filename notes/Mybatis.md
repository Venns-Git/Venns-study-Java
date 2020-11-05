# Mybatis

# 1. 简介

## 1.1 什么是Mybatis

- MyBatis 是一款优秀的**持久层框架**

- 它支持自定义 SQL、存储过程以及高级映射
- MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作
- MyBatis 可以通过简单的 XML 或注解来配置和映射原始类型、接口和 Java POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录

## 1.2 持久化

**数据持久化**

- 持久化就是将程序的数据在持久状态和瞬时状态转化的过程
- 内存：**断电即失**
- 数据库(JDBC),IO文件持久化

- 生活：冷藏，罐头

**为什么需要持久化？**

- 有一些对象，需要长期保存

- 内存太贵

## 1.3 持久层

Dao层，Service层，Controller层

- 完成持久化工作的代码块
- 层界限十分明显

## 1.4 为什么需要Mybatis

- 帮助程序员将数据存入到数据库中

- 方便
- 传统JDBC代码太复杂
- 优点
	- 简单易学
	- 灵活
	- sql和代码分离，提高可维护性
	- 提供映射标签，支持对象与数据库的orm字段关系映射
	- 提供xml标签，支持编写动态sql

- **使用的人多**

# 2. 第一个Mybatis程序

## 2.1 搭建环境

搭建数据库

```sql
CREATE DATABASE `mybatis`;
USE `mybatis`;
CREATE TABLE `user`(
	`id` INT(20) not NULL PRIMARY KEY,
	`name` VARCHAR(30) DEFAULT NULL,
	`pwd` VARCHAR(30) DEFAULT NULL
)ENGINE=INNODB DEFAULT CHARSET=utf8;
INSERT INTO `user`(`id`,`name`,`pwd`)VALUES
(1,`venns`,`123456`),
(2,`tom`,`123456`),
(3,`mike`,`123456`)
```

新建项目

1. 新建一个普通的maven项目

2. 删除src目录

3. 导入maven依赖

	```xml
	<!-- 导入依赖   -->
	    <dependencies>
	        <!--MySQL驱动-->
	        <dependency>
	            <groupId>mysql</groupId>
	            <artifactId>mysql-connector-java</artifactId>
	            <version>8.0.16</version>
	        </dependency>
	        <!--Mybatis-->
	        <dependency>
	            <groupId>org.mybatis</groupId>
	            <artifactId>mybatis</artifactId>
	            <version>3.5.0</version>
	        </dependency>
	        <!--junit-->
	        <dependency>
	            <groupId>junit</groupId>
	            <artifactId>junit</artifactId>
	            <version>4.12</version>
	        </dependency>
	    </dependencies>
	```

## 2.2 创建一个模块

- 编写mybatis的核心配置文件

	```xml
	<?xml version="1.0" encoding="UTF-8" ?>
	<!DOCTYPE configuration
	        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
	        "http://mybatis.org/dtd/mybatis-3-config.dtd">
	<configuration>
	    <environments default="development">
	        <environment id="development">
	            <transactionManager type="JDBC"/>
	            <dataSource type="POOLED">
	                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
	                <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUnicode=true&amp;characterEncoding=UTF-8&amp;serverTimezone=GMT%2B8"/>
	                <property name="username" value="root"/>
	                <property name="password" value="123456"/>
	            </dataSource>
	        </environment>
	    </environments>
	</configuration>
	```
	
	
	
- 编写mybatis的工具类

	```java
	//SqlSessionFactory --> sqlSession
	public class MybatisUtils {
	    private static SqlSessionFactory sqlSessionFactory;
	    static {
	        try {
	            //使用Mybatis第一步：获取sqlSessionFactory对象
	            String resource = "mybatis-config.xml";
	            InputStream inputStream = Resources.getResourceAsStream(resource);
	            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		//既然有了SqlSessionFactory 顾名思义 我们就可以从中获得SqlSession的实例了
	    //SqlSession 完全包含了面向数据库执行SQL命令所需要的所有方法
	    public static SqlSession getSqlSession(){
	        return sqlSessionFactory.openSession();
	    }
	}
	```

## 2.3 编写代码

- 实体类

	```java
	public class User {
	    private int id;
	    private String name;
	    private String pwd;
	
	    public User() {
	    }
	
	    public User(int id, String name, String pwd) {
	        this.id = id;
	        this.name = name;
	        this.pwd = pwd;
	    }
	
	    public void setId(int id) {
	        this.id = id;
	    }
	
	    public void setName(String name) {
	        this.name = name;
	    }
	
	    public void setPwd(String pwd) {
	        this.pwd = pwd;
	    }
	
	    public int getId() {
	        return id;
	    }
	
	    public String getName() {
	        return name;
	    }
	
	    public String getPwd() {
	        return pwd;
	    }
	
	    @Override
	    public String toString() {
	        return "User{" +
	                "id=" + id +
	                ", name='" + name + '\'' +
	                ", pwd='" + pwd + '\'' +
	                '}';
	    }
	    
	}
	```

	

- Dao接口

	```java
	public interface UserDao {
	    List<User> getUserList();
	}
	```

	

- 接口实现类(由原来的UserDaoImpl转变为一个Mapper)

	```xml
	<?xml version="1.0" encoding="UTF-8" ?>
	<!DOCTYPE mapper
	        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
	        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
	<!--namespace = 绑定一个对象的Dao/Mapper接口-->
	<mapper namespace="com.venns.dao.UserDao">
	    <!--查询-->
	    <select id="getUserList" resultType="com.venns.pojo.User">
	        select * from mybatis.user
	    </select>
	</mapper>
	```

##  2.4测试

- 核心文件中注册Mappers

	```xml
	    <mappers>
	        <mapper resource="com/venns/dao/UserMapper.xml"/>
	    </mappers>
	```

- junit测试

	```java
	public class UserDaoTest {
	    @Test
	    public void test(){
	        //第一步：获得SqlSession对象
	        SqlSession sqlSession = null;
	        try {
	            //方式一：执行SQL
	            sqlSession = MybatisUtils.getSqlSession();
	            UserDao userDao = sqlSession.getMapper(UserDao.class);
	            List<User> userList = userDao.getUserList();
	            //方式二：
	            //sqlSession.selectList("com.venns.dao.UserDao.getUserList");
	            for (User user:userList){
	                System.out.println(user);
	            }
	        }catch (Exception e){
	            e.printStackTrace();
	        }finally {
	            //关闭SqlSession
	            sqlSession.close();
	        }
	    }
	}
	```

可能会遇到的问题：

1. 配置文件没有注册
2. 绑定接口错误
3. 方法名不对
4. 返回类型不对
5. Maven导出资源问题

# 3.CRUD

## 1.namespace

namespace中的包名要和Dao/Mapper接口中的包名一致

## 2.select

选择，查看

- id：就是对应的namespace中的方法名
- resultType：Sql语句执行的返回值
- parameter：参数类型



1. 编写接口

	```java
	//根据id查询用户
	User getUserById(int id);
	```

	

2. 编写对应mapper中的sql语句

	```xml
	    <select id="getUserById" parameterType="int" resultType="com.venns.pojo.User">
	        select * from mybatis.user where id = #{id}
	    </select>
	```

	

3. 测试

	```java
	    @Test
	    public void getUserById(){
	        SqlSession sqlSession = MybatisUtils.getSqlSession();
	        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
	        User user = mapper.getUserById(1);
	        System.out.println(user);
	        sqlSession.close();
	    }
	```

	

## 3.Insert

```xml
    <insert id="addUser" parameterType="com.venns.pojo.User">
        insert into mybatis.user (id,name,pwd) values (#{id},#{name},#{pwd})
    </insert>
```



## 4.Update

```xml
    <update id="updateUser" parameterType="com.venns.pojo.User">
        update mybatis.user set name=#{name},pwd=#{pwd} where id = #{id}
    </update>
```



## 5.Delete

```xml
    <delete id="deleteUser" parameterType="int">
        delete from  mybatis.user where id = #{id}
    </delete>
```

注意点：

- 增删改需要提交事务

	```java
	sqlSession.commit();
	```

## 6.万能的Map

假设，我们的实体类，或者数据库中的表，字段或者参数过多，我们应当考虑使用Map

```java
    //万能的map
    User addUser2(Map<String,Object> map);
```

```xml
    <insert id="addUser2" parameterType="map">
        insert into mybatis.user (id,name,pwd) values (#{userid},#{username},#{password})
    </insert>
```

```java
    @Test
    public void addUser2(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        HashMap<String, Object> map = new HashMap<>();
        map.put("userid",5);
        map.put("username","nico");
        map.put("password","666666");
        mapper.addUser2(map);
        sqlSession.close();
    }
```

Map传递参数，直接在sql取出key即可 【parameterType="map"】

对象传递参数，直接在sql中取出对象的属性即可 【parameterType="Object"】

只有一个基本类型参数的情况下，可以直接在sql中渠道 【】

多个参数用Map，**或者注解**

## 7.模糊查询

1. java代码执行的时候的，传递通配符 % %

	```java
	List<User> userList = mapper.getUserListLike("%t%");
	```

2. 在sql拼接中使用通配符

	```xml
	<select id="getUserListLike" resultType="com.venns.pojo.User">
	    select * from mybatis.user where name like #{value}
	</select>
	```

	