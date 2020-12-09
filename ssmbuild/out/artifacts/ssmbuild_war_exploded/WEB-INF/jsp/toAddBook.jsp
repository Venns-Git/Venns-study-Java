<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12">
                <div class="page-header">
                    <h1>
                        <small>新增书籍</small>
                    </h1>
                </div>
            </div>
        </div>

        <form action="${pageContext.request.contextPath}/book/addBook" method="">
            <div class="from-group">
                <label>书籍编号</label>
                <input type="text" name="bookId" class="form-control">
            </div>
            <div class="from-group">
                <label>书籍名称</label>
                <input type="text" name="bookName" class="form-control">
            </div>
            <div class="from-group">
                <label>书籍数量</label>
                <input type="number" id="bookCounts" class="form-control">
            </div>
            <div class="from-group">
                <label>书籍描述</label>
                <input type="text" name="detail" class="form-control">
            </div>
            <div class="form-group">
                <input type="submit" class="form-control" value="确定添加">
            </div>
        </form>
    </div>
</body>
</html>