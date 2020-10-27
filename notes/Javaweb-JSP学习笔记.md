# Javaweb-JSP学习笔记

## 什么是JSP

Java Server pages：Java服务器端页面，也和Servlet一样，用于动态web技术

最大的特点：

- 写JSP就像在写HTML
- 区别：
	- HTML只给用户提供静态的数据
	- JSP页面中可以嵌入Java代码，为用户提供动态数据

## JSP原理

思路：JSP到底怎么执行的

- 代码层面没有任何问题

- 服务器内部工作

	- tomcat中有一个word目录

		IDEA中使用Tomcat的会在IDEA的tomcat中生产一个work目录

**浏览器向服务器发送请求，不管访问什么资源，其实都是在访问Servlet**

JSP最终也会被转换成为一个Java类

**JSP本质上就是一个Servlet**

```java
public void _jspInit(){} //初始化
public void _jspDestroy(){} //销毁
public void _jspService(HttpServletRequest request,HttpServletResponse response) //JSPService
```

1. 判断请求

2. 内置了一些对象

	```java
	final javax.servlet.jsp.PageContext pageContext; //页面上下文
	javax.servlet.http.HttpSession session = null;  //session
	final javax.servlet.ServletContext application; //applicationContext
	final javax.servlet.ServletConfig config;		//config
	javax.servlet.jsp.JspWriter out = null;			//out
	final java.lang.Object page = this;				//page：当前 
	HttpServletRequest request						//请求 
	HttpServletResponse response					//响应
	```

	

3. 输出页面前增加的代码

	```java
	      response.setContentType("text/html"); //设置响应的页面类型
	      pageContext = _jspxFactory.getPageContext(this, request, response,
	      			null, true, 8192, true);
	      _jspx_page_context = pageContext;
	      application = pageContext.getServletContext();
	      config = pageContext.getServletConfig();
	      session = pageContext.getSession();
	      out = pageContext.getOut();
	      _jspx_out = out;
	```

4. 以上的这些个对象我们可以在JSP页面中直接使用

## JSP基础语法

任何语言都有自己的语法，Java中有，JSP作为Java技术的一种应用，它有一些自己扩充的语法，Java所有的语法都支持

**JSP表达式**

```jsp
<%--  JSP表达式
        作用：用来将程序的输出，输出到客户端
        <%= 变量或者表达式%>
     --%>
```

**JSP脚本片段**

```jsp
<%
        int sum = 0;
        for (int i = 0;i <= 100;i++){
            sum += i;
        }
        out.println("<h1>sum = "+sum+"</h1>");
    %>
```

**脚本片段的再实现**

```jsp
<%-- 在代码中嵌入HTML元素   --%>
    <%
        for (int i = 0; i < 10; i++) {
    %>
        <h1>hello world <%= i%></h1>
    <%
        }
    %>
```

**JSP声明**

```jsp
<%!
        static {
            System.out.println("loading Servlet");
        }
        private int global = 0;
        public void venns(){
            System.out.println("进入了方法!");
        }
    %>
```

jsp声明:会被编译到JSP生成的Java类中，其他的，就会被生成到_jspService中

在JSP中嵌入Java代码即可

```jsp
<%%>
<%=%>
<%!%>
<%--注释--%>

```

JSP的注释不会再客户端显示，HTML就会

## JSP指令

```jsp
<%@page args....%>
<%@include file=""%>
<%--    <%@include 会将两个页面合二为一 --%>
<%@include file="../common/header.jsp"%>
<h1>网页主体</h1>
<%@include file="../common/footer.jsp"%>
<%--
    jsp标签
    jsp:include 拼接页面，本质还是第二个
        --%>
<jsp:include page="../common/header.jsp" />
<h1>网页主体</h1>
<jsp:include page="../common/footer.jsp"/>
```

## 9大内置对象

- pageContext  存东西
- Request  存东西
- Response
- Seession  存东西
- Application  (SerlvetContext)  存东西
- config  (ServletConfig)
- out
- page  (几乎不用)
- excepetion

```java
pageContext.setAttribute("name1","N0.1"); //保存的数据只在一个页面中有效
request.setAttribute("name2","N0.2"); //保存的数据只在一次请求中有效，请求转发会携带这个数据
session.setAttribute("name1","N0.3"); //保存的数据只在一次会话中有效，从打开浏览器到关闭浏览器
application.setAttribute("name1","N0.4");// 保存的数据只在服务器中有效，从打开服务器到关闭服务器
```

requset：客户端向服务器发送请求，产生的数据，用户看完就没用了，比如：新闻

session：客户端向服务器发送请求，产生的数据，用户用完一会还有用，比如：购物车

application：客户端向服务器发送请求，产生的数据，一个用完了，其他用户还可能使用，比如：聊天数据

## JSP标签、JSTL标签，EL表达式

```xml
<!--JSTL表达式的依赖-->
<dependency>
    <groupId>javax.servlet.jsp.jstl</groupId>
    <artifactId>jstl-api</artifactId>
    <version>1.2</version>
</dependency>
<!--standard标签库 -->
<dependency>
    <groupId>taglibs</groupId>
    <artifactId>standard</artifactId>
    <version>1.1.2</version>
</dependency>
```



EL表达式：${}

- **获取数据**
- **执行运算**
- **获取web开发的常用对象**
- ~~调用Java方法~~

**JSP标签**

```jsp
//拼接页面
<%--<jsp:include page=""/>--%>
//请求转发
<%--<jsp:forward page=""></jsp:forward>--%>
<%--<jsp:forward page="">--%>
//请求转发同时携带参数
<%--    <jsp:param name="" value=""/>--%>
<%--</jsp:forward>--%>
```

**JSTL表达式**

JSTL标签库的使用就是为了弥补HTML标签的不足；它自定义了许多的标签，可以供我们使用，标签的功能和Java代码一样

**核心标签**（掌握部分）

**格式化标签**

**SQL标签**

**XML标签**

**JSTL使用步骤：**

- 引入对应的taglib
- 使用其中的方法
- 在Tomcat中也需要引入jstl的jar包

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入JSTL标准标签库，--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>if测试</h1>
    <hr>
    <form action="coreif.jsp" method="get">
        <%--
            EL表达式获取表单中的数据
            ${param.参数名}
        --%>
        <input type="text" name="username" value="${param.username}">
        <input type="submit" value="登录">
    </form>
    <%-- 判断如果是登录的管理员，则登录成功--%>
    <c:if test="${param.username == 'admin'}" var="isAdmin">
        <c:out value="欢迎你，管理员" />
    </c:if>
    <%--自闭合标签--%>
    <c:out value="${isAdmin}" />
</body>
</html>
```

```jsp
<%--  定义一个遍历score 值为85  --%>
    <c:set var="score" value="85"/>
    <c:choose>
        <c:when test="${score >= 90}">
            优秀
        </c:when>
        <c:when test="${score >= 80}">
            良好
        </c:when>
        <c:when test="${score >= 70}">
            中
        </c:when>
    </c:choose>
```

```jsp
<%
        ArrayList<String> people = new ArrayList<>();
        people.add(0,"张三");
        people.add(1,"李四");
        people.add(2,"王五");
        request.setAttribute("list",people);
    %>
    <%--
        var,每一次遍历出来的变量
        items，要遍历的对象
        begin 开始位置
        end  结束位置
        step 步长
    --%>
    <c:forEach var="people" items="${list}">
        <c:out value="${people}" /><br>
    </c:forEach>
    <hr>
    <C:forEach var="people" items="${list}" begin="0" end="1" step="1">

    </C:forEach>
```

