<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <h1>
        当前有
        <span>
            <%=this.getServletConfig().getServletContext().getAttribute("OnlineCount")%>
        </span>
        在线
    </h1>
</body>
</html>
