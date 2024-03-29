# mybatis-plus

> 简介

MyBatis-Plus（简称 MP）是一个 MyBatis的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。

mybatis本来就是简化JDBC操作的，mybatis-plus即简化mybatis操作。

> 特性

- **无侵入**：只做增强不做改变，引入它不会对现有工程产生影响，如丝般顺滑
- **损耗小**：启动即会自动注入基本 CURD，性能基本无损耗，直接面向对象操作
- **强大的 CRUD 操作**：内置通用 Mapper、通用 Service，仅仅通过少量配置即可实现单表大部分 CRUD 操作，更有强大的条件构造器，满足各类使用需求
- **支持 Lambda 形式调用**：通过 Lambda 表达式，方便的编写各类查询条件，无需再担心字段写错
- **支持主键自动生成**：支持多达 4 种主键策略（内含分布式唯一 ID 生成器 - Sequence），可自由配置，完美解决主键问题
- **支持 ActiveRecord 模式**：支持 ActiveRecord 形式调用，实体类只需继承 Model 类即可进行强大的 CRUD 操作
- **支持自定义全局通用操作**：支持全局通用方法注入（ Write once, use anywhere ）
- **内置代码生成器**：采用代码或者 Maven 插件可快速生成 Mapper 、 Model 、 Service 、 Controller 层代码，支持模板引擎，更有超多自定义配置等您来使用
- **内置分页插件**：基于 MyBatis 物理分页，开发者无需关心具体操作，配置好插件之后，写分页等同于普通 List 查询
- **分页插件支持多种数据库**：支持 MySQL、MariaDB、Oracle、DB2、H2、HSQL、SQLite、Postgre、SQLServer 等多种数据库
- **内置性能分析插件**：可输出 SQL 语句以及其执行时间，建议开发测试时启用该功能，能快速揪出慢查询
- **内置全局拦截插件**：提供全表 delete 、 update 操作智能分析阻断，也可自定义拦截规则，预防误操作

## 快速上手

> 步骤

1. 创建数据库

2. 创建表并添加数据

	```sql
	DROP TABLE IF EXISTS user;
	
	CREATE TABLE user
	(
	    id BIGINT(20) NOT NULL COMMENT '主键ID',
	    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	    age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	    PRIMARY KEY (id)
	);
	DELETE FROM user;
	
	INSERT INTO user (id, name, age, email) VALUES
	(1, 'Jone', 18, 'test1@baomidou.com'),
	(2, 'Jack', 20, 'test2@baomidou.com'),
	(3, 'Tom', 28, 'test3@baomidou.com'),
	(4, 'Sandy', 21, 'test4@baomidou.com'),
	(5, 'Billie', 24, 'test5@baomidou.com');
	```

3. 编写项目，初始化（springboot），导入依赖

	```xml
	<!--数据库驱动-->
	<dependency>
	    <groupId>mysql</groupId>
	    <artifactId>mysql-connector-java</artifactId>
	    <version>8.0.27</version>
	</dependency>
	<!--lombok-->
	<dependency>
	    <groupId>org.projectlombok</groupId>
	    <artifactId>lombok</artifactId>
	    <version>1.18.22</version>
	</dependency>
	<!--mybatis-plus-->
	<dependency>
	    <groupId>com.baomidou</groupId>
	    <artifactId>mybatis-plus-boot-starter</artifactId>
	    <version>3.2.0</version>
	</dependency>
	<dependency>
	    <groupId>org.springframework.boot</groupId>
	    <artifactId>spring-boot-starter-web</artifactId>
	</dependency>
	```

4. 连接数据库

5.  

	**传统方式：pojo-dao(连接mybatis，配置mapper.xml文件)-service-controller**

	使用mybatis-plus之后：

	- pojo

		```java
		@Data
		@AllArgsConstructor
		@NoArgsConstructor
		public class User {
		    private long id;
		    private String name;
		    private int age;
		    private String email;
		}
		```

	- mapper接口

		```java
		@Mapper
		public interface UserMapper extends BaseMapper<User> {
		}
		```

		在启动类上加上`@mapperScan`注解，参数为包路径

	- 使用

		```java
		List<User> users = userMapper.selectList(null);
		users.forEach(System.out::println);
		```

