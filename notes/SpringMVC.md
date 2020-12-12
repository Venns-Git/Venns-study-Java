# SpringMVC

# MVC

MVC：是一种软件设计规范，是一种架构模式

- 模型（Model）【dao，service】
-  视图（View）【jsp】
- 控制器（Controller）【servlet】

Spring MVC特点:

- 轻量级
- 高效，基于请求响应的MVC框架
- 与Spring 兼容性好
- 约定大于配置
- 功能强大：RESTful，数据验证，格式化，本地化，主题等
- 简洁灵活

# 回顾Servlet

1. 导入依赖

	```xml
	<dependencies>
	    <dependency>
	        <groupId>javax.servlet</groupId>
	        <artifactId>servlet-api</artifactId>
	        <version>2.5</version>
	    </dependency>
	    <dependency>
	        <groupId>javax.servlet.jsp</groupId>
	        <artifactId>jsp-api</artifactId>
	        <version>2.2</version>
	    </dependency>
	</dependencies>
	```

2. servlet

	```java
	public class HelloServlet extends HttpServlet {
	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        //1.获取前端参数
	
	        String method = req.getParameter("method");
	        if (method.equals("add")){
	            req.getSession().setAttribute("msg","执行了add方法");
	        }
	        if (method.equals("delete")){
	            req.getSession().setAttribute("msg","执行了delete方法");
	        }
	
	        //2.调用业务层
	
	        //3.视图转发或者重定向
	        req.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(req,resp);
	    }
	
	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        doGet(req, resp);
	    }
	}
	```

3. jsp

	```jsp
	<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<html>
	<head>
	    <title>Title</title>
	</head>
	<body>
	    <form action="/hello" method="post">
	        <input type="text" name="method">
	        <input type="submit">
	    </form>
	</body>
	</html>
	```

	```jsp
	<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<html>
	<head>
	    <title>Title</title>
	</head>
	<body>
	${msg}
	</body>
	</html>
	```

4. 注册servlet

	```xml
	<servlet>
	    <servlet-name>hello</servlet-name>
	    <servlet-class>com.venns.servlet.HelloServlet</servlet-class>
	</servlet>
	<servlet-mapping>
	    <servlet-name>hello</servlet-name>
	    <url-pattern>/hello</url-pattern>
	</servlet-mapping>
	```

# helloMVC

1. 新建module，添加web支持

2. 导入springMVC依赖

3. 配置web.xml，注册DispatcherServlet

	```xml
	<!--注册DispatcherServlet-->
	<servlet>
	    <servlet-name>springmvc</servlet-name>
	    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	    <!--初始化，关联配置文件-->
	    <init-param>
	        <param-name>contextConfigLocation</param-name>
	        <param-value>classpath:springmvc-servlet.xml</param-value>
	    </init-param>
	    <!--设置启动级别为1级-->
	    <load-on-startup>1</load-on-startup>
	</servlet>
	
	<!--
	    /：匹配所有请求，不包括jsp
	    /*：匹配所有请求，包括jsp
	-->
	<servlet-mapping>
	    <servlet-name>springmvc</servlet-name>
	    <url-pattern>/</url-pattern>
	</servlet-mapping>
	```

4. 编写springMVC配置文件

	```xml
	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
	       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	       xsi:schemaLocation="http://www.springframework.org/schema/beans
	       http://www.springframework.org/schema/beans/spring-beans.xsd">
	
	    <!--添加处理映射器-->
	    <bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping" />
	    <!--添加处理器适配器-->
	    <bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter" />
	
	    <!--视图解析器-->
	    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="internalResourceViewResolver">
	        <!--前缀-->
	        <property name="prefix" value="/WEB-INF/jsp/" />
	        <!--后缀-->
	        <property name="suffix" value=".jsp" />
	    </bean>
	
	    <!--Handler-->
	    <bean id="/hello" class="com.venns.controller.HelloController" />
	</beans>
	```

