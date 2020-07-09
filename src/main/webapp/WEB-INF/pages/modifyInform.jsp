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
            height: 25px;
        }

    </style>


    <script>
        //去掉字串左边的空格
        function lTrim(str) {
            if (str.charAt(0) == " " || str.charAt(0) == "　") {
                str = str.slice(1);
                str = lTrim(str);
            }
            return str;
        }

        //去掉字串右边的空格
        function rTrim(str) {
            var iLength;
            iLength = str.length;
            if (str.charAt(iLength - 1) == " " || str.charAt(iLength - 1) == "　") {
                str = str.slice(0, iLength - 1);
                str = rTrim(str);
            }
            return str;
        }

        //去掉字串两边的空格
        function trim(str) {
            return lTrim(rTrim(str));
        }
        function modifyInform() {
            var username=document.getElementById("username").value;
            username=trim(username);
            if(username==""){
                window.parent.alertmessage("请输入账户名");
                return false;
            }
            var password=document.getElementById("password").value;
            password=trim(password);
            if(password==""){
                window.parent.alertmessage("请输入账号密码");
                return false;
            }
            var name=document.getElementById("name").value;
            name=trim(name);
            if(name==""){
                window.parent.alertmessage("请输入姓名");
                return false;
            }
            ajaxRequest(username, password, name);


        }

        function ajaxRequest(username, password, name) {
            var index = parent.layer.getFrameIndex(window.name);
            var ajax;
            if(window.XMLHttpRequest){//火狐
                ajax=new XMLHttpRequest();
            }else if(window.ActiveXObject){//ie
                ajax=new ActiveXObject("Msxml2.XMLHTTP");
            }
            //复写onreadystatechange函数
            ajax.onreadystatechange=function(){
                //判断Ajax状态码
                if(ajax.readyState==4){
                    //判断响应状态吗
                    if(ajax.status==200){
                        //获取响应内容
                        var result=eval(ajax.responseText);
                        //处理响应内容
                        if(result){
                            window.parent.alertmessage("修改成功");
                            parent.layer.close(index);
                        }else{
                            window.parent.alertmessage("修改失败");
                        }
                        //获取元素对象
                    }else if(ajax.status==404){
                        alert("请求资源不存在")
                    }else if(ajax.status==500){
                        alert("服务器繁忙")
                    }
                }
            }

            ajax.open("post", "modifyInform.do");
            ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            username, password, name, role, academicName,status
            ajax.send("username="+username+"&password="+password+"&name="+name);
        }

        function reset() {
            document.getElementById("password").value="";
            document.getElementById("name").value="";
        }

    </script>
</head>
<body  >
  <% Operator operator= (Operator) request.getAttribute("operator");%>
    <table class="table_context" width="100%">
        <input id="username" name="username" type="hidden" value="<%=operator.getUsername()%>">
        <tr>
            <td width="10%" height="50" align="right">账号密码:<span style="color: red;">*</span></td>
            <td width="10%" height="50" align="left"><input id="password" name="password"  value="<%=operator.getPassword()%>"></td>
        </tr>
        <tr>
            <td width="10%" height="50" align="right">姓名:<span style="color: red;">*</span></td>
            <td width="5%" height="50" align="left"><input id="name" name="name" width="50px" type="text" value="<%=operator.getName()%>"></td>
        </tr>
        <tr>
            <td height="50" colspan="2" align="center"><button class="layui-btn" onclick="modifyInform('<%=operator.getUsername()%>')" >保存数据</button><button class="layui-btn layui-btn-danger" onclick="reset()">取消编辑</button></td>
        </tr>
    </table>
</body>
</html>
