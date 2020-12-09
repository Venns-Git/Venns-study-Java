<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改书籍</title>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12">
            <div class="page-header">
                <h1>
                    <small>修改书籍</small>
                </h1>
            </div>
        </div>
    </div>

    <form action="${pageContext.request.contextPath}/book/updateBook" method="post">
        <div class="from-group">
            <label>书籍编号</label>
            <input type="text" name="bookId" class="form-control" value="${books.bookID}">
        </div>
        <div class="from-group">
            <label>书籍名称</label>
            <input type="text" name="bookName" class="form-control" value="${books.bookName}">
        </div>
        <div class="from-group">
            <label>书籍数量</label>
            <input type="text" name="bookCounts" class="form-control" value="${books.bookCounts}">
        </div>
        <div class="from-group">
            <label>书籍描述</label>
            <input type="text" name="detail" class="form-control" value="${books.detail}">
        </div>
        <div class="form-group">
            <input type="submit" class="form-control" value="确定修改">
        </div>
    </form>
</div>
</body>
</html>