5. 编写controller

	```java
	public class HelloController implements Controller {
	
	    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
	
	        //ModelAndView 模型和视图
	        ModelAndView mv = new ModelAndView();
	        
	        //封装对象,放在Model中
	        mv.addObject("msg","Hello SpringMVC");
	
	        //封装要跳转的视图，放在View中
	        mv.setViewName("hello");//  /WEB-INF/jsp/hello.jsp
	        return mv;
	    }
	}
	```

6. 编写jsp页面

	```jsp
	<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<html>
	<head>
	    <title>Title</title>
	</head>
	<body>
	${msg}
	</body>
	</html>
	```

6. 启动tomcat测试

可能遇到：404，排除步骤:

- 查看控制台，看看是不是缺少jar包
- 看在idea的项目发布中添加lib依赖了没
- 重启tomcat

## 执行流程

1. DispatcherServlet表示前置控制器，是整个SpringMVC的控制中心，用户发出请求，DispacherServlet接受请求并拦截请求

2. HandlerMapping为处理器映射，DispatcherServlet调用

	HandlerMapping,HandlerMapping根据请求url查找Handler

3. HandlerExecution表示具体Handler,其主要作用是根据url查找控制器，

4. HandlerExecution将解析后的信息传递给DispatherServlet,如解析控制器映射等

5. HandlerAdapter表示处理器适配器，其按照待定的规则去执行Handler

6. Handler让具体的Controller执行

7. Cntroller将具体的执行信息返回给HandlerAdapter,如ModelAndView

8. HandlerAdapter将视图逻辑名或模型传递给DispatcherServlet

9. DispatcherServlet调用视图解析器（ViewResolver）来解析HandlerAdapter传递的逻辑视图名

10. 视图解析器将解析的逻辑视图名传给DispatcherServlet

11. DispacherServlet根据逻辑解析器的视图结果，调用具体的视图

12. 最终视图呈现给用户

# 使用注解开发

1. 创建空项目，添加web支持

2. 注册DispatcherSetrvlet

	```xml
	<!--配置DispatcherServlet 这个是springMVC的核心：请求分发器，前端控制器-->
	<servlet>
	    <servlet-name>springmvc</servlet-name>
	    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	    <!--DispatcherServlet 要绑定spring的配置文件-->
	    <init-param>
	        <param-name>contextConfigLocation</param-name>
	        <param-value>classpath:springmvc-servlet.xml</param-value>
	    </init-param>
	    <!--启动级别 ： 1-->
	    <load-on-startup>1</load-on-startup>
	</servlet>
	<!--
	    在springmvc中：
	    /   ：只匹配所以的请求，不会匹配jsp页面
	    /*  ：匹配所有的请求，包括jsp页面
	-->
	<servlet-mapping>
	    <servlet-name>springmvc</servlet-name>
	    <url-pattern>/</url-pattern>
	</servlet-mapping>
	```

3. 配置springmvc-servlet.xml

	```xml
	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
	       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	       xmlns:context="http://www.springframework.org/schema/context"
	       xmlns:mvc="http://www.springframework.org/schema/mvc"
	       xsi:schemaLocation="http://www.springframework.org/schema/beans
	       http://www.springframework.org/schema/beans/spring-beans.xsd
	       http://www.springframework.org/schema/context
	       http://www.springframework.org/schema/context/spring-context.xsd
	       http://www.springframework.org/schema/mvc
	       http://www.springframework.org/schema/mvc/spring-mvc.xsd">
	
	    <!--自动扫描包，让指定包下的注解生效，由IOC容器统一管理-->
	    <context:component-scan base-package="com.venns.controller" />
	
	    <!--让springmvc 不处理静态资源 .css .js .html-->
	    <mvc:default-servlet-handler />
	
	    <!--
	    支持mvc注解驱动
	        在spring中一般采用@RequsetMapping注解来完成映射关系
	        想要@RequsetMapping注解生效
	        必须在上下文中注册@DefalutAnontationHandlerMapping
	        和一个AnnotationMethodHandlerAdapter 实例
	        这两个实例分别在类级别和方法级别处理
	        而annotation-driven 配置帮助我们自动完成上述两个实例的注入
	    -->
	    <mvc:annotation-driven />
	
	    <!--视图解析器-->
	    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="internalResourceViewResolver">
	        <!--前缀-->
	        <property name="prefix" value="/WEB-INF/jsp/" />
	        <!--后缀-->
	        <property name="suffix" value=".jsp" />
	    </bean>
	</beans>
	```

