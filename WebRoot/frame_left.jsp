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
    
    <title>My JSP 'frame_left.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		#s1{color: #FFCC00;position:relative;left:10%;margin:10;font-size:20}
		/* a:link {color: #FFCC00} */ /*设置全局未点击链接的颜色*/
		/* a:visited {color: #0099FF} */ /*设置全局被点击后的链接颜色*/
		/* a:hover {color: #颜色代码 }  *//*设置全局鼠标悬停在链接上未点击时的颜色*/
		/* a:active {color: #颜色代码 }  *//*设置全局鼠标在点击链接瞬间发生的动作*/
	</style>
  </head>
  
  <body style="background-color:#660000;margin:0">
    <div>
    	<a href="userListServerlet" id="s1" target="showframe">用户管理--></a><br>
    	<a href="commodityListServerlet" id="s1" target="showframe">商品管理--></a><br>
    	<a href="orderListServerlet" id="s1" target="showframe">订单管理--></a><br>
    	<a href="bannerImageListServerlet" id="s1" target="showframe">广告管理--></a><br>
    	<a href="transportListServerlet" id="s1" target="showframe">快递公司--></a><br>
    	<a href="report.jsp" id="s1" target="showframe">营业报表--></a>
    	<a href="appListServerlet" id="s1" target="showframe">APP管理--></a>
    </div>
  </body>
</html>
