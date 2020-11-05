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

# 4.配置解析

## 1.核心配置文件

- mybatis-config.xml

- MyBatis 的配置文件包含了会深深影响 MyBatis 行为的设置和属性信息

	```xml
	properties（属性）
	settings（设置）
	typeAliases（类型别名）
	typeHandlers（类型处理器）
	objectFactory（对象工厂）
	plugins（插件）
	environments（环境配置）
	environment（环境变量）
	transactionManager（事务管理器）
	dataSource（数据源）
	databaseIdProvider（数据库厂商标识）
	mappers（映射器）
	```

## 2.环境配置

Mybatis 可以配置成适应多种环境

**不过要记住：尽管可以配置多个环境，但 每个 SqlSessionFactory 实例只能选择一种环境**

学会使用配置多套运行环境

Mybatis默认的事务管理器就是JDBC，连接池：POOLED

## 3.属性（properties）

我们可以通过properties属性来实现引用配置文件

这些属性都是可外部配置且可动态替换的，既可以在典型的Java属性文件中配置，亦可通过properties元素的子元素来传递【db.properties】

编写一个配置文件

db.properties

```properties
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/mybatis?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8
username=root
password=123456
```

在核心配置文件中引入（注意顺序）

```xml
<properties resource="db.properties">
        <property name="username" value="root"/>
        <property name="password" value="123456"/>
</properties>
```

- 可以直接引入外部文件
- 可以在其中增加一些属性配置
- 如果两个文件中有同一个字段，优先使用外部配置文件的

## 4.类型别名（typeAliases）

- 类型别名为Java类型设置的一个短的名字
- 存在的意义仅在于用来减少类完全限定名的冗余

```xml
    <typeAliases>
        <typeAlias type="com.venns.pojo.User" alias="User" />
    </typeAliases>
```

扫描实体类的包，它的别名就为这个类的类名，首字母小写

```xml
    <typeAliases>
        <package name="com.venns.pojo"/>
    </typeAliases>
```

在实体类比较少的时候，使用第一种方式

如果实体类十分多，建议使用第二种

第一种可以DIY别名，第二种则不行，如果非要改，需要在实体类上增加注解

```java
@Alias("user")
public class User{}
```

## 5.设置

这是mybatis中极为重要的设置，他们会改变mybatis的运行时行为

| 设置名             | 描述                  | 有效值                                                       | 默认值 |
| ------------------ | --------------------- | ------------------------------------------------------------ | ------ |
| cacheEnabled       | 加载缓存              | true，false                                                  | true   |
| lazyLoadingEnabled | 懒加载                | true，false                                                  | false  |
| logImpl            | 指定mybatis的具体实现 | SLF4J ，LOG4J ，LOG4J2 ，JDK_LOGGING ，COMMONS_LOGGING ，STDOUT_LOGGING ，NO_LOGGING |        |

## 6.其他配置

- typeHandlers（类型处理器）
- objectFactory（对象工厂）
- plugins（插件）
	- mybatis-generator-core
	- mybatis-plus
	- 通用mapper

## 7.映射器（mappers）

MapperRegistry：注册绑定我们的Mapper文件

方式一:【推荐使用】

```xml
<mappers>
    <mapper resource="com/venns/dao/UserMapper.xml"/>
</mappers>
```

方式二：使用class文件绑定注册

```xml
<mappers>
	<mapper class="com.venns.dao.UserDao" />
</mappers>
```

注意点：

- 接口和它的Mapper配置文件必须同名
- 接口和它的Mapper配置文件必须在同一个包下

方式三：使用扫描包进行注入绑定

```xml
<mappers>
	<package name="com.venns.dao" />
</mappers>
```

注意点：

- 接口和它的Mapper配置文件必须同名
- 接口和它的Mapper配置文件必须在同一个包下

## 8.生命周期和作用域

生命周期和作用域是至关重要的，因为错误的使用会导致非常严重的**并发问题**

**SqlSessionFactoryBuilder：**

- 一旦创建了SqlSessionFactory，就不再需要它了
- 局部变量

**SqlSessionFactory：**

- 说白了就是可以想象为：数据库连接池
- 一旦被创建就应该在运行期间一直存在，**没有任何理由丢弃它或重新创建另一个实例**

- 因此，最佳作用域是应用作用域
- 最简单的就是使用**单例模式**或者静态单例模式

**SqlSession：**

- 连接到连接池的一个请求
- SqlSession的实例不是线程安全的，因此是不能被共享的，所以它的最佳作用域是请求或者方法作用域
- 用完之后需要赶紧关闭，否则资源被占用

# 5.解决属性名和字段名不一致的问题

## 1.问题

新建一个项目，拷贝之前的，测试实体类字段不一致的情况

```java
public class User {
    private int id;
    private String name;
    private String password;
}
```

测试出现问题 password=null

解决方案:

1. 起别名

	```xml
	    <select id="getUserById" parameterType="int" resultType="com.venns.pojo.User">
	        select id,name,pwd as password from mybatis.user where id = #{id}
	    </select>
	```

## 2.resultMap

结果集映射

```
id name pwd
id name password
```

```xml
<resultMap id="UserMap" type="User">
    <result column="id" property="id" />
    <result column="name" property="name" />
    <result column="pwd" property="password" />
</resultMap>
<select id="getUserById" resultType="UserMap">
    select * from mybatis.user where id = #{id}
</select>
```

- resultMap元素是Mybatis中最重要最强大的元素
- resultMap的设计思想是，对于简单的语句不需要配置显式的结过映射，而对于复杂一嗲的语句只需要描述它们的关系就行了
- resultMap最有效的地方在于，虽然你已经对它相当了解了，但是根本就不要显式的用到他们

# 6.日志

## 1.日志工厂

如果一个数据库操作出现了异常，我们需要排错，日志就是最好的助手

曾经：sout，debug

现在：日志工厂

- SLF4J 

- LOG4J 【掌握】

- LOG4J2 

- JDK_LOGGING 

- COMMONS_LOGGING 

- STDOUT_LOGGING 【掌握】

- NO_LOGGING

在mybatis中具体实现哪个日志实现，在设置中设定

**STDOUT_LOGGING标准日志输出**