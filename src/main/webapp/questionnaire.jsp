<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.qidian.utils.Common" %>
<%@ page import="com.qidian.domain.Task" %>
<%@ page import="com.qidian.domain.PageBean" %>
<%@ page import="com.qidian.domain.Questionnaire" %>
<%@ page import="com.qidian.domain.Question" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="../layui/css/layui.css">
    <script src="../layui/layui.js"></script>
    <style>
        /*.table_context{
            font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
            width:1050px;
            border-collapse:collapse;
        }

        .table_context td,.table_context th{
            font-size:1em;
            padding:3px 7px 2px 7px;
        }
        .table_context th{
            font-size:10px;
            text-align:center;
            padding-top:5px;
            padding-bottom:4px;
            background-color:#ccc;
            color:#ffffff;
        }
        .table_context td{
            border-bottom: solid #ccc 1px;
        }*/
        input[type="checkbox"] {
            width: 15px;
            height: 15px;
            display: inline-block;
            text-align: center;
            vertical-align: middle;
            line-height: 18px;
            margin-right: 10px;
            position: relative;
        }

        input[type="checkbox"]::before {
            content: "";
            position: absolute;
            top: 0;
            left: 0;
            background: #fff;
            width: 100%;
            height: 100%;
            border: 1px solid #d9d9d9;
        }

        input[type="checkbox"]:checked::before {
            content: "\2713";
            background-color: #fff;
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            border: 1px solid #7D7D7D;
            color: #7D7D7D;
            font-size: 20px;
            font-weight: bold;
        }

    </style>

    <style>
        .inner-container {
            position: absolute; left: 0;
            overflow-x: hidden;
            overflow-y: scroll;
        }
        /* for Chrome 只针对谷歌浏览器*/
        .inner-container::-webkit-scrollbar {
            display: none;
        }
    </style>
    <script>



    </script>

</head>
<body style=" overflow-x:hidden; text-align: center"  class="inner-container">
<div style="color: #666;">
    <form method="post" action="../answer/main.do">

    <table class="layui-table" style="width: 100%;" lay-skin="line">
        <%
            Integer i= 0;
            Questionnaire questionnaire= (Questionnaire) request.getAttribute("questionnaire");
        %>
            <input type="hidden" id="questionnaireId" name="questionnaireId" value="${questionnaire.questionnaireId}">
             <h3>${questionnaire.queName}</h3>
            <tr>
                <td width="10%" height="50" align="right">输入学号</td>
                <td width="10%" height="50" align="left"><input name="sno"></td>
            </tr>
            <c:forEach items="${questionnaire.questionsList}" var="question">
                    <c:if test="${question.validity=='1'}">
                    <tr>
                        <td width="10%" height="50" align="right">
                             ${question.stem}
                        </td>
                        <td width="5%" height="50" align="left" style="color: green" >
                                <c:choose>
                                    <c:when test="${question.type=='0'}"><input name=" ${question.stem}"></c:when>
                                    <c:when test="${question.type=='1'}">
                                        <select name=" ${question.stem}" lay-verify="" >
                                            <c:forEach items="${question.optionlist}" var="option">
                                                <option value="${option}">${option}</option>
                                            </c:forEach>
                                        </select>
                                    </c:when>
                                    <c:when test="${question.type=='2'}"><input name=" ${question.stem}r"></c:when>
                                    <c:when test="${question.type=='3'}"><input name=" ${question.stem}"></c:when>
                            </c:choose>
                        </td>
                    </tr>
                    </c:if>
                <% i++; %>
            </c:forEach>

    </table>
    <input type="submit" class="layui-btn layui-btn-normal" value="提交">

    </form>
    &nbsp;

    <!--<table class="table_context" width="100%">

		</table>-->
</div>
</body>
<%!
    String displayNumber(String number){
        if(number!=null)
            return "<a title='" + number + "'>" + Common.getFixedLengthString(number, 7) + "</a>";
        else
            return "&nbsp;";
    }



    String displayWorkspace(String workspace){
        if(workspace!=null)
            return "<a title='" + workspace + "'>" + Common.getFixedLengthString(workspace, 8) + "</a>";
        else
            return "&nbsp;";
    }

    String displayAddress(String address){
        if(address!=null)
            return "<a title='" + address + "'>" + Common.getFixedLengthString(address, 5) + "</a>";
        else
            return "&nbsp;";
    }
    String displayEmail(String email){
        if(email!=null)
            return "<a title='" + email + "'>" + Common.getFixedLengthString(email, 6) + "</a>";
        else
            return "&nbsp;";
    }
    String getNotNullString(Integer age){
        if(age==null){
            return "";
        }else{
            return String.valueOf(age);
        }
    }
%>
</html>
