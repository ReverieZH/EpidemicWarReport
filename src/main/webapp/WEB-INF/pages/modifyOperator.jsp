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
        function modifyOperator() {
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
            var roleop=document.getElementById("roleop");
            var index = roleop.selectedIndex;
            var role= roleop.options[index].value;
            role=trim(role);
            if(role==""){
                window.parent.alertmessage("请选择角色");
                return false;
            }
            var academic=document.getElementById("academic");
            var index = academic.selectedIndex;
            var academicName = academic.options[index].text; // 选中值
            var academicId= academic.options[index].value;
            if(academicName==""){
                window.parent.alertmessage("请选择学院");
                return false;
            }
            var statusop=document.getElementById("statusop");
            var index=statusop.selectedIndex;
            var status = statusop.options[index].value; // 选中值

            ajaxRequest(username, password, name, role, academicName,status);


        }

        function ajaxRequest(username, password, name, role, academicName,status) {
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

            ajax.open("post", "modifyOperator.do");
            ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            username, password, name, role, academicName,status
            ajax.send("username="+username+"&password="+password+"&name="+name+"&role="+role+"&academicName="+academicName+"&validty="+status);
        }

        function reset() {
            document.getElementById("classesName").value="";
        }

        function academicAjax() {
            var academic=document.getElementById("academic");
            if(academic.length==1) {
                var ajax;
                if (window.XMLHttpRequest) {//火狐
                    ajax = new XMLHttpRequest();
                } else if (window.ActiveXObject) {//ie
                    ajax = new ActiveXObject("Msxml2.XMLHTTP");
                }
                //复写onreadystatechange函数
                ajax.onreadystatechange = function () {
                    //判断Ajax状态码
                    if (ajax.readyState == 4) {
                        //判断响应状态吗
                        if (ajax.status == 200) {
                            var result = ajax.responseText;
                            var ac=JSON.parse(result);
                            for(var i=0;i<ac.length;i++){
                                academic.options.add(new Option(ac[i].academicName,ac[i].academicId));
                            }
                            //获取元素对象
                        } else if (ajax.status == 404) {
                            alert("请求资源不存在")
                        } else if (ajax.status == 500) {
                            alert("服务器繁忙")
                        }
                    }
                }

                ajax.open("post", "../academic/getAcademics.do");
                ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                ajax.send();
            }
        }

        function majorAjax() {
            var major=document.getElementById("major");
            major.innerHTML = "<option selected disabled hidden ></option>";
            var academic=document.getElementById("academic");
            var index = academic.selectedIndex;
            var academicId = academic.options[index].value; // 选中值
            var ajax;
            if (window.XMLHttpRequest) {//火狐
                ajax = new XMLHttpRequest();
            } else if (window.ActiveXObject) {//ie
                ajax = new ActiveXObject("Msxml2.XMLHTTP");
            }
            //复写onreadystatechange函数
            ajax.onreadystatechange = function () {
                //判断Ajax状态码
                if (ajax.readyState == 4) {
                    //判断响应状态吗
                    if (ajax.status == 200) {
                        var result = ajax.responseText;
                        var ac=JSON.parse(result);
                        for(var i=0;i<ac.length;i++){
                            major.options.add(new Option(ac[i].majorName,ac[i].majorId));
                        }
                        //获取元素对象
                    } else if (ajax.status == 404) {
                        alert("请求资源不存在")
                    } else if (ajax.status == 500) {
                        alert("服务器繁忙")
                    }
                }
            }

            ajax.open("post", "../major/getMajors.do");
            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            ajax.send("academicId="+academicId);
        }

        function classesAjax() {
            var major=document.getElementById("major");
            var classes=document.getElementById("classes");
            classes.innerHTML = "<option selected disabled hidden ></option>";
            var index = major.selectedIndex;
            var majorId = major.options[index].value; // 选中值

            var ajax;
            if (window.XMLHttpRequest) {//火狐
                ajax = new XMLHttpRequest();
            } else if (window.ActiveXObject) {//ie
                ajax = new ActiveXObject("Msxml2.XMLHTTP");
            }
            //复写onreadystatechange函数
            ajax.onreadystatechange = function () {
                //判断Ajax状态码
                if (ajax.readyState == 4) {
                    //判断响应状态吗
                    if (ajax.status == 200) {
                        var result = ajax.responseText;
                        var ac = JSON.parse(result);
                        for (var i = 0; i < ac.length; i++) {
                            classes.options.add(new Option(ac[i].classesName, ac[i].classessId));
                        }
                        //获取元素对象
                    } else if (ajax.status == 404) {
                        alert("请求资源不存在")
                    } else if (ajax.status == 500) {
                        alert("服务器繁忙")
                    }
                }
            }

            ajax.open("post", "../classes/getClasses.do");
            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            ajax.send("majorId=" + majorId);

        }

        function getGrade() {
            var myDate= new Date();
            var startYear=myDate.getFullYear()-5;//起始年份
            var endYear=myDate.getFullYear();//结束年份
            var gradeop=document.getElementById('gradeop');
            for (var i=endYear;i>=startYear;i--)
            {
                gradeop.options.add(new Option(i,i));
            }
        }
    </script>
</head>
<body   onload="academicAjax()">
  <% Operator operator= (Operator) request.getAttribute("operator");%>
    <table class="table_context" width="100%">
        <tr>
            <td width="10%" height="50" align="right">账户名:<span style="color: red;">*</span></td>
            <td width="10%" height="50" align="left"><input id="username" name="username" type="text" value="<%=operator.getUsername()%>"></td>
        </tr>
        <tr>
            <td width="10%" height="50" align="right">账号密码:<span style="color: red;">*</span></td>
            <td width="10%" height="50" align="left"><input id="password" name="password"  value="<%=operator.getPassword()%>"></td>
        </tr>
        <tr>
            <td width="10%" height="50" align="right">姓名:<span style="color: red;">*</span></td>
            <td width="10%" height="50" align="left"><input id="name" name="name" type="text" value="<%=operator.getName()%>"></td>
        </tr>
        <tr>
            <td width="10%" height="50" align="right">角色:<span style="color: red;">*</span></td>
            <td width="10%" height="50" align="left"><select name="roleop" id="roleop" style="height: 25px ;width: 200px"  >
                <option selected disabled hidden value="<%=operator.getRole()%>"><%=operator.getRoleStr()%></option>
                <option value="0" >系统管理员</option>
                <option value="1" >学生处管理人员</option>
                <option value="2">学院管理人员</option>
                <option value="3">辅导员</option>
            </select></td>
        </tr>
        <tr>
            <td width="10%" height="50" align="right">所属学院:<span style="color: red;">*</span></td>
            <td width="10%" height="50" align="left"><select name="academic" id="academic" style="height: 25px ;width: 200px" >
                <option selected disabled hidden value="<%=operator.getAcademicName()%>"><%=operator.getAcademicName()%></option>
            </select></td>
        </tr>
        <tr>
            <td width="10%" height="50" align="right">使用状态:</td>
            <td width="10%" height="50" align="left"> <select id="statusop" name="statusop" lay-verify="" >
                <option value="<%=operator.getValidty()%>" disabled selected hidden><%=operator.getValidty().equals("1")?"使用中":"禁用中"%></option>
                <option value="1">启用</option>
                <option value="0">禁用</option>
            </select></td>
        </tr>
        <tr>
            <td height="50" colspan="2" align="center"><button class="layui-btn" onclick="modifyOperator('<%=operator.getUsername()%>')" >保存数据</button><button class="layui-btn layui-btn-danger" onclick="reset()">取消编辑</button></td>
        </tr>
    </table>
</body>
</html>