4. 创建对应的jsp 和 controller 包

5. 创建controller

	```java
	@Controller
	public class HelloController {
	    @RequestMapping("/hello")
	    public String hello(Model model){
	        //封装数据
	        model.addAttribute("msg","hello springMVCAnnotation");
	
	
	        return "hello"; //会被视图解析器处理 /WEB-INF/jsp/hello.jsp
	    }
	}
	```

6. 进行测试

# Controller

- 负责提供访问应用程序的行为，通过通过接口定义或注解定义两种方式
- 负责解析用户的请求并将其转化为一个模型
- 在spring MVC中，一个控制器可以包含多个方法
- 在spring MVC中，对于Controller的配置方式有很多种

## 方式一：实现Controller接口

```java
//实现该接口的类获得控制器功能
public interface Controller {
    //处理请求并返回一个模型与视图对象
    ModelAndView handleRequest(HttpServletRequest var1, HttpServletResponse var2) throws Exception;
}
```

1. 编写类实现接口

	```java
	//只要实现了 Controller 接口的类 说明这就是一个控制器
	public class ControllerTest1 implements Controller {
	    @Override
	    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
	        ModelAndView mv = new ModelAndView();
	
	        mv.addObject("msg","ControllerTest1");
	        mv.setViewName("test");
	
	        return mv;
	    }
	}
	```

2. 配置对应jsp文件

	```jsp
	<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<html>
	<head>
	    <title>Title</title>
	</head>
	<body>
	${msg}
	</body>
	</html>
	```

3. 在springmvc配置文件中注册bean

	```xml
	<bean name="/t1" class="com.venns.controller.ControllerTest1" />
	```

4. 测试运行

## 方式二：使用注解

编写Controller

```java
@Controller
public class ControllerTest2 {
    @RequestMapping("/t2") //url地址
    public String test1(Model model){
        model.addAttribute("msg","ControllerTest2");
        return "test";//视图
    }
}
```

- @Controller会被spring接管，不用手动注册bean，

- @RequestMapping参数为Controllor接管的url地址

- return 返回的参数为视图地址，会被视图解析器解析

# Restful风格

Restful就是一个资源得及资源操作的风格，不是标准也不是协议，基于这个风格实际的软件可以更简介，更有层次，更易于实现缓存等机制

1. 新建RestfulController类

	```java
	@Controller
	public class RestfulController {
	
	    //原来的：  http://localhost:8080/add?a=1&b=2
	    //Restful：http://localhost:8080/add/a/b
	    @RequestMapping("/add")
	    public String test(int a, int b, Model model){
	        int res = a + b;
	        model.addAttribute("msg","结果为"+res);
	        return "test";
	    }
	}
	```

2. 使用@PathVariable注解，让方法参数的值对应绑定要一个url模板变量上

	```java
	@Controller
	public class RestfulController {
	
	    //原来的：  http://localhost:8080/add?a=1&b=2
	    //Restful：http://localhost:8080/add/a/b
	    @RequestMapping("/add/{a}/{b}")
	    public String test(@PathVariable int a,@PathVariable int b, Model model){
	        int res = a + b;
	        model.addAttribute("msg","结果为"+res);
	        return "test";
	    }
	}
	```

- RequestMapping 可以添加第二个参数 method = RequestMethod.xxx xxx为请求方式，可以精简为xxxMapping

	```java
	@Controller
	public class RestfulController {
	
	    //原来的：  http://localhost:8080/add?a=1&b=2
	    //Restful：http://localhost:8080/add/a/b
	    
	    @GetMapping("/add/{a}/{b}")
	    public String test(@PathVariable int a,@PathVariable int b, Model model){
	        int res = a + b;
	        model.addAttribute("msg","结果为"+res);
	        return "test";
	    }
	}
	```

# 转发和重定向

