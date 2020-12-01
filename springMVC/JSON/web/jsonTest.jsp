<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">

        //编写一个JavaScript对象
        let user = {
            name:"venns",
            age:3,
            sex:"男"
        };

        //将js对象转换为JSON对象
        let json = JSON.stringify(user)
        console.log(user);

        //将JSON对象转换为js对象
        let obj = JSON.parse(json);
        console.log(obj);
    </script>
</head>
<body>

</body>
</html>
