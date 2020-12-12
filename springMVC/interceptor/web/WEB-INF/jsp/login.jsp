<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%--在WEB-INF下的所有页面或者资源，只能通过controller，或者servlet进行访问--%>
<body>
    <h1>登录页面</h1>
    <form action="${pageContext.request.contextPath}/user/login" method="post">
        username:<input type="text" name="username">
        password:<input type="password" name="password">
        <input type="submit" value="提交">
    </form>
</body>
</html>
