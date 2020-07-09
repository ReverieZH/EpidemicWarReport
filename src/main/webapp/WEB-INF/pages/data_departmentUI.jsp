<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>疫站报</title>
	<link rel="stylesheet" href="<%=basePath%>/layui/css/layui.css">
	<script src="<%=basePath%>/layui/layui.js"></script>
  	<style>
  		#left{
  			width: 225px;	
  			height: 800px;
  			background-color: #393D49;
  		}
  		#left .div{
				margin-left:20px ;
			}	
			#imgdiv {
				position:absolute;
			  border-width:0px;
			  left:1000px;
			  top:5px;
			  width:300px;
			  height:40px;
			  font-family:'微软雅黑 Bold', '微软雅黑 Regular', '微软雅黑';
			  font-style:normal;
			  color:#FFFFFF;
			  text-align:left;
			  line-height:32px;

			}
			#logodiv{
				position:absolute;
				left:50px;
				top: 5px;
				width: 35px;
				height: 35px;
			}
			
			#logo {
				  border-width:0px;
				  left:0px;
				  top:0px;
				  width:35px;
				  height:35px;
      }
         
      #word {
							  border-width:0px;
							  position:absolute;
							  left:100px;
							  top:5px;
							  width:200px;
							  word-wrap:break-word;
							  color: #FFFFFF;
        }
        	   
        #right{
				    position:absolute;
				    margin-left: 20px;
				    margin-top: 10px;
				    left: 220px;
				    top:70px;
						width: 1050px;
						height: 600px;
						border: solid #ccc 1px ;
						display: inline;
						box-shadow: 0px 2px 5px #888888;

						padding: 10px;
						 overflow: hidden;
			}
			#right iframe{
				width: 1050px;
				height: 550px;
			
			}
			i{
				font-size: 23px;
         	color: #FFFFFF;
         	margin-right: 5px;
			}
  	</style>
  	
		<script>
		//一般直接写在一个js文件中
		function getiframe(url){
				layui.use('layer', function(){
				  var layer = layui.layer;
				  layer.open({
				  type: 2,
				      title: '修改任务',
				      maxmin: true,
				      shadeClose: true, //点击遮罩关闭层
				      area : ['800px' , '520px'],
				  content: url //这里content是一个普通的String
				});
				})
			}
				//注意：导航 依赖 element 模块，否则无法进行功能性操作
				layui.use('element', function(){
				  var element = layui.element;
				  
				  //…
				});

			function change(url,id){
				var li=document.getElementById(id);
				li.class="layui-this";
				var iframe=document.getElementById("inframe");
				iframe.src=url;
			}
		</script> 
</head>
<body>
     <%String role= String.valueOf(session.getAttribute("role"));%>
			<div id="top">
						<ul class="layui-nav" lay-filter="">
					  <li class="layui-nav-item "><a href="servicemain.do">业务管理</a></li>
					  <li class="layui-nav-item layui-this"><a href="">数据管理</a></li>					 
					  <li class="layui-nav-item">
					    <a href=""><img src="//t.cn/RCzsdCq" class="layui-nav-img">我</a>
					    <dl class="layui-nav-child">
							<dd><a onclick="getiframe('operator/getmodifyInform.do?username=<%=session.getAttribute("username")%>')">修改信息</a></dd>
							<dd><a href="logout.do">退出系统</a></dd>
					    </dl>
					  </li>
						</ul>
						
						<div id="imgdiv">
								<div id="logodiv" >
								    <img id="logo" class="img" src="img/u1167.svg"/>
								</div>
								<div id="word"  >
									<p style="font-size:18px;"><span style="font-family:'微软雅黑 Bold', '微软雅黑 Regular', '微软雅黑';font-weight:700;"> 疫站报-疫情上报系统</span></p>
								</div>
					</div>	
			</div>
			
		<div id="content">
		
			<div id="left" >
							<ul id="side_nav" class="layui-nav layui-nav-tree " style="margin-right: 10px;">
								<div class="div" >
									 <li class="layui-nav-item layui-nav-itemed"><span>机构设置</span><div style="float:right;"><i class="fa fa-angle-down" aria-hidden="true"></i></div>
									  <ul>
									   <li id="academic"><a onclick="change('academic/main.do','academic')"><i class="layui-icon layui-icon-shrink-right"></i>学校院系设置</a></li>
									   <li id="major"><a onclick="change('major/main.do','major')"><i class="layui-icon layui-icon-file-b"></i>专业设置列表</a></li>
									   <li id="classes"><a onclick="change('classes/main.do','classes')"><i class="layui-icon layui-icon-flag"></i>班级设置列表</a></li>
									  </ul>
									 </li>
								 </div>
								 <div class="div" >
									 <li class="layui-nav-item layui-nav-itemed"><span>学生数据</span><div style="float:right;"><i class="fa fa-angle-down" aria-hidden="true"></i></div>
									  <ul>
									   <li id="student"><a onclick="change('student/main.do','student','${role}')"><i class="layui-icon layui-icon-username"></i> 学生数据管理</a></li>
									  </ul>
									 </li>
							     </div>
								<c:if test="${role==0}">
							     <div class="div" >
									 <li class="layui-nav-item layui-nav-itemed"><span>教师数据</span><div style="float:right;"><i class="fa fa-angle-down" aria-hidden="true"></i></div>
									  <ul>
									   <li id="operator"><a onclick="change('operator/main.do','operator','${role}')"><i class="layui-icon layui-icon-face-smile"></i> 教师数据管理</a></li>
<%--									   <li ir="role"><a onclick="change('role/main.do','role','${role}')"><i class="layui-icon layui-icon-user"></i> 系统角色管理</a></li>--%>
									  </ul>
									 </li>
								 </div>
								</c:if>
							</ul>
				  </div>

					<div id="right">
						<iframe id="inframe" marginWidth=0 marginHeight=0  width="1050px" height="600px" scrolling="auto" frameBorder=0 style="float: right;right: 300px;"></iframe>
					</div>
			</div>
		<script type="text/javascript">
		 (function(){
			  var navWrap=document.getElementById("side_nav");
			  var nav1s=navWrap.getElementsByTagName("span");
			  var nav2s=navWrap.getElementsByTagName("ul");
			  for(var i=0,len=nav1s.length;i<len;i++){
			   nav1s[i].onclick=(function(i){
			    return function(){
			     for(var j=0;j<len;j++){
			      nav2s[j].style.display="none";
			     }
			     nav2s[i].style.display="block";
			    }
			   })(i)
			  }
			 })()
		</script>
			
</body>
</html>