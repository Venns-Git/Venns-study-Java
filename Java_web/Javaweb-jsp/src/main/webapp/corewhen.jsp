<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
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
</body>
</html>
