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

<title>My JSP 'commodity.jsp' starting page</title>

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
	function cancel(obj) {
		$("#updateDialog").hide();
		$("#addDialog").hide();
	}

	function showAddDialog() {
		$("#addDialog").show();
		$("#updateDialog").hide();
	}

	function showUpdateDialog(commodityId) {
		$("#updateDialog").show();
		$("#addDialog").hide();

		$("#id").text("修改id:" + commodityId + "商品信息");

		$.post("getCommodityByIdServerlet", {
			id : commodityId
		}, function(data, status) {
			jsonObj = JSON.parse(data);
			$("#updateName").val(jsonObj.commodityName);
			$("#updatePrice").val(jsonObj.price);
			$("#updateCost").val(jsonObj.cost);
			$("#updateQuantity").val(jsonObj.quantity);
			$("#updateDetail").val(jsonObj.detail);
			$("#updateWeight").val(jsonObj.weight);
		});

		$("#update").click(function() {
			if ($("#updateName").val() == "" || $("#updatePrice").val() == "" || $("#updateQuantity").val() == ""
				|| $("#updateDetail").val() == "" || $("#updateTransport").val() == "") {
				alert("参数不能为空");
			} else {
				$.post("updateCommodityServerlet", {
					id : commodityId,
					name : $("#updateName").val(),
					price : $("#updatePrice").val(),
					quantity : $("#updateQuantity").val(),
					detail : $("#updateDetail").val(),
					cost : $("#updateCost").val(),
					weight : $("#updateWeight").val()
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

	function showDialog(show, hide) {
		$("#" + show).show();
		$("#" + hide).hide();
	}

	function deleteApp(appId) {
		if (confirm("你确定删除此此商品吗！id=" + appId)) {
			$.post("deleteServerlet", {
				id : appId
			}, function(data, status) {
				if (data == "true") {
					location.reload();
				} else {
					alert("删除失败！" + data);
				}
			});
		}
	}

	function stopSelling(id) {
	}

	function update() {
		var imgVar = $("#img").val();
		var nameVar = $("#name").val();
		var priceVar = $("#price").val();
		var detailVar = $("#detail").val();

		alert(imgVar + "," + nameVar + "," + priceVar + "," + detailVar)
		$.post("addCommodityServerlet", {
			img : imgVar,
			name : nameVar,
			price : priceVar,
			detail : detailVar
		}, function(data, status) {
			if (data == "true") {
				location.reload();
			} else {
				alert("新增失败！" + data);
			}
		});
	}
</script>
</head>

<body style="background-color:#336600">
	<button onclick="showDialog('addDialog','updateDialog')">新增</button>
	<table width="100%">
		<tr align="center" style="background-color:#990000">
			<th>id</th>
			<th>版本标识</th>
			<th>版本名称</th>
			<th>文件大小</th>
			<th>更新日志</th>
			<th>下载路径</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list }" var="appBean">
			<tr align="center" style="background-color:#0099FF">
				<td>${appBean.id }</td>
				<td>${appBean.newVersion }</td>
				<td>${appBean.versionName }</td>
				<td>${appBean.targetSize }</td>
				<td>${appBean.updateLog }</td>
				<td>${appBean.apkFileUrl }</td>
				<td>${appBean.createTime }</td>
				<td>
					<%-- <button onclick="showUpdateDialog(${appBean.id })">修改</button> --%>
					<button onclick="deleteApp(${appBean.id })">删除</button>
				</td>
			</tr>
		</c:forEach>
	</table>

	<table id="updateDialog"
		style="background-color:#FFCC33;position:absolute;left:40%;top:10%;display:none">
		<tr>
			<th id="id">修改商品信息</th>
		</tr>
		<tr style="display:none">
			<td>图片：</td>
			<td><input type="file" id="updateImg" name="img" /></td>
		</tr>
		<tr>
			<td>商品名称：</td>
			<td><input type="text" id="updateName" name="updateName" /></td>
		</tr>
		<tr>
			<td>价格：</td>
			<td><input type="text" id="updatePrice" name="updatePrice" /></td>
		</tr>
		<tr>
			<td>成本：</td>
			<td><input type="text" id="updateCost" name="updateCost" /></td>
		</tr>
		<tr>
			<td>数量：</td>
			<td><input type="text" id="updateQuantity" name="updateQuantity" /></td>
		</tr>
		<tr>
			<td>重量：</td>
			<td><input type="text" id="updateWeight" name="updateWeight" /></td>
		</tr>
		<tr>
			<td>详情：</td>
			<td><textarea rows="5" id="updateDetail" cols="40"
					name="updateDetail"></textarea></td>
		</tr>
		<tr>
			<td><button id="update">修改</button></td>
			<td><button onclick="cancel(this)">取消</button></td>
		</tr>
	</table>

	<form action="uploadServerlet" method="post"
		enctype="multipart/form-data">
		<table id="addDialog"
			style="background-color:#FFCC33;position:absolute;left:40%;top:10%;display:none">
			<tr>
				<th id="id">发布APP</th>
			</tr>
			<tr>
				<td>APK文件：</td>
				<td><input type="file" id="apk" name="apk" /></td>
			</tr>
			<tr>
				<td>版本号：</td>
				<td><input type="text" id="newVersion" name="newVersion" /></td>
			</tr>
			<tr>
				<td>版本名称：</td>
				<td><input type="text" id="versionName" name="versionName" /></td>
			</tr>
			<tr>
				<td>文件大小：</td>
				<td><input type="text" id="targetSize" name="targetSize" /></td>
			</tr>
			<tr>
				<td>更新日志：</td>
				<td><textarea rows="3" cols="20" id="updateLog"
						name="updateLog"></textarea></td>
			</tr>
			<tr>
				<td><input type="submit" value="增加" /></td>
				<td><input type="button" onclick="cancel(this)" value="取消" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
