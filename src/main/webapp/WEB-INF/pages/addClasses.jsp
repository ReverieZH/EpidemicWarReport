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

				function addClasses() {
					var classesName=document.getElementById("classesName").value;
					classesName=trim(classesName);
					if(classesName==""){
						window.parent.alertmessage("请输入班级名称");
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
					var major=document.getElementById("major");
					var index = major.selectedIndex;
					var majorId = major.options[index].value; // 选中值
					var majorName = major.options[index].text; // 选中值
					if(majorName==""){
						window.parent.alertmessage("请选择专业");
						return false;
					}

				//	window.parent.alertmessage("添加成功");
					ajaxRequest(classesName,majorId,majorName,academicId);
					// location.href="task/addTask.do?"+"taskName="+taskName+"&taskDesc="+taskDesc+"&date="+date;


				}

				function ajaxRequest( classesName,majorId,majorName,academicId) {
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

					ajax.open("post", "addClasses.do");
					ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
					ajax.send("classesName="+classesName+"&majorId="+majorId+"&majorName="+majorName+"&academicId="+academicId);
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
		</script>
	</head>
	<body onload="academicAjax()">

			<table class="table_context" width="100%">
	                    <tr>
	                    	<td width="10%" height="50" align="right">班级名称:<span style="color: red;">*</span></td>
	                        <td width="10%" height="50" align="left"><input id="classesName" name="classesName" type="text"></td>
	                    </tr>
						<tr>
							<td width="10%" height="50" align="right">所属学院:<span style="color: red;">*</span></td>
							<td width="10%" height="50" align="left"><select name="academic" id="academic" style="height: 25px ;width: 200px" onchange="majorAjax()" >
								<option selected disabled hidden ></option>
							</select></td>
						</tr>
				        <tr>
							<td width="10%" height="50" align="right">所属专业:<span style="color: red;">*</span></td>
							<td width="10%" height="50" align="left"><select name="major" id="major" style="height: 25px ;width: 200px" >
								<option selected disabled hidden ></option>
							</select></td>
						</tr>
	                    <tr>
							<td height="50" colspan="2" align="center"><button class="layui-btn" onclick="addClasses()">保存数据</button><button class="layui-btn layui-btn-danger" onclick="reset()">取消编辑</button></td>
	                    </tr>
			</table>
	</body>
</html>
