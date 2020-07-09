<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.qidian.domain.Apply" %>
<%@ page import="java.util.List" %>
<%@ page import="com.qidian.domain.Form" %><%--
  Created by IntelliJ IDEA.
  User: rzh
  Date: 2020/7/3
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>首页门户</title>
    <link rel="stylesheet" href="./layui/css/layui.css">
    <script src="./layui/layui.js"></script>
    <style >

        #top{
            background-color: #01AAED;
            width: 100%;
            height: 200px;
            color: #FFFFFF;
        }

        .bar{
            width: 100%;
            height: 30px;
            background-color: #d2d2d2;

        }
        .apply{
            margin-left: 20px;
            padding-top: 5px;
        }
        .table_context{
            width: 100%;
        }
        .table_context td{
            padding: 10px 10px ;
            border: 1px solid #CCCCCC;
        }
    </style>
</head>
<body>
 <%
  Integer number= 0;
  List<Apply> group=(List<Apply>) request.getAttribute("group");
String name=String.valueOf(request.getSession().getAttribute("name"));
String sno=String.valueOf(request.getSession().getAttribute("sno"));
%>
<div id="top" >
    <div  style="margin-left: 50px;padding-top: 50px;">
        <h3>陕西科技大学</h3><br>
        <p>欢迎你：<%=name%>(学号:<span id="sno"><%=sno%></span>)</p>
    </div>
</div>
<div >

    <c:forEach  items="${group}" var="apply">
        <div class="bar">
            <div class="apply">
                <i class="layui-icon layui-icon-cols" style="font-size: 18px; color: #1E9FFF;"></i> ${apply.applyName}
            </div>
        </div>
        <div class="content">
            <table class="table_context" >
                <tr>
                <%
                    List<Form> formList=group.get(number).getFormList();
                    int sum=(formList.size()/4+1)*4;
                    System.out.println(sum);
                    int column=1;
                    for(int i=0;i<sum;i++) {

                        if(i>formList.size()-1){%>
                            <td width="10%" align="center"></td>
                         <%}else {              Form form=formList.get(i);
                         %>
                            <td width="10%" align="center"><a href="<%=form.getLink()%>" class="layui-btn layui-btn-primary"><%=form.getFormName()%></a></td>
                        <% } %>
                         <% if(column%4==0) {
                            column=1;
                            %>
                               </tr>
                                <tr>
                         <%continue;}
                         column++;%>
                <%}
                  number++;%>
                             </tr>
            </table>
    </c:forEach>

</div>

</html>

