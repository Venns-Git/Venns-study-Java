# SpringBoot

Javaweb的开发框架，和SpringMVC类似，能够简化开发，迅速的开发web应用，约定大于配置

**主要优点：**

- 为所有Spring开发者更快的入门
- 开箱即用，简化项目配置
- 内嵌式简化Web项目
- 没有冗余代码生成和XML配置要求

**核心：自动装配**

# 第一个SpringBoot程序

1. 基于https://start.spring.io/进行快速开发，也可以从idea内置初始化，项目名为helloworld
2. 在helloworldApplication【程序主入口】同级目录增加controller包，dao包，service包...
3. 编写对应代码即可

**彩蛋：**

- 可以在resources目录下新建banner.txt改变springboot启动时的画面

# 原理初探

## 自动装配

**pom.xml**

- spring-boot-dependcies：核心依赖在父工程中
- 引入spring-boot依赖的时候，不需要指定版本，就是因为有这些版本仓库

**启动器**

- ```xml
	<dependency>
	    <groupId>org.springframework.boot</groupId>
	    <artifactId>spring-boot-starter</artifactId>
	</dependency>
	```

- 就是SpringBoot的启动场景，比如spring-boot-starter-web，就会自动导入web依赖
- SpringBoot会将所有的功能场景，变成一个个的启动器

 **主程序**

```java
@SpringBootApplication
public class Springboot01HelloworldApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot01HelloworldApplication.class, args);
    }

}
```

- `@SpringBootApplication` 标注这个类是一个springboot的应用
- `SpringApplication.run(Springboot01HelloworldApplication.class, args); `  讲springboot应用启动

- 注解

	```Java
	@SpringBootConfiguration //springboot的配置
		@Configuration //表明这是一个spring配置类
			@Component //表明这是一个spring组件
	@EnableAutoConfiguration //自动配置
		@AutoConfigurationPackage //自动注册包
			@Import({Registrar.class}) //导入选择器
		@Import(AutoConfigurationImportSelector.class) //自动导入选择器
			getAutoConfigurationFntry() //获得自动配置的实体
	        getCandidateConfigurations() //获取候选的配置
	            getSrpingFactoriseLoaderFactorClass() //标注了EnableAutoConfiguration注解的类
	  		loadFactoryNames() //获取所有的加载配置
	       	loadSpringFactories() //获取所有的资源
	            ClassLoader.getResources() //获取项目资源 "META-INF/spring.factoris"
	            ClassLoader.getSystemResources() //获取系统资源
	@ComponentScan //扫描当前主启动类同级的包
	```

结论：springboot的所有自动配置都是在启动的时候扫描并加载：`spring.factories`所有的自动配置类都在这里面，但是不一定生效，要判断条件是否成立，只要导入了对应的start，就有对应的启动器，有了启动器，我们自动装配就会生效。

**步骤：**

1. springboot在启动的时候，从类路径下/META-INF/spring.factories获取指定的值
2. 将这些自动配置的类导入容器，自动配置就会生效，帮我进行自动配置
3. 以前需要自动配置的东西，springboot就帮我们做了
4. 整个JavaEE，解决方案和自动配置的东西都在 spring-boot-autoconfigure-2.2.0.RELEASE.jar 包下
5. 它会把所有需要导入的组件，以类名的方式返回，这些组件就会被添加到容器
6. 容器中也会存在非常多的xxxAutoConfiguration的文件（@bean），就是这些类给容器导入了这个场景需要的所有组件，并且自动装配@Configuration
7. 有了自动配置类，免去了手动编写配置文件的工作

## run

```java
@SpringBootApplication
public class Springboot01HelloworldApplication {

    //该方法返回一个ConfigurableApplicationContext对象
    //参数一：应用入口类     参数类：命令行参数
    public static void main(String[] args) {
        SpringApplication.run(Springboot01HelloworldApplication.class, args);
    }

}
```

本以为是运行了一个main方法，没想到却开启了一个服务

### SpringApplication.run

#### SpringApplication

1. 推断应用的类型是普通的项目还是Web项目
2. 查找并加载所有可用初始化器，设置到initializers属性中
3. 推出所有的应用程序监听器，设置到listeners属性中
4. 推断并设置main方法的定义类，找到运行的主类

# SpringBoot配置

springboot使用一个全局的配置文件，配置文件的名称是固定的，推荐使用yaml格式

- application.properties
	- 语法结构：key=value
