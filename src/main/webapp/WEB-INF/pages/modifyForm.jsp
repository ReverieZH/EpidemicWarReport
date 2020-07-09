<%@ page import="com.qidian.domain.Task" %>
<%@ page import="com.qidian.domain.Apply" %>
<%@ page import="com.qidian.domain.Form" %><%--
  Created by IntelliJ IDEA.
  User: rzh
  Date: 2020/6/27
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
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

        function modifyForm() {

            var formId=document.getElementById("formId").value;
            var formName=document.getElementById("formName").value;
            formName=trim(formName);
            if(formName==""){
                window.parent.alertmessage("请输入应用名称");
                return false;
            }
            var applyName=document.getElementById("applyName");
            var index=applyName.selectedIndex;
            var appName = applyName.options[index].value; // 选中值

            var statusop=document.getElementById("statusop");
            var index=statusop.selectedIndex;
            var status = statusop.options[index].value; // 选中值

            var link=document.getElementById("link").value;
            link=trim(link);
            if(link==""){
                window.parent.alertmessage("请输入超链接");
                return false;
            }
            ajaxRequest(formId,formName,appName,link,status);
            //location.href="modifyTask.do?"+"taskId="+taskId+"&taskName="+taskName+"&taskDesc="+taskDesc+"&status="+status;
        }

        function ajaxRequest(formId,formName,appName,link,status) {
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

            ajax.open("post", "modifyForm.do");
            ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            ajax.send("formId="+formId+"&formName="+formName+"&applyName="+appName+"&status="+status+"&link="+link);
        }

        function reset() {
            document.getElementById("formName").value="";
            document.getElementById("link").value="";
        }


        function Apply() {
            var applyName=document.getElementById("applyName");
            if(applyName.length==1) {
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
                            //获取响应内容
                            var result = eval(ajax.responseText);
                            for (var i = 0; i < result.length; i++) {
                                applyName.options.add(new Option(result[i], result[i]));
                            }
                            //处理响应内容
                            //获取元素对象
                        } else if (ajax.status == 404) {
                            alert("请求资源不存在")
                        } else if (ajax.status == 500) {
                            alert("服务器繁忙")
                        }
                    }
                }

                ajax.open("post", "../apply/getApplyName.do");
                ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                ajax.send();
            }
        }


    </script>
</head>
<body  onload="Apply()">

    <% Form form= (Form) request.getAttribute("form");%>
    <table class="table_context" width="100%">
        <tr>
            <input type="hidden" name="formId" id="formId" value="<%=form.getFormId()%>">
            <td width="10%" height="50" align="right">应用名称:<span style="color: red;">*</span></td>
            <td width="10%" height="50" align="left"><input id="formName" name="formName"  value="<%=form.getFormName()%>" type="text"></td>
        </tr>

        <tr>
            <td width="10%" height="50" align="right">所属类型:<span style="color: red;">*</span></td>
            <td width="10%" height="50" align="left"><select id="applyName" name="applyName" style="width: 100px"  >
                <option value="<%=form.getApplyName()%>" disabled selected hidden><%=form.getApplyName()%></option>
            </select> </td>
        </tr>
        <tr>
            <td width="10%" height="50" align="right">超链接:<span style="color: red;">*</span></td>
            <td width="10%" height="50" align="left"><input id="link" name="link" type="text"  value="<%=form.getLink()%>"></td>
        </tr>
        <tr>
            <td width="10%" height="50" align="right">使用状态:</td>
            <td width="10%" height="50" align="left"> <select id="statusop" name="statusop" lay-verify="" >
                <option value="<%=form.getStatus()%>" disabled selected hidden><%=form.getStatus().equals("1")?"使用中":"禁用中"%></option>
                <option value="1">启用</option>
                <option value="0">禁用</option>
            </select></td>
        </tr>
        <tr>
            <td height="50" colspan="2" align="center"><button class="layui-btn" onclick="modifyForm()" >保存数据</button><button class="layui-btn layui-btn-danger" onclick="reset()">取消编辑</button></td>
        </tr>
    </table>
</body>
</html>
