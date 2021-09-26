<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Feigle特产店后台管理系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="js/jquery-3.4.1.min.js"></script>
<script>
	$(document).ready(function(){
	  $("button").click(function(){
	  	var name = $(".name").val();
	  	var psw = $(".psw").val();
	  	
	  	if(name == "" || psw == ""){
	  		alert("用户名、密码不能为空");
	  		return false;
	  	}
	  	/* else{
		  	$.post("loginServerlet",{name,psw},function(data,status){
		    	alert(data);
		    });
	  	} */
	  });
	});
</script>

<style type="text/css">
#s1 {
	position: relative;
	left: 30%;
	top: 10%;
	height: 30;
	margin: 5;
}
</style>
</head>

<body style="margin:0;background-color:#0099FF;">
	<form action="loginServerlet" method="POST">
		<div
			style="margin-left:auto;margin-right:auto;margin-top:15%;width:400;height:250;background-color:#990000;">
			<b
				style="position:relative;left:5%;top:10;font-size:30;color:#FFCC33;">Feigle特产店后台管理系统</b><br>
			<input id="s1" class="name" name="name" type="text" placeholder="请输入用户名" /><br>
			<input id="s1" class="psw" name="psw" type="password" placeholder="请输入密码" /><br>
			<button id="s1">登录</button>
		</div>
	</form>
</body>
</html>
