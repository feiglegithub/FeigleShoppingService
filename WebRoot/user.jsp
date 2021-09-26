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

<title>用户管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="js/jquery-3.4.1.min.js"></script>
<script src="easyloader.js"></script>
<script src="jquery.easyui.min.js"></script>
<script src="jquery.easyui.mobile.js"></script>
<script type="text/javascript">
	function update(obj) {
		alert($(obj).val());
	}

	function deleteUser(obj) {
		if (confirm("你确定删除此用户吗！")) {
			$.post("deleteUserServerlet", {
				id : $(obj).val()
			}, function(data, status) {
				if (data == "true") {
					location.reload();
				} else {
					alert("删除失败！" + data);
				}
			});
		}
	}

	function showDialog(obj) {
		$("#addDialog").hide();

		$("#id").text("修改id:" + $(obj).val() + "用户信息");
		$("#psw").val("");
		$("#role").val("");

		$("#dialog").show();
		$("#update").click(function() {
			if ($("#psw").val() == null || $("#psw").val() == "" || $("#role").val() == null || $("#role").val() == "") {
				alert("参数不能为空");
			} else {
				$.post("updateUserServerlet", {
					id : $(obj).val(),
					psw : $("#psw").val(),
					role : $("#role").val()
				}, function(data, status) {
					if (data == "true") {
						location.reload();
					} else {
						alert("更新失失败！" + data);
					}
				});
			}

		});
	}

	function cancel(obj) {
		$("#dialog").hide();
		$("#addDialog").hide();
	}

	function add() {
		$("#addName").val("");
		$("#addPsw").val("");
		$("#addRole").val("");

		$("#dialog").hide();
		$("#addDialog").show();
		
		$("#add").click(function() {
			var nameVar = $("#addName").val();
			var pswVar = $("#addPsw").val();
			var roleVar = $("#addRole").val();

			if (nameVar == "" || pswVar == "" || roleVar == "") {
				alert("参数不能为空！")
			} else {
				$.post("addUserServerlet", {
					name : nameVar,
					psw : pswVar,
					role : roleVar
				}, function(data, status) {
					if (data == "true") {
						location.reload();
					} else {
						alert("新增失败！" + data);
					}
				});
			}
		});
	}
</script>
</head>

<body style="background-color:#336600">
	<button onclick="add()">新增</button>
	<table width="100%">
		<tr align="center" style="background-color:#990000">
			<th>id</th>
			<th>用户名</th>
			<th>角色</th>
			<th>操作</th>
		</tr>

		<c:forEach items="${userInfoList}" var="userInfo">
			<tr align="center" style="background-color:#0099FF">
				<td>${userInfo.id}</td>
				<td>${userInfo.name}</td>
				<td>${userInfo.role}</td>
				<td>
					<button onclick="showDialog(this)" style="margin:5;"
						value="${userInfo.id}">
						修改
						</bottun>
						<button onclick="deleteUser(this)" style="margin:5"
							value="${userInfo.id}">删除</button>
				</td>
			</tr>
		</c:forEach>
	</table>

	<table id="dialog"
		style="background-color:#FFCC33;position:absolute;left:40%;top:10%;display:none">
		<tr>
			<th id="id">修改用户信息</th>
		</tr>
		<tr>
			<td>密码：</td>
			<td><input type="password" id="psw" /></td>
		</tr>
		<tr>
			<td>角色：</td>
			<td><select id="role">
					<option value="0">管理员</option>
					<option value="1">操作员</option>
					<option value="2">普通用户</option>
			</select></td>
		</tr>
		<tr>
			<td><button id="update">修改</button></td>
			<td><button onclick="cancel(this)">取消</button></td>
		</tr>
	</table>

	<table id="addDialog"
		style="background-color:#FFCC33;position:absolute;left:40%;top:10%;display:none">
		<tr>
			<th>新增用户</th>
		</tr>
		<tr>
			<td>用户名：</td>
			<td><input type="text" id="addName" /></td>
		</tr>
		<tr>
			<td>密码：</td>
			<td><input type="password" id="addPsw" /></td>
		</tr>
		<tr>
			<td>角色：</td>
			<td><select id="addRole">
					<option value="0">管理员</option>
					<option value="1">操作员</option>
					<option value="2">普通用户</option>
			</select></td>
		</tr>
		<tr>
			<td><button id="add">增加</button></td>
			<td><button onclick="cancel(this)">取消</button></td>
		</tr>
	</table>
</body>
</html>