## 配置日志

所有的sql是不可见的，如果我们需要知道具体的执行流程，则需要查看日志

```properties
mybatis-plus.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
```

这里采用标准的控制台输出

## CRUD拓展

> inser插入

```java
@Test
void insertTest(){
    User user = new User();
    user.setName("venns");
    user.setAge(18);
    user.setEmail("123456@qq.com");
    int insert = userMapper.insert(user);
    System.out.println(insert); // 受影响行数
    System.out.println(user); // 发现自动生成了一个id
}
```

- 这里是采用了主键生成策略中的雪花算法（默认）

更改主键生成策略，可以在实体类的主键上加上`@TableId(TYPE = )`注解，其中有,`AUTO`（自增，其中数据库字段也必须有自增属性），`NONE`（未设置），`INPUT`（手动输入），`ID_WORKER`(雪花算法)，`UUID`，`ID_WORKER_STR`(雪花算法转字符串)

> update更新

```java
@Test
void updateTest(){
    User user = new User();
    //通过条件自动拼接动态sql
    user.setId(5);
    user.setName("venns");
    int i = userMapper.updateById(user);
    System.out.println(i);
}
```

查询，新增也是如此,也支持批量操作

> 自动填充

创建时间，更新时间等等诸如此类的操作都是自动化完成的，我们不用手动更新

1. 数据库级别：即在数据库中创建字段，每次操作都需要进行更新时间

2. 代码级别：

	1. 只需要实体类的对应属性上加上相应的注解即可

		```java
		@TableField(fill = FieldFill.INSERT)
		private Date createTime;
		@TableField(fill = FieldFill.INSERT_UPDATE)
		private Date updateTime;
		```

		`@TbaleField`表示这是一个数据库字段`fill`为填充方式`INSERT`表示插入的时候添加，`INSERT_UPDATE`为插入和更新的时候添加，还有`DEFAULT`默认不添加，`UPDATE`表示更新的时候添加

	2. 编写处理器处理注解

		```java
		@Component
		@Slf4j
		public class MyMetaObjectHandler implements MetaObjectHandler {
		
		    /*
		        插入时的填充策略
		     */
		    @Override
		    public void insertFill(MetaObject metaObject) {
		        log.info("==========start insert fill==========");
		        /**
		         * @filedName 需要处理的字段
		         * @fileVal 填充的值
		         * @metaObject 元数据
		         */
		        this.setFieldValByName("createTime",new Date(),metaObject);
		        this.setFieldValByName("updateTime",new Date(),metaObject);
		    }
		
		    @Override
		    public void updateFill(MetaObject metaObject) {
		        log.info("========start update fill=============");
		        this.setFieldValByName("updateTime",new Date(),metaObject);
		    }
		}
		```

		

## 乐观锁

意图：当要更新一条记录的时候，希望这条记录没有被别人更新

实现方式：

- 取出记录时，获取当前 version
- 更新时，带上这个 version
- 执行更新时， set version = newVersion where version = oldVersion
- 如果 version 不对，就更新失败

> 具体实现

1. 给数据库添加version字段，默认值为1，实体类加上对应属性

2. 给实体类的对应属性加上`@version`属性，表示这是一个乐观锁

	```java
	@Version
	private int version;
	```

3. 注册组件

	```java
	@EnableTransactionManagement
	// 扫描我们的mapper包 不用在启动类上加注解了
	@MapperScan("com.venns.mybatis_plus.mapper")
	@Configuration
	public class MyBatisPlusConfig {
	    @Bean
	    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
	        return new OptimisticLockerInterceptor();
	    }
	}
	```

