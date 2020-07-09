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

				function addStudent() {
					var sno=document.getElementById("sno").value;
					sno=trim(sno);
					if(sno==""){
						window.parent.alertmessage("请输入学号");
						return false;
					}
					var name=document.getElementById("name").value;
					name=trim(name);
					if(name==""){
						window.parent.alertmessage("请输入学生姓名");
						return false;
					}
					var password=document.getElementById("password").value;
					password=trim(password);
					if(password==""){
						window.parent.alertmessage("请输入学生账号密码");
						return false;
					}
					var sexop=document.getElementById("sexop");
					var index = sexop.selectedIndex;
					var sex= sexop.options[index].value;
					sex=trim(sex);
					if(sex==""){
						window.parent.alertmessage("请选择学生性别");
						return false;
					}
					var gradeop=document.getElementById("gradeop");
					var index = gradeop.selectedIndex;
					var grade= gradeop.options[index].value;
					grade=trim(grade);
					if(grade==""){
						window.parent.alertmessage("请选择学生年级");
						return false;
					}
					var typeop=document.getElementById("typeop");
					var index = typeop.selectedIndex;
					var type= typeop.options[index].value;
					type=trim(type);
					if(type==""){
						window.parent.alertmessage("请选择学生类型");
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
					var classes=document.getElementById("classes");
					var index = classes.selectedIndex;
					var classesId = classes.options[index].value; // 选中值
					var classesName = classes.options[index].text; // 选中值
					if(classesName==""){
						window.parent.alertmessage("请选择班级");
						return false;
					}

					var dormitory=document.getElementById("dormitory").value;
					dormitory=trim(dormitory);
					if(dormitory==""){
						window.parent.alertmessage("请输入宿舍号");
						return false;
					}
					var origin=document.getElementById("origin").value;
					origin=trim(origin);
					if(origin==""){
						window.parent.alertmessage("请输入生源地");
						return false;
					}
					var address=document.getElementById("address").value;
					address=trim(address);
					if(address==""){
						window.parent.alertmessage("请输入家庭地址");
						return false;
					}
					var phone=document.getElementById("phone").value;
					phone=trim(phone);
					if(phone==""){
						window.parent.alertmessage("请输入联系电话");
						return false;
					}

				//	window.parent.alertmessage("添加成功");
					ajaxRequest(sno,name,password,sex,grade,type,academicName,majorName,classesName,dormitory,origin,address,phone);
					// location.href="task/addTask.do?"+"taskName="+taskName+"&taskDesc="+taskDesc+"&date="+date;


				}

				function ajaxRequest( sno,name,password,sex,grade,type,academicName,majorName,classesName,dormitory,origin,address,phone) {
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

					ajax.open("post", "addStudent.do");
					ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
					ajax.send("sno="+sno+"&name="+name+"&password="+password+"&sex="+sex+"&grade="+grade+"&type="+type+"&academicName="+academicName+"&majorName="+majorName+"&classesName="+classesName+"&dormitory="+dormitory+"&origin="+origin+"&address="+address+"&phone="+phone);
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

			function classesAjax() {
				var major=document.getElementById("major");
				var classes=document.getElementById("classes");
				classes.innerHTML = "<option selected disabled hidden ></option>";
				var index = major.selectedIndex;
				var majorId = major.options[index].value; // 选中值

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

			function getGrade() {
				var myDate= new Date();
				var startYear=myDate.getFullYear()-5;//起始年份
				var endYear=myDate.getFullYear();//结束年份
				var gradeop=document.getElementById('gradeop');
				for (var i=endYear;i>=startYear;i--)
				{
					gradeop.options.add(new Option(i,i));
				}
			}
		</script>
	</head>
	<body onload="academicAjax(),getGrade()">

			<table class="table_context" width="100%">
	                    <tr>
	                    	<td width="10%" height="50" align="right">学号:<span style="color: red;">*</span></td>
	                        <td width="10%" height="50" align="left"><input id="sno" name="sno" type="text"></td>
	                    </tr>
						<tr>
							<td width="10%" height="50" align="right">姓名:<span style="color: red;">*</span></td>
							<td width="10%" height="50" align="left"><input id="name" name="name" type="text"></td>
						</tr>
						<tr>
							<td width="10%" height="50" align="right">账号密码:<span style="color: red;">*</span></td>
							<td width="10%" height="50" align="left"><input id="password" name="password" type="password"></td>
						</tr>
						<tr>
							<td width="10%" height="50" align="right">性别:<span style="color: red;">*</span></td>
							<td width="10%" height="50" align="left"><select name="sexop" id="sexop" style="height: 25px ;width: 200px"  >
								<option selected disabled hidden ></option>
								<option value="男" >男</option>
								<option value="女" >女</option>
							</select></td>
						</tr>
						<tr>
							<td width="10%" height="50" align="right">年级:<span style="color: red;">*</span></td>
							<td width="10%" height="50" align="left"><select name="gradeop" id="gradeop" style="height: 25px ;width: 200px"  >
								<option selected disabled hidden ></option>
							</select></td>
						</tr>
						<tr>
							<td width="10%" height="50" align="right">学生类型:<span style="color: red;">*</span></td>
							<td width="10%" height="50" align="left"><select name="typeop" id="typeop" style="height: 25px ;width: 200px"  >
								<option selected disabled hidden ></option>
								<option value="本科生" >本科生</option>
								<option value="研究生" >研究生</option>
								<option value="博士生" >博士生</option>
							</select></td>
						</tr>
						<tr>
							<td width="10%" height="50" align="right">所属学院:<span style="color: red;">*</span></td>
							<td width="10%" height="50" align="left"><select name="academic" id="academic" style="height: 25px ;width: 200px" onchange="majorAjax()" >
								<option selected disabled hidden ></option>
							</select></td>
						</tr>
				        <tr>
							<td width="10%" height="50" align="right">所属专业:<span style="color: red;">*</span></td>
							<td width="10%" height="50" align="left"><select name="major" id="major" style="height: 25px ;width: 200px" onchange="classesAjax()">
								<option selected disabled hidden ></option>
							</select></td>
						</tr>
						<tr>
							<td width="10%" height="50" align="right">所属班级:<span style="color: red;">*</span></td>
							<td width="10%" height="50" align="left"><select name="classes"  id="classes" style="height: 25px ;width: 200px" >
								<option selected disabled hidden ></option>
							</select></td>
						</tr>
						<tr>
							<td width="10%" height="50" align="right">宿舍号:<span style="color: red;">*</span></td>
							<td width="10%" height="50" align="left"><input id="dormitory" name="dormitory" type="text"></td>
						</tr>
						<tr>
							<td width="10%" height="50" align="right">生源地:<span style="color: red;">*</span></td>
							<td width="10%" height="50" align="left"><input id="origin" name="origin" type="text"></td>
						</tr>
						<tr>
							<td width="10%" height="50" align="right">地址:<span style="color: red;">*</span></td>
							<td width="10%" height="50" align="left"><input id="address" name="address" type="text"></td>
						</tr>
						<tr>
							<td width="10%" height="50" align="right">联系电话:<span style="color: red;">*</span></td>
							<td width="10%" height="50" align="left"><input id="phone" name="phone" type="text"  maxlength="11"></td>
						</tr>
	                    <tr>
							<td height="50" colspan="2" align="center"><button class="layui-btn" onclick="addStudent()">保存数据</button><button class="layui-btn layui-btn-danger" onclick="reset()">取消编辑</button></td>
	                    </tr>
			</table>
	</body>
</html>
