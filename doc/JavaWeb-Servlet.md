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

web服务器接收到客户端的http请求，针对这个请求，分别创建一个代表请求的HttpServletRequest对象，代表响应的HttpServletResponse对象

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

### 1.向浏览器输出消息

### 2.下载文件

1. 要获取下载文件的路径
2. 下载的文件名是什么
3. 设置想办法让浏览器能够支持下载我们需要的东西
4. 获取下载文件的输入流
5. 常见缓冲区
6. 获取OutputStream对象
7. 将FileOutputStream写入到Buffer缓冲区
8. 使用OutputStream将缓冲区当中的数据输出到客户端

```java
public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取下载文件的路径
        String realPath = this.getServletContext().getRealPath("/logo.png");
        System.out.println("下载文件的路径"+realPath);
        //2.下载文件名是什么
        String fileName = realPath.substring(realPath.lastIndexOf("//") + 1);
        //3.设置想办法让浏览器能够支持(content-disposition) 下载我们需要的东西,中文文件名需要用URLEncoder.encode解决乱码
        resp.setHeader("content-disposition","attachment;filename"+ URLEncoder.encode(fileName,"UTF-8"));
        //4.获取下载文件的输入流
        FileInputStream in = new FileInputStream(realPath);
        //5.创建缓冲区
        int len = 0;
        byte[] buffer = new byte[1024];
        //6.获取输出流对象
        ServletOutputStream out = resp.getOutputStream();
        //7.将FileOutputStream流写入buffer缓冲区,使用OutputStream将缓冲区的数据输出到客户端
        while((len = in.read(buffer)) > 0){
            out.write(buffer,0,len);
        }
        in.close();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

```

### 3.验证码功能

验证码怎么来的？

- 前端实现
- 后端实现，需要用到Java的图片类，生成一个图片

```java
package com.venns.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //如何让浏览器3秒刷新一次
        resp.setHeader("refresh","3");
        //在内存中创建一个图片
        BufferedImage Image = new BufferedImage(80,20,BufferedImage.TYPE_INT_RGB);
        //得到图片
        Graphics2D g = (Graphics2D) Image.getGraphics(); //笔
        //设置图片的背景颜色
        g.setColor(Color.white);
        g.fillRect(0,0,80,20);
        //给图片写数据
        g.setColor(Color.blue);
        g.setFont(new Font(null,Font.BOLD,20));
        g.drawString(makeNum(),0,20);
        //告诉浏览器，这个请求用图片的方式打开
        resp.setContentType("image/jpeg");
        //网站存在缓存，不让浏览器缓存
        resp.setDateHeader("expires",-1);
        resp.setHeader("Cache-Control","no-cache");
        resp.setHeader("Pragma","no-cache");

        //把图片写给浏览器
        ImageIO.write(Image,"jpg" ,resp.getOutputStream());

    }
    //生成随机数
    private String makeNum(){
        Random random = new Random();
        String num = random.nextInt(9999) + "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4-num.length(); i++) {
            sb.append("0");
        }
        num = sb.toString() + num;
        return num;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

```

### 4.实现重定向

一个web资源收到客户端请求后，会通知客户端去访问另外一个web资源，这个过程叫做重定向

常见场景：

- 用户登录

	```java
	void sendRedirect(String var1) throws IOException;
	```

测试:

```java
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/response_war/image");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
```

重定向和转发的区别：

相同点：

- 页面都会实现跳转

不同点：

- 请求转发的时候，url不会产生变化,307
- 重定向的时候，url会发生变化,302

案例:用户登录

```jsp
<html>
<body>
<h2>Hello World!</h2>
<%--这里提交的路径，需要寻找到项目的路径--%>
<%--${pageContext.request.contextPath}代表当前的项目--%>
<form action="${pageContext.request.contextPath}/login" method="get">
    用户名：<input type="text" name="username">
    密码：<input type="password" name="password">
    <input type="submit">
</form>
</body>
</html>

```

```java
public class RequestTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理请求
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username+":"+password);
        //重定向一直要注意路径问题，否则404
        resp.sendRedirect("/response_war/success.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
```

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Success!!!</h1>
</body>
</html>
```

# HttpServletRequest

HttpServletRequest代表客户端的请求，用户通过Http协议访问服务器，Http请求中的所有信息会被封装到HttpServletRequest，通过HttpServletRequest的方法，可以获得客户端的所有信息。

## 获取前端传递的参数,请求转发

```java
String getParameter(String var1);
String[] getParameterValues(String var1);
```

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <h1>登录</h1>
    <div>
        <%--这里表单表示以post的方式提交表单，提交到我们的login请求--%>
        <form action="${pageContext.request.contextPath}/login" method="post">
            用户名：<input type="text" name="username"><br>
            密码：<input type="password" name="password"><br>
            爱好：
            <input type="checkbox" name="hobbies" value="代码">代码
            <input type="checkbox" name="hobbies" value="唱歌">唱歌
            <input type="checkbox" name="hobbies" value="电影">电影
            <input type="submit">
        </form>
    </div>
</body>
</html>
```

```java
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String[] hobbies = req.getParameterValues("hobbies");
        System.out.println("=======================");
        System.out.println(username);
        System.out.println(password);
        System.out.println(Arrays.toString(hobbies));
        System.out.println("=======================");
        //通过请求转发
        //这里的 / 代表当前web应用
        req.getRequestDispatcher("/success.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
```

