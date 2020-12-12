<%--
  Created by IntelliJ IDEA.
  User: lwx01
  Date: 2020/12/12
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <form action="${pageContext.request.contextPath}/upload2" enctype="multipart/form-data" method="post">
      <input type="file" name="file">
      <input type="submit" value="upload">
    </form>
  </body>
</html>