```java
@Controller
public class ModelTest1 {

    //转发
    @RequestMapping("/m1/t1")
    public String test1(Model model){
        model.addAttribute("msg","ModelTest1");
        return "test";
    }

    //重定向
    @RequestMapping("/m1/t2")
    public String test2(Model model){
        model.addAttribute("msg","ModelTset2");
        return "redirect:/index.jsp";
    }
}
```

如果不配置视图解析器，则需要将路径写完整

# 接收请求参数及数据回显

## 数据处理

1. 提交的域名称和条件方法的参数名一致

	提交数据：http://loacl:8080/user/t1?name=venns

	处理方法：

	```java
	@Controller
	@RequestMapping("/user")
	public class UserController {
	    @GetMapping("/t1")
	    public String test1(String name, Model model){
	        //1.接受前端参数
	        System.out.println("接受到的参数为:"+name);
	
	        //2.返回结果给前端
	        model.addAttribute("msg",name);
	
	        //3.跳转视图
	        return "test";
	    }
	}
	
	```

2. 提交的域名称和条件方法的参数名不一致

	提交数据：http://loacl:8080/user/t1?username=venns

	处理方法：

	```java
	@Controller
	@RequestMapping("/user")
	public class UserController {
	    @GetMapping("/t1")
	    public String test1(@RequestParam("username") String name, Model model){
	        //1.接受前端参数
	        System.out.println("接受到的参数为:"+name);
	
	        //2.返回结果给前端
	        model.addAttribute("msg",name);
	
	        //3.跳转视图
	        return "test";
	    }
	}
	```

3. 提交的是一个对象

	```java
	/*
	    1.接收请求，判断参数的名字，假设名字直接在方法上，可以直接使用
	    2.假设传递的是一个对象User，匹配User对象中的字段名，如果名字一致则OK，分则匹配不到
	 */
	@GetMapping("/t2")
	public String test2(User user){
	    System.out.println(user);
	    return "test";
	}
	```

	注意：前端传递的参数必须和对象的字段名一致，否则为null

## 数据显示到前端

1. 通过ModelAndView
2. 通过Model
3. 通过ModelMap

- Model 只有寥寥几个方法用于存储数据
- ModelMap 继承了 LinkedMap 除了实现了自身的一些方法，同意的继承 LinkedMap 的方法和特性
- ModelAndView 可以存储数据的同时，可以进行设置返回逻辑视图，进行控制展示层的跳转

# JSON

前后端分离时代：

后端部署后端，提供接口，提供数据

​					JSON（数据交换格式）

前端独立部署：负责渲染后端的数据

## Controller返回JSON数据

## 使用Jackson

1. 导包

	```xml
	<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
	<dependency>
	    <groupId>com.fasterxml.jackson.core</groupId>
	    <artifactId>jackson-databind</artifactId>
	    <version>2.12.0</version>
	</dependency>
	```

2. 配置web.xml

	```xml
	<?xml version="1.0" encoding="UTF-8"?>
	<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
	         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
	         version="4.0">
	    <!--配置DispatcherServlet-->
	    <servlet>
	        <servlet-name>springmvc</servlet-name>
	        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	        <init-param>
	            <param-name>contextConfigLocation</param-name>
	            <param-value>classpath:springmvc-servlet.xml</param-value>
	        </init-param>
	    </servlet>
	    <servlet-mapping>
	        <servlet-name>springmvc</servlet-name>
	        <url-pattern>/</url-pattern>
	    </servlet-mapping>
	
	    <!--配置乱码过滤器-->
	    <filter>
	        <filter-name>encoding</filter-name>
	        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
	    </filter>
	    <filter-mapping>
	        <filter-name>encoding</filter-name>
	        <url-pattern>/*</url-pattern>
	    </filter-mapping>
	</web-app>
	```

