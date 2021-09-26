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

<title>快递管理</title>

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

	function deleteTransport(obj) {
		if (confirm("你确定删除此用户吗！")) {
			$.post("deleteTransportServerlet", {
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
		$.post("getTransportByIdServerlet", {
			id : $(obj).val()
		}, function(data, status) {
			jsonObj = JSON.parse(data);
			$("#name").val(jsonObj.name);
			$("#def").val(jsonObj.def);
			$("#price").val(jsonObj.price);
			$("#enable").prop("checked", jsonObj.enable == 1 ? true : false);
		});

		$("#dialog").show();
		$("#update").click(function() {
			if ($("#name").val() == null || $("#name").val() == "" || $("#def").val() == null || $("#def").val() == ""
			 || $("#price").val() == null || $("#price").val() == "") {
				alert("参数不能为空");
			} else {
				$.post("updateTransportByIdServerlet", {
					id : $(obj).val(),
					name : $("#name").val(),
					def : $("#def").val(),
					price : $("#price").val(),
					enable : $("#enable").prop("checked")
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
		$("#addDef").val("");
		$("#addPrice").val("");
		$("#addEnable").prop("checked", false);

		$("#dialog").hide();
		$("#addDialog").show();

		$("#add").click(function() {
			var nameVar = $("#addName").val();
			var defVar = $("#addDef").val();
			var priceVar = $("#addPrice").val();
			var enableVar = $("#addEnable").prop("checked");

			if (nameVar == "" || defVar == "" || priceVar == "") {
				alert("参数不能为空！")
			} else {
				$.post("addTransportServerlet", {
					name : nameVar,
					def : defVar,
					price : priceVar,
					enable : enableVar
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
			<th>快递公司</th>
			<th>首重</th>
			<th>续重</th>
			<th>启用</th>
			<th>操作</th>
		</tr>

		<c:forEach items="${transportList}" var="transport">
			<tr align="center" style="background-color:#0099FF">
				<td>${transport.id}</td>
				<td>${transport.name}</td>
				<td>${transport.def}</td>
				<td>${transport.price}</td>
				<td><c:if test="${transport.enable == 0 }">
						<c:out value="否" />
					</c:if> <c:if test="${transport.enable == 1 }">
						<c:out value="是" />
					</c:if></td>
				<td>
					<button onclick="showDialog(this)" style="margin:5;"
						value="${transport.id}">
						修改
						</bottun>
						<button onclick="deleteTransport(this)" style="margin:5"
							value="${transport.id}">删除</button>
				</td>
			</tr>
		</c:forEach>
	</table>

	<table id="dialog"
		style="background-color:#FFCC33;position:absolute;left:40%;top:10%;display:none">
		<tr>
			<th id="id">修改</th>
		</tr>
		<tr>
			<td>快递公司：</td>
			<td><input type="text" id="name" /></td>
		</tr>
		<tr>
			<td>首重：</td>
			<td><input type="text" id="def" /></td>
		</tr>
		<tr>
			<td>续重：</td>
			<td><input type="text" id="price" /></td>
		</tr>
		<tr>
			<td>启用：</td>
			<td><input type="checkbox" id="enable" />启用</td>
		</tr>
		<tr>
			<td><button id="update">修改</button></td>
			<td><button onclick="cancel(this)">取消</button></td>
		</tr>
	</table>

	<table id="addDialog"
		style="background-color:#FFCC33;position:absolute;left:40%;top:10%;display:none">
		<tr>
			<th>新增</th>
		</tr>
		<tr>
			<td>快递公司：</td>
			<td><input type="text" id="addName" /></td>
		</tr>
		<tr>
			<td>首重：</td>
			<td><input type="text" id="addDef" /></td>
		</tr>
		<tr>
			<td>续重：</td>
			<td><input type="text" id="addPrice" /></td>
		</tr>
		<tr>
			<td>启用：</td>
			<td><input type="checkbox" id="addEnable" />启用</td>
		</tr>
		<tr>
			<td><button id="add">增加</button></td>
			<td><button onclick="cancel(this)">取消</button></td>
		</tr>
	</table>
</body>
</html>
