<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="check_login.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'frame_header.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="background-color:#6699FF">
    <p style="position:relative;left:30%;top:20%;width:100%;font-size:50;color:#FFCC00;">Feigle特产店管理后台系统</p>
    <p style="position:absolute;right:10%;top:20%;font-size:20">欢迎你：${name }</p>
  </body>
</html>