3. 配置springmvc-servlet.xml

	```xml
	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
	       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	       xmlns:context="http://www.springframework.org/schema/context"
	       xmlns:mvc="http://www.springframework.org/schema/mvc"
	       xsi:schemaLocation="http://www.springframework.org/schema/beans
	       http://www.springframework.org/schema/beans/spring-beans.xsd
	       http://www.springframework.org/schema/context
	       http://www.springframework.org/schema/context/spring-context.xsd
	       http://www.springframework.org/schema/mvc
	       http://www.springframework.org/schema/mvc/spring-mvc.xsd">
	
	
	    <context:component-scan base-package="com.venns.controller" />
	    <mvc:default-servlet-handler />
	    <mvc:annotation-driven />
	
	    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="internalResourceViewResolver">
	        <!--前缀-->
	        <property name="prefix" value="/WEB-INF/jsp/" />
	        <!--后缀-->
	        <property name="suffix" value=".jsp" />
	    </bean>
	</beans>
	```

4. 编写User实体类

	```java
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public class User {
	    private String name;
	    private int age;
	    private String sex;
	
	}
	```


5. 解决乱码问题：

- 更改@RequestMapping参数为`value = "/j1",produces = "application/json;charset=utf-8"`

- 可以直接在springmvc配置文件中统一配置

	```xml
	<mvc:annotation-driven> 
	    <mvc:message-converters register-defaults="true">
	        <bean class="org.springframework.http.converter.StringHttpMessageConverter">
	            <constructor-arg value="UTF-8" />
	        </bean>
	        <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
	            <property name="objectMapper">
	                <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
	                    <property name="failOnEmptyBeans" value="false" />
	                 </bean>
	            </property>
	        </bean>
	    </mvc:message-converters>
	</mvc:annotation-driven>
	```

6. 编写工具类

  ```java
  public class JsonUtils {
  
      public static String getJson(Object object){
          return getJson(object,"yyyy-MM-dd HH:mm:ss");
      }
  
      public static String getJson(Object object,String dataFormat){
          ObjectMapper mapper = new ObjectMapper();
          //不使用时间戳的方式
          mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
          //定义日期格式
          SimpleDateFormat sdf = new SimpleDateFormat(dataFormat);
          mapper.setDateFormat(sdf);
          try {
              return mapper.writeValueAsString(object);
          } catch (JsonProcessingException e) {
              e.printStackTrace();
          }
          return null;
      }
  }
  ```

7. 编写Controller类

	```java
	@Controller
	// @RestController 不走视图解析器
	public class UserController {
	
	
	    @RequestMapping("/j1")
	    @ResponseBody//不会走视图解析器，会直接返回一个字符串
	    public String json1() throws JsonProcessingException {
	        //jackson ObjectMapper
	        ObjectMapper mapper = new ObjectMapper();
	
	        //创建一个对象
	        User user = new User("venns",3,"男");
	
	        String str = mapper.writeValueAsString(user);
	
	        return str;
	    }
	
	    //返回日期
	    @RequestMapping("/j2")
	    @ResponseBody
	    public String json2() throws JsonProcessingException {
	
	        User user1 = new User("venns1", 3, "男");
	        User user2 = new User("venns2", 3, "男");
	        User user3 = new User("venns3", 3, "男");
	        User user4 = new User("venns4", 3, "男");
	        List<User> userList = new ArrayList<>();
	        userList.add(user1);
	        userList.add(user2);
	        userList.add(user3);
	        userList.add(user4);
	
	        return JsonUtils.getJson(userList);
	    }
	
	    //返回日期
	    @RequestMapping("/j3")
	    @ResponseBody
	    public String json3() throws JsonProcessingException {
	        Date date = new Date();
	        return JsonUtils.getJson(date);
	    }
	}
	```

## FastJson

阿里开发一款专门用于Java开发的包，可以方便的实现json对象与JavaBean对象的转换

1. 导入依赖

	```java
	<dependency>
	    <groupId>com.alibaba</groupId>
	    <artifactId>fastjson</artifactId>
	    <version>1.2.47</version>
	</dependency>
	```

fastjson有三个主要的类：

- JSONObject 代表json对象
- JSONArray 代表json对象数组
- JSON代表 JSONObject和JSONArray的转换

主要方法：

