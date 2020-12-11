<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/statics/js/jquery-3.5.0.js"></script>
    <script>
        function a1(){
            $.post({
                url: "${pageContext.request.contextPath}/a3",
                data: {"name":$("#name").val()},
                success: function (name){
                    if (name.toString() === "ok"){
                        $("#userInfo").css("color","green");
                    }else{
                        $("#userInfo").css("color","red");
                    }
                    $("#userInfo").html(name)
                }
            })
        }
        function a2(){
            $.post({
                url: "${pageContext.request.contextPath}/a3",
                data: {"pwd":$("#pwd").val()},
                success: function (pwd){
                    if (pwd.toString() === "ok"){
                        $("#pwdInfo").css("color","green");
                    }else{
                        $("#pwdInfo").css("color","red");
                    }
                    $("#pwdInfo").html(name)
                }
            })
        }
    </script>
</head>
<body>
    <p>
        username:<input type="text" id="name" onblur="a1()">
        <span id="userInfo"></span>
    </p>
    <p>
        password:<input type="text" id="pwd" onblur="a2()">
        <span id="pwdInfo"></span>
    </p>
</body>
</html>