- application.yaml
	- 语法结构：key 空格 value

**配置文件的作用：修改springboot自动配置的默认值**

## yaml

基础语法：key：空格value

```yaml
# 普通的key-value
name: venns

# 对象
student:
  name: venns
  age: 3
# 行内写法
students: {name: venns,age: 3}

# 数组
pet:
  - cat
  - dog
  - pig
pets: [cat,dog,pig]
```

### 通过yaml给属性赋值

1. 编写yaml

	```yaml
	person:
	  name: venns
	  age: 3
	  happy: false
	  birth: 2001/01/1
	  maps: {k1: v1,k2: v2}
	  list:
	    - code
	    - music
	    - girl
	  dog:
	    name: 旺财
	    age: 3
	```

2. 给需要注入属性的类添加注解绑定

	```java
	@Component
	@ConfigurationProperties(prefix = "person")
	public class Person {
	
	    private String name;
	    private int age;
	    private boolean happy;
	    private Date birth;
	    private Map<String,Object> maps;
	    private List<Object> lists;
	    private Dog dog;
	```

- 需要导入依赖

	```xml
	<dependency>
	    <groupId>org.springframework.boot</groupId>
	    <artifactId>spring-boot-configuration-processor</artifactId>
	    <optional>true</optional>
	</dependency>
	```

### JSR303校验

使用yaml进行注入也可以进行数据校验

1. 导入依赖

	```xml
	<dependency>
	    <groupId>org.springframework.boot</groupId>
	    <artifactId>spring-boot-starter-validation</artifactId>
	</dependency>
	```

2. 给需要进行数据校验的类添加`@Validated`注解

3. 给对应的字段添加校验格式注解即可 例如：`@Email(message="xxxx")`，xxx为错误提示

## 多环境配置及配置文件位置

官方给出可以写配置的四个位置：

- file：`./config/`  项目目录下config目录
- file：`./` 项目目录下
- classpath：`/config/` resources目录下的config项目
- classpath：`/` resources目录下

**优先级由高到低**

### 多环境切换

springboot的多环境配置：可以选择激活哪一个配置文件

`spring.profiles.active=xxx`  ：xxx代表配置文件后缀,例如：application-test.properties 代表测试环境,application-dev.properties代表开发环境

### 可以用yaml实现多文档模块

```yaml
server:
  port: 8081
spring:
  profiles:
    active: dev
---
server:
  port: 8082
spring:
  profiles: dev
---
server:
  port: 8083
spring:
    profiles: test
```

# SpringBoot Web开发

## 静态资源导入

###  webjars

- 以jar包的形式导入前端资源，只需导入maven依赖即可

