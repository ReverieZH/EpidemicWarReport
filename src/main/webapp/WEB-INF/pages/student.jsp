<%@ page import="com.qidian.domain.*" %><%--
  Created by IntelliJ IDEA.
  User: rzh
  Date: 2020/6/27
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<% String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="<%=basePath%>/layui/css/layui.css">
    <script src="<%=basePath%>/layui/layui.js"></script>
    <style>
        .table_context{
            font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
            width:750px;
            /*border-collapse:collapse;*/

        }

        input[type="text"] {
            margin-left: 5px;
            width: 400px;
            height: 25px;
        }

    </style>

</head>
<% Student student= (Student) request.getAttribute("student");%>
<body >

    <table class="table_context" width="100%">
        <tr>
            <td width="10%" height="50" align="right">学号:<span style="color: red;">*</span></td>
            <td width="10%" height="50" align="left"><input id="sno" name="sno" type="text" value="<%=student.getSno()%>"></td>
        </tr>
        <tr>
            <td width="10%" height="50" align="right">姓名:<span style="color: red;">*</span></td>
            <td width="10%" height="50" align="left"><input id="name" name="name" type="text" value="<%=student.getName()%>"></td>
        </tr>
        <tr>
            <td width="10%" height="50" align="right">账号密码:<span style="color: red;">*</span></td>
            <td width="10%" height="50" align="left"><input id="password" name="password" type="text" value="<%=student.getPassword()%>"></td>
        </tr>
        <tr>
            <td width="10%" height="50" align="right">性别:<span style="color: red;">*</span></td>
            <td width="10%" height="50" align="left">
                <input id="sex" name="sex" type="text" value="<%=student.getSex()%>">
            </td>
        </tr>
        <tr>
            <td width="10%" height="50" align="right">年级:<span style="color: red;">*</span></td>
            <td width="10%" height="50" align="left">
                <input id="grade" name="grade" type="text" value="<%=student.getGrade()%>">
            </td>
        </tr>
        <tr>
            <td width="10%" height="50" align="right">学生类型:<span style="color: red;">*</span></td>
            <td width="10%" height="50" align="left">
                <input id="type" name="type" type="text" value="<%=student.getType()%>">
            </td>
        </tr>
        <tr>
            <td width="10%" height="50" align="right">所属学院:<span style="color: red;">*</span></td>
            <td width="10%" height="50" align="left">
                <input id="academic" name="academic" type="text" value="<%=student.getAcademicName()%>">
            </td>
        </tr>
        <tr>
            <td width="10%" height="50" align="right">所属专业:<span style="color: red;">*</span></td>
            <td width="10%" height="50" align="left">
                <input id="major" name="major" type="text" value="<%=student.getMajorName()%>">
            </td>
        </tr>
        <tr>
            <td width="10%" height="50" align="right">所属班级:<span style="color: red;">*</span></td>
            <td width="10%" height="50" align="left">
                <input id="classes" name="classes" type="text" value="<%=student.getClassesName()%>">
            </td>
        </tr>
        <tr>
            <td width="10%" height="50" align="right">宿舍号:<span style="color: red;">*</span></td>
            <td width="10%" height="50" align="left"><input id="dormitory" name="dormitory" type="text" value="<%=student.getDormitory()%>"></td>
        </tr>
        <tr>
            <td width="10%" height="50" align="right">生源地:<span style="color: red;">*</span></td>
            <td width="10%" height="50" align="left"><input id="origin" name="origin" type="text" value="<%=student.getOrigin()%>"></td>
        </tr>
        <tr>
            <td width="10%" height="50" align="right">地址:<span style="color: red;">*</span></td>
            <td width="10%" height="50" align="left"><input id="address" name="address" type="text" value="<%=student.getAddress()%>"></td>
        </tr>
        <tr>
            <td width="10%" height="50" align="right">联系电话:<span style="color: red;">*</span></td>
            <td width="10%" height="50" align="left"><input id="phone" name="phone" type="text"  maxlength="11" value="<%=student.getPhone()%>"></td>
        </tr>
    </table>
</body>
</html>
