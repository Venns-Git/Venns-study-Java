<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍展示页面</title>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12">
                <div class="page-header">
                    <h1>
                        <small>书籍列表 ———— 显示所有书籍</small>
                    </h1>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="column col-md-4">
                <%--toAddBook--%>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/toAddBook">新增书籍</a>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/allBook">显示全部书籍</a>
            </div>
            <div class="column col-md-4">
                <form action="${pageContext.request.contextPath}/book/queryBook" method="post">
                    <input type="text" name="queryBookName" placeholder="请输入要查询的书籍名称" class="form-control">
                    <input type="submit" value="查询" class="btn btn-primary">
                </form>
            </div>
        </div>
        <div class="row clearfix">
            <div class="col-md-12">
                <table class="table table-hover table-striped">
                    <thead>
                        <tr>
                            <th>书籍编号</th>
                            <th>书籍名称</th>
                            <th>书籍数量</th>
                            <th>书籍详情</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <%--书籍从数据库中查询出来，从list中遍历出来--%>
                    <tbody>
                        <c:forEach var="book" items="${list}">
                            <tr>
                                <td>${book.bookID}</td>
                                <td>${book.bookName}</td>
                                <td>${book.bookCounts}</td>
                                <td>${book.detail}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/book/toUpdate?id=${book.bookID}">修改</a>
                                    &nbsp;|&nbsp;
                                    <a href="${pageContext.request.contextPath}/book/deleteBook/${book.bookID}">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</body>
</html>
