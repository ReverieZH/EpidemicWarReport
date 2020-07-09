
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
			    width: 500px;
			    height: 25px;
            }
 
		</style>
	</head>
	<body>
			<table class="table_context" width="100%">
						<tr>
							<td width="10%" height="50" align="right">超链接:</td>
							<td width="10%" height="50" align="left"><input id="link" name="link" type="text" value="<%=request.getAttribute("link")%>"></td>
						</tr>
			</table>
	</body>
</html>
