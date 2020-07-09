<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.qidian.utils.Common" %>
<%@ page import="java.util.List" %>
<%@ page import="com.qidian.domain.*" %>


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
    	</style>
    	<script>
    			function getiframe(url){
				layui.use('layer', function(){
				  var layer = layui.layer;
				  layer.open({
				  type: 2,
				      title: '修改任务',
				      maxmin: true,
				      shadeClose: true, //点击遮罩关闭层
                      area : ['800px' , '500px'],
				  content: url //这里content是一个普通的String
				});
				})
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
                       var academic=document.getElementById("academic");
                       var majors=document.getElementById("majors");
                       majors.innerHTML = "<option selected disabled hidden ></option>";
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
                                       var ac = JSON.parse(result);
                                       for (var i = 0; i < ac.length; i++) {
                                           majors.options.add(new Option(ac[i].majorName, ac[i].majorId));
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
                           ajax.send("academicId=" + academicId);

                }

                function classesAjax() {
                    var majors=document.getElementById("majors");
                    var classes=document.getElementById("classes");
                    classes.innerHTML = "<option selected disabled hidden ></option>";
                    var index = majors.selectedIndex;
                    var majorId = majors.options[index].value; // 选中值

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


                function changepagesize() {
                    var pageisze=document.getElementById("pageisze");
                    var index=pageisze.selectedIndex;
                    var limit = pageisze.options[index].value; // 选中值
                    location.href="main.do?limit="+limit;
                }


                function deletetask(){
                    var checkbox = document.getElementsByName('taskId');
                    var page=document.getElementById("page").value;
                    var url="";
                        for (var i = 0; i < checkbox.length; i++) {
                            if (checkbox[i].checked) {
                                url+="taskId="+checkbox[i].value+"&";
                            }
                        }

                      url+="page="+page;
                    location.href="deleteTask.do?"+url;
                }


                function alertmessage(inform){
                    layui.use('layer', function() {
                        var layer = layui.layer;
                        layer.msg(inform);
                    });
                }

                function checkAll() {
                    var checkALl=document.getElementById("check_all_box");
                    var checkbox = document.getElementsByName('taskId');
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
        <script>
            layui.use('laydate', function(){
                var laydate = layui.laydate;

                //执行一个laydate实例
                laydate.render({
                    elem: '#time' //指定元素
                });
            });

        </script>
        <script>
            //Demo
            layui.use('form', function(){
                var form = layui.form;

                //监听提交
                form.on('submit(formDemo)', function(data){
                    layer.msg(JSON.stringify(data.field));
                    return false;
                });
            });
        </script>
	</head>
	<body style=" overflow-x:hidden;"  class="inner-container" onload="academicAjax()">
            <a style="margin-top: 5px;" href="javascript:history.back()" ><i class="layui-icon layui-icon-next" style="color: #666;"></i><cite>采集数据结果</cite></a>
            <a style="width: 10px;height: 10px" href="javascript:location.reload()"><i class="layui-icon layui-icon-refresh"></i> </a>

            <div style="border: solid #ccc 1px;margin-top:5px;padding-top:5px;padding-bottom: 5px;">
                <form method="post" action="account.do">
                    <input type="hidden" name="taskId" value="<%=request.getAttribute("taskId")%>">
                    <table>
                        <tr>
                            <td width="20%" height="20" align="left">
                                <div style="float: left;padding-top: 8px;">
                                    <label >所在学院:</label>
                                </div>
                                <div style="float: left;width: 200px;">
                                    <select name="academic" id="academic"  class="layui-input" onchange="majorAjax()" >
                                        <option selected disabled hidden ></option>
                                    </select>
                                </div>
                            </td>
                            <td width="20%" height="20" align="left">
                                <div style="float: left;padding-top: 8px;">
                                    <label >所在专业:</label>
                                </div>
                                <div style="float: left;width: 200px;">
                                    <select name="majors"  id="majors" class="layui-input" onchange="classesAjax()" >
                                        <option selected disabled hidden ></option>
                                    </select>
                                </div>
                            </td>
                            <td width="20%" height="20" align="left">
                                <div style="float: left;padding-top: 8px;">
                                    <label >所在班级:</label>
                                </div>
                                <div style="float: left;width: 200px;">
                                    <select name="className"  id="classes" class="layui-input" >
                                        <option selected disabled hidden ></option>
                                    </select>
                                </div>
                            </td>
                            <td width="20%" height="20" align="left">
                                <div style="float: left;padding-top: 8px;">
                                    <label >学生姓名:</label>
                                </div>
                                <div style="float: left;width: 200px;">
                                    <input name="name" class="layui-input" ></select>
                                </div>
                            </td>
                            <td width="20%" height="20" align="left">
                                <div style="float: left;padding-top: 8px;">
                                    <label >学生类型:</label>
                                </div>
                                <div style="float: left;width: 200px;">
                                    <select name="type"   class="layui-input" >
                                        <option selected disabled hidden ></option>
                                        <option value="本科生">本科生</option>
                                        <option value="研究生">研究生</option>
                                        <option value="博士生">博士生</option>
                                    </select>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <%--<td width="20%" height="20" align="center" >
                                <div style="float: left;padding-top: 8px;">
                                    <label >填写情况:</label>
                                </div>
                                <div style="float: left;width: 200px;">
                                    <select name="condition"   class="layui-input" >
                                        <option selected disabled hidden ></option>
                                        <option value="1">已填</option>
                                        <option value="0">未填</option>
                                        <option value="2">全部</option>
                                    </select>
                                </div>
                            </td>--%>
                            <td width="20%" height="20" align="center" >
                                <div style="float: left;padding-top: 8px;">
                                    <label >填写时间:</label>
                                </div>
                                <div style="float: left;width: 200px;">
                                    <input type="text" class="layui-input" name="answerDate" id="time" placeholder="yyyy-mm-dd" >
                                </div>
                              <%--  <div class="layui-form-item">
                                    <label class="layui-form-label">填写时间</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="time" placeholder="yyyy-mm-dd" >
                                    </div>
                             </div>--%>
                            </td>
                            <td width="5%" height="20" align="center" ><div style="float: left;padding-top: 25px;"><input type="submit" value="搜索" class="layui-btn" ></div></td>
            </tr>
        </table>
        </form>
        </div>

            <%
                Integer i= 0;
                PageBean<Student> pageBean= (PageBean<Student>) request.getAttribute("pageBean");
                Questionnaire questionnaire= (Questionnaire) request.getAttribute("questionnaire");
                List<Question> questions=questionnaire.getQuestionsList();
                request.setAttribute("questions",questions);
            %>

		<table class="layui-table" style="width: 1050px;" lay-skin="line">
            <thead>
		             <tr>
                         <td width="10%" height="20" align="center" >学生姓名：</td>
                         <c:forEach items="${questions}" var="question">
                             <td width="10%" height="20" align="center">${question.stem}</td>
                         </c:forEach>
                         <td width="10%" height="20" align="center">编辑</td>
                    </tr>

             </thead>
            <c:forEach  items="${pageBean.list}" var="student">

                                <c:if test="${fn:length(student.answers)>0}"> <%--已填--%>
                                        <tr>
                                        <td width="10%" height="20" align="center">${student.name}</td>
                                            <c:forEach items="${student.answers}" var="answer">
                                                <td width="10%" height="20" align="center">
                                                    <c:choose>
                                                        <c:when test="${answer.answer==null||answer.answer==''}">" "</c:when>
                                                        <c:otherwise>${answer.answer}</c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </c:forEach>

                                        <td width="10%" height="20" align="center">
                                          <button class="layui-btn layui-btn-normal">已签到</button>
                                        </td>
                                        </tr>
                                </c:if>

            </c:forEach>
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
