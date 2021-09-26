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
    
    <title>My JSP 'inventory.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="background-color:#336600">
    	<table width="100%">
    		<tr align="center" style="background-color:#990000">
    			<th>图片</th>
    			<th>名称</th>
    			<th>数量</th>
    			<th>操作</th>
    		</tr>
    		<tr align="center" style="background-color:#0099FF">
    			<td></td>
    			<td></td>
    			<td></td>
    			<td>
    				<a>增加</a>
    				<a>减少</a>
    			</td>
    		</tr>
    	</table>
  </body>
</html>
