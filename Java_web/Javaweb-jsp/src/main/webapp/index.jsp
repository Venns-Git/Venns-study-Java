<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error/500.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%--  JSP表达式
        作用：用来将程序的输出，输出到客户端
        <%= 变量或者表达式%>
     --%>
    <%= new java.util.Date()%>
    <%--JSP脚本片段--%>
    <%
        int sum = 0;
        for (int i = 0;i <= 100;i++){
            sum += i;
        }
        out.println("<h1>sum = "+sum+"</h1>");
    %>
    <%-- 在代码中嵌入HTML元素   --%>
    <%
        for (int i = 0; i < 10; i++) {
    %>
        <h1>hello world <%= i%></h1>
    <%
        }
    %>
    <%!
        static {
            System.out.println("loading Servlet");
        }
        private int global = 0;
        public void venns(){
            System.out.println("进入了方法!");
        }
    %>
</body>
</html>
