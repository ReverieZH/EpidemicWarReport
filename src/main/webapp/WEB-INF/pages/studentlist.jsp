<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.qidian.utils.Common" %>
<%@ page import="com.qidian.domain.Task" %>
<%@ page import="com.qidian.domain.PageBean" %>
<%@ page import="com.qidian.domain.Student" %>
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

            .layui-table td{
                padding: 5px 5px;
            }
    	</style>
    	<script>
    			function getiframe(url){
				layui.use('layer', function(){
				  var layer = layui.layer;
				  layer.open({
				  type: 2,
				      maxmin: true,
				      shadeClose: true, //点击遮罩关闭层
                      area : ['800px' , '500px'],
				  content: url //这里content是一个普通的String
				});
				})
			    }

                function changeStatus(sno,Status) {
                    ajaxRequest(sno,Status);
                }

                function ajaxRequest( sno,status) {
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
                                //alert(result);
                                //处理响应内容
                                if(result){
                                    window.location.reload();
                                    //alert("正确");
                                }else{
                                    alert("修改失败");
                                }
                                //获取元素对象
                            }else if(ajax.status==404){
                                alert("请求资源不存在")
                            }else if(ajax.status==500){
                                alert("服务器繁忙")
                            }
                        }
                    }

                    ajax.open("post", "changeStatus.do");
                    ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
                    ajax.send("sno="+sno+"&status="+status);
                }


                function changepagesize() {
                    var pageisze=document.getElementById("pageisze");
                    var index=pageisze.selectedIndex;
                    var limit = pageisze.options[index].value; // 选中值
                    location.href="main.do?limit="+limit;
                }


                function deleteStudent(){
                    var checkbox = document.getElementsByName('sno');
                    var url="";
                        for (var i = 0; i < checkbox.length; i++) {
                            if (checkbox[i].checked) {
                                url+="sno="+checkbox[i].value+"&";
                            }
                        }
                    url=url.substring(0,url.length-1);
                    deleteajax(url);
                }
                function deleteajax(url) {
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
                                //alert(result);
                                //处理响应内容
                                if(result){

                                    window.location.reload();
                                    alertmessage("删除成功");
                                    //alert("正确");
                                }else{
                                    alertmessage("删除失败");
                                }
                                //获取元素对象
                            }else if(ajax.status==404){
                                alert("请求资源不存在")
                            }else if(ajax.status==500){
                                alert("服务器繁忙")
                            }
                        }
                    }

                    ajax.open("get", "deleteStudent.do?"+url);
                    ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
                    ajax.send();
                }

                function deleteOneStudent(sno){
                    deletajaxRequest(sno);
                }

                function deletajaxRequest( sno ) {
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
                                //alert(result);
                                //处理响应内容
                                if(result){
                                    window.location.reload();
                                    //alert("正确");
                                }else{
                                    alert("删除失败");
                                }
                                //获取元素对象
                            }else if(ajax.status==404){
                                alert("请求资源不存在")
                            }else if(ajax.status==500){
                                alert("服务器繁忙")
                            }
                        }
                    }

                    ajax.open("post", "delete.do");
                    ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
                    ajax.send("sno="+sno);
                }

                function alertmessage(inform){
                    layui.use('layer', function() {
                        var layer = layui.layer;
                        layer.msg(inform);
                    });
                }

                function checkAll() {
                    var checkALl=document.getElementById("check_all_box");
                    var checkbox = document.getElementsByName('sno');
                    if(checkALl.checked) {
                        for (var i = 0; i < checkbox.length; i++) {
                            if (!checkbox[i].checked) {
                                checkbox[i].checked = true;
                            }
                        }
                    }else{
                        for (var i = 0; i < checkbox.length; i++) {
                            if (checkbox[i].checked) {
                                checkbox[i].checked = false;
                            }
                        }
                    }
                }
    	</script>
    	
	</head>
	<body style=" overflow-x:hidden;"  class="inner-container">
		<div style="color: #666;">
            <a style="margin-top: 5px;" href="javascript:history.back()" ><i class="layui-icon layui-icon-next" style="color: #666;"></i><cite>学生数据管理</cite></a>
            <a style="width: 10px;height: 10px" href="javascript:location.reload()"><i class="layui-icon layui-icon-refresh"></i> </a>
            <button style="float: right;" class="layui-btn layui-btn-primary" onclick="deleteStudent()">删除学生</button>
		<button style="float: right;" class="layui-btn layui-btn-primary" onclick="getiframe('getaddStudent.do')">添加学生</button>
		
		<table class="layui-table" style="width: 1050px;" lay-skin="row">
            <thead>
            <tr>
                <td rowspan="2" width="8%" align="center" ><input type="checkbox" onchange="checkAll()" id="check_all_box">全选</td>
                <td colspan="4"  align="center" >学生基本信息</td>
                <td colspan="4" align="center" ></td>
                <td rowspan="2" width="10px"  align="center">操作</td>
            </tr>
            <tr>
                <td width="5%" height="5" align="center" >学号</td>
                <td width="7%" height="5" align="center" >姓名</td>
                <td width="14%" height="5" align="center" >学院</td>
                <td width="8%" height="5" align="center">班级</td>
                <td  width="8%" height="5" align="center" >籍贯</td>
                <td width="5%" height="5" align="center" >性别</td>
                <td width="5%" height="5" align="center" >年级</td>
                <td width="5%" height="5" align="center">联系电话</td>
            </tr>
            </thead>
            <c:set var="i" value="${pageBean.startIndex}" scope="request"></c:set>
            <%
                Integer i= 0;
                PageBean<Student> pageBean= (PageBean<Student>) request.getAttribute("pageBean");
            %>
            <input type="hidden" id="page" value="<%=pageBean==null?1:pageBean.getPageNum()%>">
            <c:forEach begin="0" end="${pageBean.pageSize}" items="${pageBean.list}" var="task">
                <c:if test="${i<pageBean.totalRecord}">
                    <tr >
                        <td width="6%"  height="5" align="center" ><input  type="checkbox" name="sno" value="<%=pageBean.getList().get(i).getSno()%>"></td>
                        <td width="5%" height="5" align="center" ><a onclick="getiframe('getStudent.do?sno=<%=pageBean.getList().get(i).getSno()%>')"><%=pageBean.getList().get(i).getSno()%></a></td>
                        <td width="7%" height="5" align="center" ><a onclick="getiframe('getStudent.do?sno=<%=pageBean.getList().get(i).getSno()%>')"><%=pageBean.getList().get(i).getName()%></a></td>
                        <td width="15%" height="5" align="center" ><%=pageBean.getList().get(i).getAcademicName()%></td>
                        <td width="8%" height="5" align="center"><%=pageBean.getList().get(i).getClassesName()%></td>
                        <td  width="8%" height="5" align="center" ><%=pageBean.getList().get(i).getOrigin()%></td>
                        <td width="5%" height="5" align="center" ><%=pageBean.getList().get(i).getSex()%></td>
                        <td width="5%" height="5" align="center" ><%=pageBean.getList().get(i).getGrade()%></td>
                        <td width="5%" height="5" align="center"><%=pageBean.getList().get(i).getPhone()%></td>
                        <td align="center"><button class="layui-btn" onclick="getiframe('getmodifyStudent.do?sno=<%=pageBean.getList().get(i).getSno()%>')">编辑</button> <button class="layui-btn layui-btn-warm" onclick="changeStatus('<%=pageBean.getList().get(i).getSno()%>','0')" >禁用</button> <button class="layui-btn layui-btn-danger" onclick="deleteOneStudent('<%=pageBean.getList().get(i).getSno()%>')">删除</button></td>
                    </tr>
                </c:if>
                <c:set var="i" value="${i+1}"></c:set>
                <% i++; %>
            </c:forEach>
            <c:remove var="i" scope="request"></c:remove>
		</table>
		  &nbsp;共<span><%=(pageBean==null)?0:pageBean.getTotalRecord()%></span>条&nbsp; 每页<select id="pageisze" name="pagesize" lay-verify="" onchange="changepagesize()">
                    <option value="" disabled selected hidden>${pageBean.pageSize}</option>
                    <option value="10">10</option>
                    <option value="20">20</option>
                    <option value="30">30</option>
                    <option value="40">40</option>
                    <option value="50">50</option>
                    <option value="60">60</option>
                   </select>条
            &nbsp;
            <c:choose>
                    <c:when test="${pageBean.pageNum<pageBean.totalPage}">
                        <a class="layui-btn layui-btn-primary" href="main.do?page=${pageBean.pageNum+1}">下一页</a>&nbsp;&nbsp;
                        <a class="layui-btn layui-btn-primary" href="main.do?page=${pageBean.totalPage}">最后一页</a>
                    </c:when>
                    <c:otherwise>
                        下一页&nbsp;&nbsp;最后一页&nbsp;
                    </c:otherwise>
             </c:choose>

							
		<!--<table class="table_context" width="100%">
         <c:remove var="pageBean" scope="session"/>

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
