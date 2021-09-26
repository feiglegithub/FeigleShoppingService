<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="check_login.jsp" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String name = (String)session.getAttribute("name");
	/* if(name == null || name.equals("")){
		response.getWriter().print("<p style=\"font-size:50;position:relative;left:40%;top:40%;color:red;\">用户信息不正确或超时，请重新登录！！</p>");
		Thread.sleep(500);
		response.sendRedirect("login.jsp");
	} */
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<frameset rows="20%,80%">
	<frame src="frame_header.jsp" frameborder="0" scrolling="no">

	<frameset cols="10%,*">
		<frame src="frame_left.jsp" frameborder="0">
		<frame src="frame_right.jsp" frameborder="0" name="showframe">
	</frameset>
</frameset>
</html>
