
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
				layui.use('laydate', function(){
				  var laydate = layui.laydate;
				  
				  //执行一个laydate实例
				  laydate.render({
				    elem: '#date' //指定元素
				  });
				});

				function addQuestion() {

					var typeselect=document.getElementById("typeselect");
					if(typeselect.value==""){
						window.parent.alertmessage("请选择字段类型");
						return false;
					}
					var index=typeselect.selectedIndex;
					var type = typeselect.options[index].value; // 选中值
					var stem=document.getElementById("stem").value;
					if(stem==""){
						window.parent.alertmessage("请输入字段名称");
						return false;
					}
					var questionnaireId=document.getElementById("questionnaireId").value;


					if(type==1){
						var option1=document.getElementById("option1").value;
						var option2=document.getElementById("option2").value;
						var option3=document.getElementById("option3").value;
						var option4=document.getElementById("option4").value;
						var option5=document.getElementById("option5").value;
						addQuestionAjax( type,stem,option1,option2,option3,option4,option5,questionnaireId);
					}else {
						addQuestionAjax(type, stem,"", "", "", "", "", questionnaireId);
					}
				//	window.parent.alertmessage("添加成功");

					// location.href="task/addTask.do?"+"taskName="+taskName+"&taskDesc="+taskDesc+"&date="+date;
				}

				function addQuestionAjax( type,stem,option1,option2,option3,option4,option5,questionnaireId) {
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

					ajax.open("post", "addQuestion.do");
					ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
					ajax.send("type="+type+"&stem="+stem+"&option1="+option1+"&option2="+option2+"&option3="+option3+"&option4="+option4+"&option5="+option5+"&questionnaireId="+questionnaireId+"&validity=1");
				}

				function reset() {
					document.getElementById("formName").value="";
					document.getElementById("queDesc").value="";
					document.getElementById("date").value="";
				}

				function changeType() {
					var typeselect=document.getElementById("typeselect");
					var index=typeselect.selectedIndex;
					var type = typeselect.options[index].value; // 选中值
					var optionTable=document.getElementById("optionTable");
					if(type==1){
						optionTable.style.display="block";
					}else {
						optionTable.style.display="none";
					}
					//location.href="main.do?type="+limit;
				}
		</script>
	</head>
	<body>
			<table class="table_context" width="100%">
				        <input type="hidden" id="questionnaireId" name="questionnaireId" value="<%=request.getAttribute("questionnaireId")%>">
						<tr>
							<td width="10%" height="50" align="right">字段类型:<span style="color: red;">*</span></td>
							<td width="10%" height="50" align="left"><select id="typeselect" name="typeselect" lay-verify="" onchange="changeType()">
								<option value="" disabled selected hidden>选择字段类型</option>
								<option value="0">输入框</option>
								<option value="1">下拉框</option>
								<option value="2">地址框</option>
								<option value="3">定位框</option>
							</select></td>
						</tr>
						<tr>
	                    	<td width="10%" height="50" align="right">字段名称:<span style="color: red;">*</span></td>
	                        <td width="10%" height="50" align="left"><input id="stem" name="stem" type="text"></td>
	                    </tr>
						</table>
								   <table id="optionTable" class="table_context" width="100%" style="display:none;">
									   <tr>
										   <td width="10%" height="50" align="right">选项1设置:</td>
										   <td width="10%" height="50" align="left"><input id="option1" name="option1" type="text"></td>
									   </tr>
									   <tr>
										   <td width="10%" height="50" align="right">选项2设置:</td>
										   <td width="10%" height="50" align="left"><input id="option2" name="option2" type="text"></td>
									   </tr>
									   <tr>
										   <td width="10%" height="50" align="right">选项3设置:</td>
										   <td width="10%" height="50" align="left"><input id="option3" name="option3" type="text"></td>
									   </tr>
									   <tr>
										   <td width="10%" height="50" align="right">选项4设置:</td>
										   <td width="10%" height="50" align="left"><input id="option4" name="option4" type="text"></td>
									   </tr>
									   <tr>
										   <td width="10%" height="50" align="right">选项5设置:</td>
										   <td width="10%" height="50" align="left"><input id="option5" name="option5" type="text"></td>
									   </tr>
								   </table>
									<table class="table_context" width="100%">
												<tr>
													<td height="50" colspan="2" align="center"><button class="layui-btn" onclick="addQuestion()">保存数据</button><button class="layui-btn layui-btn-danger">取消编辑</button></td>
												</tr>
									</table>
			</table>
	</body>
</html>
