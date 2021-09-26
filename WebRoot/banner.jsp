<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="check_login.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'banner.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	function deleteBanner(bannerId) {
		if (confirm("你确定删除此广告图片吗！id：" + bannerId)) {
			$.post("bannerDeleteServerlet", {
				id : bannerId
			}, function(data, status) {
				if (data == "true") {
					location.reload();
				} else {
					alert("删除失败！" + data);
				}
			});
		}
	}
</script>
</head>

<body style="background-color:#336600">
	<form action="bannerUploadServerlet" method="post"
		enctype="multipart/form-data" id="formId">
		<input type="file" name="img" /> <input type="submit" values="新增广告" />
		<input type="reset" values="重置" />
	</form>

	<table width="100%">
		<tr align="center" style="background-color:#990000">
			<th>id</th>
			<th>图片</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list }" var="bannerBean">
			<tr align="center" style="background-color:#0099FF">
				<td>${bannerBean.id }</td>
				<td><img alt="商品图片" src="${bannerBean.path }"
					style="width:100;height:100;" /></td>
				<td>${bannerBean.createTime }</td>
				<td>
					<button onclick="deleteBanner(${bannerBean.id })">删除</button>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
