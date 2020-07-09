<%--
  Created by IntelliJ IDEA.
  User: rzh
  Date: 2020/7/9
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="text-align: center">
<% String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String name= String.valueOf(request.getAttribute("name"));
%>
  <img src="<%=basePath%><%=name%>"  style="width: 200px;height: 200px">
</body>
</html>
