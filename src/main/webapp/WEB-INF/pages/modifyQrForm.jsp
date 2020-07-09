<%@ page import="com.qidian.domain.QRForm" %>
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

				function modifyQrForm() {
					var qrformId=document.getElementById("qrformId").value;
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
					var statusop=document.getElementById("statusop");
					var index=statusop.selectedIndex;
					var status = statusop.options[index].value; // 选中值

					ajaxRequest(qrformId,qrformName,qrformDesc,status);
				}

				function ajaxRequest( qrformId,qrformName,qrformDesc,status) {
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
									window.parent.alertmessage("修改成功");
									parent.layer.close(index);
								}else{
									window.parent.alertmessage("修改失败");
								}
								//获取元素对象
							}else if(ajax.status==404){
								alert("请求资源不存在")
							}else if(ajax.status==500){
								alert("服务器繁忙")
							}
						}
					}

					ajax.open("post", "modifyQrForm.do");
					ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
					ajax.send("qrformId="+qrformId+"&qrformName="+qrformName+"&qrformDesc="+qrformDesc+"&status="+status);
				}

				function reset() {
					document.getElementById("qrformName").value="";
					document.getElementById("qrformDesc").value="";
				}

		</script>
	</head>
	<body >
		<% QRForm qrForm= (QRForm) request.getAttribute("qrForm"); %>
			<table class="table_context" width="100%">
				<input type="hidden" name="qrformId" id="qrformId" value="<%=qrForm.getQrformId()%>">
				<tr>
	                    	<td width="10%" height="50" align="right">任务名称:<span style="color: red;">*</span></td>
	                        <td width="10%" height="50" align="left"><input id="qrformName" name="qrformName" type="text" value="<%=qrForm.getQrformName()%>"></td>
	                    </tr>
				        <tr>
	                    	<td width="10%" height="50" align="right">任务描述:<span style="color: red;">*</span></td>
	                        <td width="10%" height="50" align="left"><input id="qrformDesc" name="qrformDesc" type="text" value="<%=qrForm.getQrformDesc()%>"></td>
	                    </tr>
						<tr>
							<td width="10%" height="50" align="right">使用状态:</td>
							<td width="10%" height="50" align="left"> <select id="statusop" name="statusop" lay-verify="" >
								<option value="<%=qrForm.getStatus()%>" disabled selected hidden><%=qrForm.getStatus().equals("1")?"使用中":"禁用中"%></option>
								<option value="1">启用</option>
								<option value="0">禁用</option>
							</select></td>
						</tr>

	                    <tr>
							<td height="50" colspan="2" align="center"><button class="layui-btn" onclick="modifyQrForm()">保存数据</button><button class="layui-btn layui-btn-danger" onclick="reset()">取消编辑</button></td>
	                    </tr>
			</table>
	</body>
</html>
