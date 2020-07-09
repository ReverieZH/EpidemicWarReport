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

				function addOperator() {
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
					ajaxRequest(username, password, name, role, academicName);
				}

				function ajaxRequest(username, password, name, role, academicName) {
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

					ajax.open("post", "addOperator.do");
					ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
					ajax.send("username="+username+"&password="+password+"&name="+name+"&role="+role+"&academicName="+academicName+"&validty=1");
				}

				function reset() {
					document.getElementById("username").value="";
					document.getElementById("password").value="";
					document.getElementById("name").value="";
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





		</script>
	</head>
	<body onload="academicAjax()">

			<table class="table_context" width="100%">
	                    <tr>
	                    	<td width="10%" height="50" align="right">账户名:<span style="color: red;">*</span></td>
	                        <td width="10%" height="50" align="left"><input id="username" name="username" type="text"></td>
	                    </tr>
						<tr>
							<td width="10%" height="50" align="right">账号密码:<span style="color: red;">*</span></td>
							<td width="10%" height="50" align="left"><input id="password" name="password" type="password"></td>
						</tr>
						<tr>
							<td width="10%" height="50" align="right">姓名:<span style="color: red;">*</span></td>
							<td width="10%" height="50" align="left"><input id="name" name="name" type="text"></td>
						</tr>
						<tr>
							<td width="10%" height="50" align="right">角色:<span style="color: red;">*</span></td>
							<td width="10%" height="50" align="left"><select name="roleop" id="roleop" style="height: 25px ;width: 200px"  >
								<option selected disabled hidden ></option>
								<option value="0" >系统管理员</option>
								<option value="1" >学生处管理人员</option>
								<option value="2">学院管理人员</option>
								<option value="3">辅导员</option>
							</select></td>
						</tr>
						<tr>
							<td width="10%" height="50" align="right">所属学院:<span style="color: red;">*</span></td>
							<td width="10%" height="50" align="left"><select name="academic" id="academic" style="height: 25px ;width: 200px" >
								<option selected disabled hidden ></option>
							</select></td>
						</tr>
	                    <tr>
							<td height="50" colspan="2" align="center"><button class="layui-btn" onclick="addOperator()">保存数据</button><button class="layui-btn layui-btn-danger" onclick="reset()">取消编辑</button></td>
	                    </tr>
			</table>
	</body>
</html>
