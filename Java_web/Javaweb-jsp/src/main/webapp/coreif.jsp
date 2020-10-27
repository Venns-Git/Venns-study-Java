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
