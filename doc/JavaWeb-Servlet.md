# Servlet简介

- 开发动态web的一门技术

- 如果想开发一个Servlet程序，只需要2个步骤
	- 编写一个类，实现Servlet接口
	- 把开发好的Java类部署到web服务器中

**把实现实现了Servlet接口的Java程序叫做，Servlet**

# HelloServlet

Serlvet接口由两个默认实现类：HttpServlet，GenericServlet

1. 构建一个Maven项目，删除src目录，在项目里建立Moudel

2. 关于Maven父子工程的理解

	父项目中会有

	```xml
	  <modules>
	    <module>Servlet_1</module>
	  </modules>
	```

	子项目中会有

	```xml
	<parent>
	    <artifactId>javaweb-servlet</artifactId>
	    <groupId>com.venns</groupId>
	    <version>1.0-SNAPSHOT</version>
	</parent>
	```

	父项目中的Java子项目可以直接使用

	```java
	son extends father
	```

3. Maven环境优化

	1. 修改web.xml为最新的
	2. 将Maven的结构搭建完整

4. 编写一个Servlet程序

	1. 编写一个普通的类

	2. 实现Servlet接口，这里我们直接继承HttpServlet

		```java
		public class HelloServlet extends HttpServlet {
		
		    //由于get或post只是请求实现的不同的方式，可以相互调用，业务逻辑都一样
		    @Override
		    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		        PrintWriter writer = resp.getWriter();
		        writer.print("Hello Servlet");
		    }
		
		    @Override
		    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		        doGet(req, resp);
		    }
		}
		```

5. 编写Servlet的映射

	我们写的是Java程序，但是要通过浏览器访问，而浏览器需要连接web服务器，所以我们需要在web服务中注册我们写的Servlet。还需要一个浏览器能访问的路径

	```xml
	<!--注册Servlet-->
	    <servlet>
	        <servlet-name>hello</servlet-name>
	        <servlet-class>com.venns.servlet.HelloServlet</servlet-class>
	    </servlet>
	    <!--Servlet请求路径-->
	    <servlet-mapping>
	        <servlet-name>hello</servlet-name>
	        <url-pattern>/hello</url-pattern>
	    </servlet-mapping>
	```

6. 配置Tomcat

	注意:配置项目发布的路径就可以了

7. 启动测试

# Mapping问题

1. 一个Servlet可以指定一个映射路径

	```xml
	<servlet-mapping>
	        <servlet-name>hello</servlet-name>
	        <url-pattern>/hello</url-pattern>
	</servlet-mapping>
	```

	

2. 一个Servlet可以指定多个映射路径

	```xml
	<servlet-mapping>
	        <servlet-name>hello</servlet-name>
	        <url-pattern>/hello</url-pattern>
	</servlet-mapping>
	<servlet-mapping>
	        <servlet-name>hello</servlet-name>
	        <url-pattern>/hello1</url-pattern>
	</servlet-mapping>
	<servlet-mapping>
	        <servlet-name>hello</servlet-name>
	        <url-pattern>/hello2</url-pattern>
	</servlet-mapping>
	```

	

3. 一个Servlet可以指定通用映射路径

	```xml
	<servlet-mapping>
	        <servlet-name>hello</servlet-name>
	        <url-pattern>/hello/*</url-pattern>
	</servlet-mapping>
	```

	

4. 默认请求路径

	```xml
	<servlet-mapping>
	        <servlet-name>hello</servlet-name>
	        <url-pattern>/*</url-pattern>
	</servlet-mapping>
	```

5. 指定一些后缀或前缀

	```xml
	<servlet-mapping>
	        <servlet-name>hello</servlet-name>
	        <url-pattern>*.venns</url-pattern>
	</servlet-mapping>
	```

6. 优先级问题

	指定了固有的映射路径优先级最高，如果找不到就会走默认的处理请求

	```xml
	<!--404 -->
	<servlet>
		<servlet-name>error</servlet-name>
	    <servlet-class>com.venns.servlet.ErrorServlet</servlet-class>
	</servlet>
	<servlet-mapping>
	        <servlet-name>error</servlet-name>
	        <url-pattern>/*</url-pattern>
	</servlet-mapping>
	```

	

# ServletContext

web容器在启动的时候，它会为每个web程序都创建一个对应的ServletContext对象，它代表了当前的web应用：

## 共享数据

我在这个Servlet中保存的数据，可以在另外一个Servlet中拿到

```java
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        String username = "venns";
        servletContext.setAttribute("username",username);//将数据保存到了ServletContext中
        System.out.println("hello");
    }
}
```

```java
public class GetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        String username = (String) servletContext.getAttribute("username");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().print("名字："+username);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
```

## 获取初始化参数

```xml
<!--配置一些web应用的初始化参数-->
    <context-param>
        <param-name>url</param-name>
        <param-value>jdbc:mysql://localhost:3306/mybatis</param-value>
    </context-param>
```

```java
public class ServletDemo03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        String url = servletContext.getInitParameter("url");
        resp.getWriter().print(url);

        Properties properties = new Properties();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
```

## 请求转发

```java
public class ServletDemo04 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入到ServletDemo04");
        ServletContext servletContext = this.getServletContext();
        servletContext.getRequestDispatcher("/gp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
```



## 读取资源文件

Properties

- 在Java目录下新建properties
- 在resources目录下新建properties

发现:都被打包到了同一个路径下，classes，俗称为classpath

思路：需要一个文件流

```Properties
username=root
password=123456
```

```java
public class ServletDemo05 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream is = this.getServletContext().getResourceAsStream("/WEB-INF/classes/db.properties");
        Properties prop = new Properties();
        prop.load(is);
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        resp.getWriter().print(username+" : "+password);
        is.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
```

# HttpServletResponse

web服务器接收到客户端的http请求，针对这个请求，分别创建一个代秒请求的HttpServletRequest对象，代表响应的HttpServletResponse对象

- 如果要获取客户端请求过来的参数：找HttpServletRequest
- 如果要给客户端响应一些信息：找HttpServletResponse

## 1.简单分类

负责向浏览器发送数据的方法

```java
ServletOutputStream getOutputStream() throws IOException;
PrintWriter getWriter() throws IOException;
```

负责向浏览器发送响应头的方法

```java
void setCharacterEncoding(String var1);

void setContentLength(int var1);

void setContentLengthLong(long var1);

void setContentType(String var1);

void setDateHeader(String var1, long var2);

void addDateHeader(String var1, long var2);

void setHeader(String var1, String var2);

void addHeader(String var1, String var2);

void setIntHeader(String var1, int var2);

void addIntHeader(String var1, int var2);
```

响应的状态码

## 2.常见应用

1. 向浏览器输出消息
2. 下载文件

# HttpServletRequest