- `JSON.toJSONString()` Java对象转JSON字符串
- `JSON.parseObject()` JSON字符串转Java对象
- `JSON.toJSON()` Java对象转JSON对象
- `JSON.toJavaObject` JSON对象转Java对象

# AJAX

异步交互技术，实现网页的局部刷新

## 使用iframe体验网页局部刷新

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>iframe测试体验页面无刷新</title>
    <script>
        function go(){
            document.getElementById("iframe").src = document.getElementById("url").value;
        }
    </script>
</head>
<body>
    <div>
        <p>请输入地址：</p>
        <input type="text" id="url">
        <input type="button" value="提交" onclick="go()">
    </div>
    <div>
        <iframe id="iframe" style="width: 100%;height: 800px"></iframe>
    </div>
</body>
</html>
```

## JQueyr AJAX

1. 导入jquery

2. JQuery.ajax()

	| 名称                         | 值/描述                            |
	| :--------------------------- | :----------------------------------------------------------- |
	| async                        | 布尔值，表示请求是否异步处理。默认是 true。                  |
	| beforeSend(*xhr*)            | 发送请求前运行的函数。                                       |
	| cache                        | 布尔值，表示浏览器是否缓存被请求页面。默认是 true。          |
	| complete(*xhr,status*)       | 请求完成时运行的函数（在请求成功或失败之后均调用，即在 success 和 error 函数之后）。 |
	| contentType                  | 发送数据到服务器时所使用的内容类型。默认是："application/x-www-form-urlencoded"。 |
	| context                      | 为所有 AJAX 相关的回调函数规定 "this" 值。                   |
	| data                         | 规定要发送到服务器的数据。                                   |
	| dataFilter(*data*,*type*)    | 用于处理 XMLHttpRequest 原始响应数据的函数。                 |
	| dataType                     | 预期的服务器响应的数据类型。                                 |
	| error(*xhr,status,error*)    | 如果请求失败要运行的函数。                                   |
	| global                       | 布尔值，规定是否为请求触发全局 AJAX 事件处理程序。默认是 true。 |
	| ifModified                   | 布尔值，规定是否仅在最后一次请求以来响应发生改变时才请求成功。默认是 false。 |
	| jsonp                        | 在一个 jsonp 中重写回调函数的字符串。                        |
	| jsonpCallback                | 在一个 jsonp 中规定回调函数的名称。                          |
	| password                     | 规定在 HTTP 访问认证请求中使用的密码。                       |
	| processData                  | 布尔值，规定通过请求发送的数据是否转换为查询字符串。默认是 true。 |
	| scriptCharset                | 规定请求的字符集。                                           |
	| success(*result,status,xhr*) | 当请求成功时运行的函数。                                     |
	| timeout                      | 设置本地的请求超时时间（以毫秒计）。                         |
	| traditional                  | 布尔值，规定是否使用参数序列化的传统样式。                   |
	| type                         | 规定请求的类型（GET 或 POST）。                              |
	| url                          | 规定发送请求的 URL。默认是当前页面。                         |
	| username                     | 规定在 HTTP 访问认证请求中使用的用户名。                     |
	| xhr                          | 用于创建 XMLHttpRequest 对象的函数。                         |

3. applicationContext.xml中配置静态资源过滤

	```xml
	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
	       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	       xmlns:context="http://www.springframework.org/schema/context"
	       xmlns:mvc="http://www.springframework.org/schema/mvc"
	       xsi:schemaLocation="http://www.springframework.org/schema/beans
	       http://www.springframework.org/schema/beans/spring-beans.xsd
	       http://www.springframework.org/schema/context
	       http://www.springframework.org/schema/context/spring-context.xsd
	       http://www.springframework.org/schema/mvc
	       http://www.springframework.org/schema/mvc/spring-mvc.xsd">
	
	    <!--自动扫描包-->
	    <context:component-scan base-package="com.venns.controller" />
	    <!--静态资源过滤-->
	    <mvc:default-servlet-handler/>
	    <!--注解驱动-->
	    <mvc:annotation-driven />
	
	    <!--视图解析器-->
	    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="internalResourceViewResolver">
	        <!--前缀-->
	        <property name="prefix" value="/WEB-INF/jsp/" />
	        <!--后缀-->
	        <property name="suffix" value=".jsp" />
	    </bean>
	</beans>
	```

4. 编写ajax

    ```jsp
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
      <head>
        <title>$Title$</title>
        <script src="${pageContext.request.contextPath}/statics/js/jquery-3.5.0.js"></script>
        <script>
          function a(){
            $.post({
              url:"${pageContext.request.contextPath}/a1",
              data:{"name":$('#username').val()},
              success:function (data){
                alert(data)
              }
            })
          }
        </script>
      </head>
      <body>
        <%--  失去焦点的时候，发起一个请求到后台  --%>
        <input type="text" id="username" onblur="a()">
      </body>
    </html>
    ```

5. 编写AjaxController

    ```java
    @RestController
    public class AjaxController {
        @RequestMapping("/a1")
        public void a1(String name, HttpServletResponse response) throws IOException {
            System.out.println("a1:param=>"+name);
            if ("venns".equals(name)){
                response.getWriter().print("true");
            }else {
                response.getWriter().print("false");
            }
        }
    }
    ```

## AJAX异步获取json对象

1. 导入jackson依赖

2. 编写controller

	```java
	@RequestMapping("/a2")
	public List<User> a2(){
	    ArrayList<User> userList = new ArrayList<>();
	
	    //添加数据
	    userList.add(new User("venns1",18,"男"));
	    userList.add(new User("venns2",19,"男"));
	    userList.add(new User("venns3",20,"男"));
	
	    return userList;
	}
	```

3. 编写jsp

	```jsp
	<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<html>
	<head>
	    <title>Title</title>
	    <script src="${pageContext.request.contextPath}/statics/js/jquery-3.5.0.js"></script>
	    <script>
	        $(function (){
	            $('#btn').click(function (){
	                let html = "";
	                $.post("${pageContext.request.contextPath}/a2",function (data) {
	                    for (let i = 0;i < data.length;i++){
	                        html += "<tr>" +
	                            "<td>" + data[i].name + "</td>" +
	                            "<td>" + data[i].age + "</td>" +
	                            "<td>" + data[i].sex + "</td>" +
	                            "</tr>"
	                    }
	                })
	                $('#content').html(html);
	            })
	        });
	    </script>
	</head>
	<body>
	    <input type="button" value="加载数据" id="btn">
	    <table>
	        <tr>
	            <td>姓名</td>
	            <td>年龄</td>
	            <td>性别</td>
	        </tr>
	        <tbody id="content">
	
	        </tbody>
	    </table>
	
	</body>
	</html>
	```

	## AJAX实现登录验证

	jsp

	```jsp
	<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<html>
	<head>
	    <title>Title</title>
	    <script src="${pageContext.request.contextPath}/statics/js/jquery-3.5.0.js"></script>
	    <script>
	        function a1(){
	            $.post({
	                url: "${pageContext.request.contextPath}/a3",
	                data: {"name":$("#name").val()},
	                success: function (name){
	                    if (name.toString() === "ok"){
	                        $("#userInfo").css("color","green");
	                    }else{
	                        $("#userInfo").css("color","red");
	                    }
	                    $("#userInfo").html(name)
	                }
	            })
	        }
	        function a2(){
	            $.post({
	                url: "${pageContext.request.contextPath}/a3",
	                data: {"pwd":$("#pwd").val()},
	                success: function (pwd){
	                    if (pwd.toString() === "ok"){
	                        $("#pwdInfo").css("color","green");
	                    }else{
	                        $("#pwdInfo").css("color","red");
	                    }
	                    $("#pwdInfo").html(name)
	                }
	            })
	        }
	    </script>
	</head>
	<body>
	    <p>
	        username:<input type="text" id="name" onblur="a1()">
	        <span id="userInfo"></span>
	    </p>
	    <p>
	        password:<input type="text" id="pwd" onblur="a2()">
	        <span id="pwdInfo"></span>
	    </p>
	</body>
	</html>
	```

	controller

	```java
	@RequestMapping("/a3")
	public String a3(String name,String pwd){
	    String msg = null;
	    if (name != null){
	        //从数据库查
	        if ("admin".equals(name)){
	            msg = "ok";
	        }else {
	            msg = "用户名有误";
	        }
	    }
	    if (pwd != null){
	        if ("123456".equals(pwd)){
	            msg = "ok";
	        }else {
	            msg = "密码有误";
	        }
	    }
	    return msg;
	}
	```

# 拦截器

类似Servlet钟的过滤器Filter，用于处理器进行预处理和后处理，开发者可以定义一些拦截器来实现特定的功能

**过滤器和拦截器的区别**:拦截器是AOP思想的具体应用

**过滤器**

- servlet规范中的一部分，任何Java web项目都可以使用
- 在url-pattern中配置了/*之后，可以对任何要访问的资源进行拦截

**拦截器**

- 拦截器是SpringMVC框架组件的，只有使用了SpringMVC框架的工程才能使用
- 拦截器只会拦截访问的控制器方法，如果访问的是jsp/jtml/js等是不会进行拦截的

## 自定义拦截器

自定义拦截器，必须实现HandlerInterceptor接口

1. 实现接口

	```java
	public class MyInterceptor implements HandlerInterceptor {
	
	    // return true; 执行下一个拦截器，放行
	    @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	        System.out.println("========处理前========");
	        return true;
	    }
	
	    @Override
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	        System.out.println("========处理后========");
	    }
	
	    @Override
	    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	        System.out.println("========清理========");
	    }
	}
	```

2. 编写controller

	```java
	@RestController
	public class TestController {
	
	    @GetMapping("/t1")
	    public String test(){
	        System.out.println("TestController==> rest()执行了");
	        return "ok";
	    }
	}
	```

3. applicationContext中配置拦截器

	```xml
	<!--拦截器配置-->
	<mvc:interceptors>
	    <mvc:interceptor>
	        <!--包括这个请求这个下面的所有请求-->
	        <mvc:mapping path="/**"/>
	        <bean class="com.venns.config.MyInterceptor"/>
	    </mvc:interceptor>
	</mvc:interceptors>
	```

# 文件上传和下载

1. 导入依赖

	```xml
	<dependencies>
	    <dependency>
	        <groupId>commons-fileupload</groupId>
	        <artifactId>commons-fileupload</artifactId>
	        <version>1.3.3</version>
	    </dependency>
	    <dependency>
	        <groupId>javax.servlet</groupId>
	        <artifactId>javax.servlet-api</artifactId>
	        <version>4.0.1</version>
	    </dependency>
	</dependencies>
	```

2. 编写前端

	```jsp
	<form action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data" method="post">
	  <input type="file" name="file">
	  <input type="submit" value="upload">
	</form>
	```

3. applicationContext配置文件上传

	```xml
	<!--文件上传配置-->
	<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
	    <!--请求的编码格式，必须要和jsp的pageEncoding属性一致-->
	    <property name="defaultEncoding" value="utf-8"/>
	    <!--上传文件大小上限，单位为字节 (10485760 == 10M)-->
	    <property name="maxUploadSize" value="10485760" />
	    <property name="maxInMemorySize" value="40960"/>
	</bean>
	```

4. 编写controller

	```java
	package com.venns.controller;
	
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.RestController;
	import org.springframework.web.multipart.commons.CommonsMultipartFile;
	
	import javax.servlet.http.HttpServletRequest;
	import java.io.File;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.InputStream;
	
	@RestController
	public class FileController {
	    @RequestMapping("/upload")
	    public String fileUpload2(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request) throws IOException {
	
	        //上传路径保存设置
	        String path = request.getServletContext().getRealPath("/upload");
	        File realPath = new File(path);
	        if (!realPath.exists()){
	            realPath.mkdir();
	        }
	        //上传文件地址
	        System.out.println("上传文件保存地址-->"+realPath);
	
	        //通过CommonsMultipartFile的方法直接写文件
	        file.transferTo(new File(realPath+"/"+file.getOriginalFilename()));
	        return "redirect:/index.jsp";
	    }
	}
	```



