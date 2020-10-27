<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
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
</body>
</html>
