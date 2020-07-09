<% String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
				layui.use('laydate', function(){
				  var laydate = layui.laydate;
				  
				  //执行一个laydate实例
				  laydate.render({
				    elem: '#date' //指定元素
				  });
				});

				function addform() {
					var formName=document.getElementById("formName").value;
					var queDesc=document.getElementById("queDesc").value;
					var date=document.getElementById("date").value;
					var taskId=document.getElementById("taskId").value;
				//	window.parent.alertmessage("添加成功");
					addFromajaxRequest(formName,queDesc,date,taskId);
					// location.href="task/addTask.do?"+"taskName="+taskName+"&taskDesc="+taskDesc+"&date="+date;
				}

				function addFromajaxRequest( formName,queDesc,date,taskId) {
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
									window.parent.alertmessage("添加成功");
								}else{
									window.parent.alertmessage("添加失败");
								}
								//获取元素对象
							}else if(ajax.status==404){
								alert("请求资源不存在")
							}else if(ajax.status==500){
								alert("服务器繁忙")
							}
						}
					}

					ajax.open("post", "addQuestionnarie.do");
					ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
					ajax.send("queName="+formName+"&queDesc="+queDesc+"&date="+date+"&taskId="+taskId);
				}

				function reset() {
					document.getElementById("formName").value="";
					document.getElementById("queDesc").value="";
					document.getElementById("date").value="";
				}

		</script>
	</head>
	<body>
			<table class="table_context" width="100%">
				        <input type="hidden" id="taskId" name="taskId" value="<%=request.getAttribute("taskId")%>">
	                    <tr>
	                    	<td width="10%" height="50" align="right">表单名称:<span style="color: red;">*</span></td>
	                        <td width="10%" height="50" align="left"><input id="formName" name="formName" type="text"></td>
	                    </tr>
	   					<tr>
	                    	<td width="10%" height="50" align="right">表单描述:</td>
	                        <td width="10%" height="50" align="left"><input id="queDesc" name="queDesc" type="text"></td>
	                    </tr>   
	                    <tr>
	                    	<td width="10%" height="50" align="right">创建时间:</td>
	                        <td width="10%" height="50" align="left"> <input id="date" name="date" type="text" id="time" placeholder="yyyy-mm-dd" ></td>
	                    </tr>
	                    <tr>
							<td height="50" colspan="2" align="center"><button class="layui-btn" onclick="addform()">保存数据</button><button class="layui-btn layui-btn-danger">取消编辑</button></td>
	                    </tr>
			</table>
	</body>
</html>