访问：/webjars/**，根据不同资源访问不同目录

### 放在resources目录下

resources目录，static目录，public目录

**优先级由高到低**

### 总结：

1. 在springboot，我们可以使用以下方式处理静态资源
	- webjars	`localhost:8080/webjars/`
	- public，static，/**，resources  `localhost:8080`
2. 优先级：resources > static(默认) > public

## 定制首页

可以将首页index.html放在 resources ， static， public目录下，直接放在资源文件夹下不生效

## 模板引擎

### Thymeleaf

1. 导入依赖

	```xml
	<dependency>
	    <groupId>org.thymeleaf</groupId>
	    <artifactId>thymeleaf-spring5</artifactId>
	</dependency>
	<dependency>
	    <groupId>org.thymeleaf.extras</groupId>
	    <artifactId>thymeleaf-extras-java8time</artifactId>
	</dependency>
	```

2. 在templates目录下编写html模板

### 语法

IndexController

```java
//在templates目录下的所有页面，只能通过controller来跳转
//这个需要模板引擎的支持
@Controller
public class IndexController {

    @RequestMapping("/test")
    public String index(Model model){
        model.addAttribute("msg","<h1>hello,springboot</h1>");
        model.addAttribute("users", Arrays.asList("venns1","venns2"));
        return "test";
    }
}
```

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <!--所有的html元素都可以被thymeleaf替换接管：th：元素名-->
    <!--不转义-->
    <div th:text="${msg}"></div>
    <!--转义-->
    <div th:utext="${msg}"></div>

    <!--循环-->
    <hr>
    <h3 th:each="user:${users}" th:text="${user}"></h3>
    <h3 th:each="user:${users}">[[${user}]]</h3>
</body>
</html>
```

## 装配扩展MVC

- 新建config包，写自己的配置类MyMvcConfig，添加@Configuration注解，实现WebMvcConfigurer接口

**自定义视图解析器**

```java
// 扩展MVC
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    
    // public interface ViewResolver 实现了视图解析器的类，就可以是视图解析器
    @Bean
    public ViewResolver myViewResolver(){
        return new MyViewResolver();
    }

    //自定义了一个自己的视图解析器
    public static class MyViewResolver implements ViewResolver{
        @Override
        public View resolveViewName(String s, Locale locale) throws Exception {
            return null;
        }
    }

}
```

**自定义视图跳转**

```Java
//视图跳转
@Override
public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/venns").setViewName("test");
}
```

总结：springboot自动配置组件的时候，先看有没有用户自己配置的，如果有就用用户配置的，没有就用自动配置的，如果有些组件存在多个，就将用户配置的和组件默认的结合起来

**国际化**

1. 配置i18n文件
2. 如果需要在项目中进行按钮自动切换，需要自定义组件 LocaleResolver
3. 将自己写的组件配置到spring容器中，@Bean

**404及500处理**

在模板文件夹下创建error文件夹，文件命名404，500即可

# 整合数据源

## 整合JDBC

对应数据访问层，无论是sql还是nosql，springboot底层都是采用springData的方式进行统一处理

1. 在application配置数据库

	```yml
	spring:
	  datasource:
	    username: root
	    password: 123456
	    url: jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
	    driver-class-name: com.mysql.cj.jdbc.Driver
	```

2. 编写controller测试

	```java
	@RestController
	public class JDBCController {
	
	    //JDBC模板
	    @Autowired
	    JdbcTemplate jdbcTemplate;
	
	    //查询数据库的所以信息
	    @RequestMapping("/userList")
	    public List<Map<String,Object>> userList(){
	        String sql = "select * from user";
	        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
	        return maps;
	    }
	
	    @RequestMapping("/addUser")
	    public String addUser(){
	        String sql = "insert into mybatis.user(id,name,pwd) values (5,'aaa','123456')";
	        jdbcTemplate.update(sql);
	        return "ok";
	    }
	```

## 自定义数据源 Druid

阿里巴巴的一个数据库连接池的实现，结合了c3p0,dbcp等DB池的优点，加入了日志监控

### 1.导入依赖

```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.1.16</version>
</dependency>
```

### 2.在yml配置文件中指定连接池位druid

```yaml
type: com.alibaba.druid.pool.DruidDataSource
```

### 3. druid特有配置

```yml
	#Spring Boot 默认是不注入这些属性值的，需要自己绑定
    #druid 数据源专有配置
    initialSize: 5
    minIdle: 5
    maxActive: 20
    maxWait: 60000
    timeBetweenEvictionRunsMillis: 60000
    minEvictableIdleTimeMillis: 300000
    validationQuery: SELECT 1 FROM DUAL
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    poolPreparedStatements: true

    #配置监控统计拦截的filters，stat:监控统计、log4j：日志记录、wall：防御sql注入
    #如果允许时报错  java.lang.ClassNotFoundException: org.apache.log4j.Priority
    #则导入 log4j 依赖即可，Maven 地址：https://mvnrepository.com/artifact/log4j/log4j
    filters: stat,wall,log4j
    maxPoolPreparedStatementPerConnectionSize: 20
    useGlobalDataSourceStat: true
    connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
```

- 如果需要用到log4j，还要导入依赖

### 4.配置后台监控

```java
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    //后台监控
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        //账号密码配置
        HashMap<String, String> initParameters = new HashMap<>();
        //增加配置
        initParameters.put("loginUsername","admin"); //固定的key loginUsername loginPassword
        initParameters.put("loginPassword","123456");

        //允许谁能访问
        initParameters.put("allow","");

        //禁止谁能访问
        initParameters.put("venns","192.168.1.1");


        bean.setInitParameters(initParameters);//设置初始化参数
        return bean;
    }
}
```

# 整合Mybatis

1. 导入依赖

	```xml
	<dependency>
	    <groupId>org.mybatis.spring.boot</groupId>
	    <artifactId>mybatis-spring-boot-starter</artifactId>
	    <version>2.1.4</version>
	</dependency>
	```

2. yml中编写数据库配置

3. yml整合mybatis

	```xml
	mybatis:
	  type-aliases-package: com.venns.pojo //别名
	  mapper-locations: classpath:mybatis/mapper/*.xml
	```

4. 编写对应的pojo和mapper
5. mapper.xml放在mapper-locations路径下

6. 测试

# SpringSecurity

侧重于身份验证和权限控制的安全框架

## 环境搭建:

初始化springboot项目，勾选web支持，security和themlef

初始化项目，具体代码看Git，最开始所有人都可以访问所有页面

## 用户认证和授权

编写自定义config类，继承WebSecurityConfigurerAdapter

```java
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
    }
}
```

```java
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页所有人可以访问，功能页只有对应有权限的人才能访问
        http.authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/level1/**").hasRole("vip1")
        .antMatchers("/level2/**").hasRole("vip2")
        .antMatchers("/level3/**").hasRole("vip3");

        //没有权限会默认到登录界面,需要开启登录的页面
        http.formLogin();
    }

    //认证
    //密码需要加密
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 这些数据正常应该从数据库中读
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("venns").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2","vip3")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2","vip3");
    }
}
```

## 注销

只需在configure方法中添加

```java
//开启注销功能,跳到首页
http.logout().logoutSuccessUrl("/");
```

在前端配置logout请求即可

## 权限控制

不同权限的用户登录过后显示不同的界面

1. pom.xml导入thymeleaf和security的整合包

	```xml
	<!--thymeleaf和security整合包  -->
	<dependency>
	    <groupId>org.thymeleaf.extras</groupId>
	    <artifactId>thymeleaf-extras-springsecurity5</artifactId>
	</dependency>
	```

2. 前端导入，要与pom.xml中保持版本一致

	```xml
	xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5"
	```

3. 编写前端

	- 登录状态

		```html
		<!--登录注销-->
		<div class="right menu">
		    <!--如果未登录-->
		    <div sec:authorize="!isAuthenticated()">
		        <a class="item" th:href="@{/toLogin}">
		            <i class="address card icon"></i> 登录
		        </a>
		    </div>
		
		    <!--如果已登录-->
		    <div sec:authorize="isAuthenticated()">
		        <a class="item">
		            用户名：<span sec:authentication="name"></span>
		            角色：<span sec:authentication="principal.authorities"></span>
		        </a>
		    </div>
		    <div sec:authorize="isAuthenticated()">
		        <a class="item" th:href="@{/logout}">
		            <i class="sign-out icon"></i>注销
		        </a>
		    </div>
		</div>
		```

	- 菜单根据不同权限动态展示

		```html
		<div class="column" sec:authorize="hasRole('vip1')">
		    <div class="ui raised segment">
		        <div class="ui">
		            <div class="content">
		                <h5 class="content">Level 1</h5>
		                <hr>
		                <div><a th:href="@{/level1/1}"><i class="bullhorn icon"></i> Level-1-1</a></div>
		                <div><a th:href="@{/level1/2}"><i class="bullhorn icon"></i> Level-1-2</a></div>
		                <div><a th:href="@{/level1/3}"><i class="bullhorn icon"></i> Level-1-3</a></div>
		            </div>
		        </div>
		    </div>
		</div>
		<div class="column" sec:authorize="hasRole('vip2')">
			//省略菜单具体内容
		</div>
		<div class="column" sec:authorize="hasRole('vip3')">
			//省略菜单具体内容
		</div>
		```

## 记住我以及首页定制

- 记住我

	```java
	//开启记住我功能,cookie 默认保存两周
	http.rememberMe();
	```

- 自定义登录页面

	```java
	http.formLogin().loginPage("/toLogin");
	```

	也可以再添加很多方法：

	- usernameParameter() : 用户名的参数名
	- passwordParameter()：密码的参数名
	- loginProcessingUrl()：登录请求的地址,也就是表单提交的地址

- 同样也可以在自定义的登录界面加上记住我

	- 前端

		```html
		<input type="checkbox" name="remember"> 记住我
		```

	- 后端

		```java
		http.rememberMe().rememberMeParameter("remember");
		```

# Shiro

三大对象:

- subject：用户
- SecurityManager：管理所有用户
- Realm：连接数据

## 快速上手

1. 导入依赖

	```java
	<dependencies>
	    <dependency>
	        <groupId>org.apache.shiro</groupId>
	        <artifactId>shiro-core</artifactId>
	        <version>1.4.0</version>
	    </dependency>
	    <!-- configure logging -->
	    <dependency>
	        <groupId>org.slf4j</groupId>
	        <artifactId>jcl-over-slf4j</artifactId>
	        <version>1.7.30</version>
	    </dependency>
	    <dependency>
	        <groupId>org.slf4j</groupId>
	        <artifactId>slf4j-log4j12</artifactId>
	        <version>1.7.7</version>
	    </dependency>
	    <dependency>
	        <groupId>log4j</groupId>
	        <artifactId>log4j</artifactId>
	        <version>1.2.17</version>
	    </dependency>
	</dependencies>
	```

2. 配置文件 ------  log4j.properties (log4j配置文件)   ,   shiro.ini (shiro用户及角色权限)

3. HelloWorld

```java
//获取当前的用户对象 subject
Subject currentUser = SecurityUtils.getSubject();
//通过当前用户拿到session
Session session = currentUser.getSession();
//判断当前用户是否被认证
currentUser.isAuthenticated()
//获取当前用户的认证
currentUser.getPrincipal()
//判断当前用户是否具有某种角色
currentUser.hasRole()
//判断当前用户是否具有某种权限
currentUser.isPermitted()
//注销    
currentUser.logout();   
```

## SpringBoot集成

1. 搭建一个基本的springboot的web框架，测试环境是否正常

2. 导入依赖

	```xml
	<dependency>
	   <groupId>org.apache.shiro</groupId>
	   <artifactId>shiro-spring</artifactId>
	   <version>1.4.1</version>
	</dependency>
	```

3. 自定义Realm对象

	```java
	//自定义的 UserRealm
	public class UserRealm extends AuthorizingRealm {
	
	    //授权
	    @Override
	    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
	        System.out.println("执行了 => 授权doGetAuthorizationInfo");
	        return null;
	    }
	
	    //认证
	    @Override
	    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
	        System.out.println("执行了 => 授权doGetAuthenticationInfo");
	        return null;
	    }
	}
	```

4. 编写ShiroConfig

	```java
	@Configuration
	public class ShiroConfig {
	
	    //第三步：ShiroFilterFactoryBean
	    @Bean
	    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
	        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
	        //设置安全管理器
	        factoryBean.setSecurityManager(defaultWebSecurityManager);
	        return factoryBean;
	    }
	
	
	    //第二步：DefaultWebSecurityManager
	    @Bean
	    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
	        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
	        //关联UserRealm
	        securityManager.setRealm(userRealm);
	
	        return securityManager;
	    }
	
	    //第一步：创建 Realm 对象，需要自定义
	    @Bean
	    public UserRealm userRealm(){
	        return new UserRealm();
	    }
	
	}
	```

## 登录拦截

1. 在ShiroFilterFactoryBean添加Shiro的内置过滤器

	```java
	HashMap<String, String> filterMap = new LinkedHashMap<>();
	
	filterMap.put("/user/*","authc");
	
	factoryBean.setFilterChainDefinitionMap(filterMap);
	/*
		anon：无需认证就可访问
	    authc：必须认证了才能访问
	    user：必须拥有 记住我 才能用
	    perms： 拥有对某个资源的权限才能访问
	    role：拥有某个角色权限才能访问
	*/
	```

	- 表示user下所有请求都必须认证才能访问

2. 自定义登录界面，并在controller配置跳转

	```java
	//设置登录请求
	factoryBean.setLoginUrl("/toLogin");
	```

	- 如果未认证，则跳转到自定义的登录界面

## 用户认证

在controller获取用户及封装数据

```java
@RequestMapping("/login")
public String login(String username,String password,Model model){
    //获取当前的用户
    Subject subject = SecurityUtils.getSubject();

    //封装用户的登录数据
    UsernamePasswordToken token = new UsernamePasswordToken(username, password);

    try {
        subject.login(token); //执行登录的方法，如果没有异常就说明ok了
        return "index";
    } catch (UnknownAccountException e) {  //用户名不存在
        model.addAttribute("msg","用户名错误");
        return "login";
    } catch (IncorrectCredentialsException e){ //密码错误
        model.addAttribute("msg","密码错误");
        return "login";
    }
}
```

在自定义的UserRealm的doGetAuthenticationInfo编写具体的认证

```java
//认证
@Override
protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
    System.out.println("执行了 => 授权doGetAuthenticationInfo");

    //用户名 密码
    String name = "root";
    String password = "123456";

    UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
    if (!token.getUsername().equals(name)){
        return null; //抛出异常：UnknownAccountException
    }

    //密码认证:shiro做
    return new SimpleAuthenticationInfo("",password,"");
}
```