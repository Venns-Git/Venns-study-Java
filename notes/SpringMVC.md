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