4. 测试

	```java
	@Test
	void optimisticLockerTest(){
	    // 1.查询用户信息
	    User user1 = userMapper.selectById(1);
	
	    // 2.修改用户信息
	    user1.setName("Venns1");
	
	    // 4.模拟另外一个线程进行操作
	    User user2 = userMapper.selectById(1);
	    user2.setName("venns2");
	    userMapper.updateById(user2);
	
	    // 3.执行更新操作,由于存在乐观锁,会操作失败
	    userMapper.updateById(user1);
	}
	```

	

## 分页查询

1. 配置拦截器组件

	```java
	@Bean
	public PaginationInterceptor paginationInterceptor(){
	    return new PaginationInterceptor();
	}
	```

2. 使用page对象即可

	```java
	@Test
	void  paginationTest(){
	    /**
	         * @cureent 当前页
	         * @size 页面大小
	         */
	    Page<User> page = new Page<>(1,5);
	    userMapper.selectPage(page,null);
	    page.getRecords().forEach(System.out::println);
	}
	```

	

## 条件构造器(warppr)

通过自带的方法，我们可以进行简单的操作，如果是要进行复杂的条件操作，则需要条件构造器--warpper

例如：我们需要查询名字非空，年龄大于12的数据

```java
/**
     * 查询名字非空，年龄大于12的数据
     */
@Test
void wrapperQueryTest(){
    QueryWrapper<User> wrapper = new QueryWrapper<>();
    wrapper.isNotNull("name").ge("age",12);
    userMapper.selectList(wrapper).forEach(System.out::println);
}
```

warpper里面内置了很多条件，如：

<img src="image-20220620162647236.png" alt="image-20220620162647236"  />

等等，几乎涵盖了所有的条件。

## 代码生成器

通过代码生成器，我们可以自动生成简单的mapper,service,controller的代码

> 安装

```xml
<!--代码生成器-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-generator</artifactId>
    <version>3.2.0</version>
</dependency>
```

> 使用

```java
public class AutoGeneratorTest {
    public static void main(String[] args) {
        // 1. 构建一个代码生成器对象
        AutoGenerator ag = new AutoGenerator();

        // 2. 配置策略
        // 2.1 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir"); //获取当前项目路径
        gc.setOutputDir(projectPath+"/src/main/java"); // 将代码生成到项目路径下
        gc.setAuthor("Venns"); // 设置作者
        gc.setOpen(false); // 生成完是否打开文件夹
        gc.setFileOverride(true); // 是否覆盖文件
        gc.setServiceName("%sService"); // service文件名字格式
        gc.setIdType(IdType.ID_WORKER); // 主键生成策略
        gc.setDateType(DateType.ONLY_DATE); // 日期格式
        gc.setSwagger2(true); // 是否配置swagger
        ag.setGlobalConfig(gc);
        // 2.2 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL); // 数据库类型
        dsc.setDriverName("com.mysql.cj.jdbc.Driver"); // 驱动名字
        dsc.setUrl("jdbc:mysql://localhost:3306/mybatis_plus?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai"); // url
        dsc.setUsername("root");
        dsc.setPassword("123456");
        ag.setDataSource(dsc);
        // 2.3 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("generator"); // 模块名
        pc.setParent("com.venns"); // 包路径
        pc.setEntity("entity"); // 实体类包命名
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        ag.setPackageInfo(pc);
        // 2.4 策略配置
        StrategyConfig sc = new StrategyConfig();
        sc.setInclude("user"); // 设置要映射的表名
        sc.setNaming(NamingStrategy.underline_to_camel); // 设置类命名规则-下划线转驼峰
        sc.setColumnNaming(NamingStrategy.underline_to_camel); //设置字段命名规则
        sc.setEntityLombokModel(true); //是否使用Lombok
        sc.setRestControllerStyle(true); // controller采用restful风格
        //同时,也可以配置自动填充,逻辑删除,乐观锁等等...
        ag.setStrategy(sc);

        // 3. 执行代码生成器
        ag.execute();
    }
}
```

> 结果

![image-20220620174956424](image-20220620174956424.png)