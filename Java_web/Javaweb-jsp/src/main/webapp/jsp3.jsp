<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--    <%@include 会将两个页面合二为一 --%>
    <%@include file="common/header.jsp"%>
    <h1>网页主体</h1>
    <%@include file="common/footer.jsp"%>
    <%--
        jsp标签
        jsp:include 拼接页面，本质还是第二个
    --%>
    <jsp:include page="common/header.jsp" />
    <h1>网页主体</h1>
    <jsp:include page="common/footer.jsp"/>
</body>
</html>
