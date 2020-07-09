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
<%--		<link rel="stylesheet" href="../../layui/css/layui.css">--%>
<%--		<script src="../../layui/layui.js"></script>--%>
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

				layui.use('laydate', function(){
				  var laydate = layui.laydate;
				  //执行一个laydate实例
				  laydate.render({
				    elem: '#date' //指定元素
				  });
				});

				function addQrForm() {
					var qrformName=document.getElementById("qrformName").value;
					qrformName=trim(qrformName);
					if(qrformName==""){
						window.parent.alertmessage("请输入任务名称");
						return false;
					}
					var qrformDesc=document.getElementById("qrformDesc").value;
					qrformDesc=trim(qrformDesc);
					if(qrformDesc==""){
						window.parent.alertmessage("请输入任务描述");
						return false;
					}
					var task=document.getElementById("task");
					var index = task.selectedIndex;
					var taskId= task.options[index].value;
					if(taskId==""){
						window.parent.alertmessage("请选择所属任务");
						return false;
					}
					var questionnaire=document.getElementById("questionnaire");
					var index = questionnaire.selectedIndex;
					var questionnaireId= questionnaire.options[index].value;
					if(questionnaireId==""){
						window.parent.alertmessage("请选择所属问卷");
						return false;
					}
					var date=document.getElementById("date").value;
					if(date==""){
						window.parent.alertmessage("请选择时间");
						return false;
					}
				//	window.parent.alertmessage("添加成功");
					ajaxRequest(qrformName,qrformDesc,questionnaireId,date);
					// location.href="task/addTask.do?"+"taskName="+taskName+"&taskDesc="+taskDesc+"&date="+date;


				}

				function ajaxRequest( qrformName,qrformDesc,questionnaireId,date) {
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
									window.parent.alertmessage("添加成功");
									parent.layer.close(index);
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

					ajax.open("post", "addQrForm.do");
					ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
					ajax.send("qrformName="+qrformName+"&qrformDesc="+qrformDesc+"&questionnaireId="+questionnaireId+"&status=1");
				}

				function reset() {
					document.getElementById("classesName").value="";
				}

			function taskAjax() {
				var task=document.getElementById("task");
				if(task.length==1) {
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
									task.options.add(new Option(ac[i].taskName,ac[i].taskId));
								}
								//获取元素对象
							} else if (ajax.status == 404) {
								alert("请求资源不存在")
							} else if (ajax.status == 500) {
								alert("服务器繁忙")
							}
						}
					}

					ajax.open("post", "../task/getTasks.do");
					ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
					ajax.send();
				}
			}

			function questionnaireAjax() {
				   var questionnaire=document.getElementById("questionnaire");
				     questionnaire.innerHTML = "<option selected disabled hidden ></option>";
					var task=document.getElementById("task");
					var index = task.selectedIndex;
					var taskId = task.options[index].value; // 选中值
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
									questionnaire.options.add(new Option(ac[i].queName,ac[i].questionnaireId));
								}
								//获取元素对象
							} else if (ajax.status == 404) {
								alert("请求资源不存在")
							} else if (ajax.status == 500) {
								alert("服务器繁忙")
							}
						}
					}

					ajax.open("post", "../questionnarie/getQuestionnaires.do");
					ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
					ajax.send("taskId="+taskId);
				}
		</script>
	</head>
	<body onload="taskAjax()">

			<table class="table_context" width="100%">
	                    <tr>
	                    	<td width="10%" height="50" align="right">任务名称:<span style="color: red;">*</span></td>
	                        <td width="10%" height="50" align="left"><input id="qrformName" name="qrformName" type="text"></td>
	                    </tr>
				        <tr>
	                    	<td width="10%" height="50" align="right">任务描述:<span style="color: red;">*</span></td>
	                        <td width="10%" height="50" align="left"><input id="qrformDesc" name="qrformDesc" type="text"></td>
	                    </tr>
						<tr>
							<td width="10%" height="50" align="right">创建时间:</td>
							<td width="10%" height="50" align="left"> <input id="date" name="date" type="text" placeholder="yyyy-mm-dd" ></td>
						</tr>
						<tr>
							<td width="10%" height="50" align="right">所属任务:<span style="color: red;">*</span></td>
							<td width="10%" height="50" align="left"><select name="task" id="task" style="height: 25px ;width: 200px" onchange="questionnaireAjax()" >
								<option selected disabled hidden ></option>
							</select></td>
						</tr>
				        <tr>
							<td width="10%" height="50" align="right">所属表单:<span style="color: red;">*</span></td>
							<td width="10%" height="50" align="left"><select name="questionnaire" id="questionnaire" style="height: 25px ;width: 200px" >
								<option selected disabled hidden ></option>
							</select></td>
						</tr>

	                    <tr>
							<td height="50" colspan="2" align="center"><button class="layui-btn" onclick="addQrForm()">保存数据</button><button class="layui-btn layui-btn-danger" onclick="reset()">取消编辑</button></td>
	                    </tr>
			</table>
	</body>
</html>
