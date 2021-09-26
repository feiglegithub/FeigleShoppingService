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

	function deleteCommodity(commodityId) {
		if (confirm("你确定删除此此商品吗！id=" + commodityId)) {
			$.post("deleteCommodityServerlet", {
				id : commodityId
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
			<th>图片</th>
			<th>名称</th>
			<th>单价</th>
			<th>成本</th>
			<th>数量</th>
			<th>重量</th>
			<th>详情</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list }" var="commodityBean">
			<tr align="center" style="background-color:#0099FF">
				<td>${commodityBean.id }</td>
				<td><img alt="商品图片" src="${commodityBean.imgPath }"
					style="width:100;height:100;" /></td>
				<td>${commodityBean.commodityName }</td>
				<td>${commodityBean.price }</td>
				<td>${commodityBean.cost }</td>
				<td>${commodityBean.quantity }</td>
				<td>${commodityBean.weight }</td>
				<td>${commodityBean.detail }</td>
				<td>${commodityBean.createTime }</td>
				<td><button onclick="showUpdateDialog(${commodityBean.id })">修改</button>
					<button onclick="deleteCommodity(${commodityBean.id })">删除</button></td>
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

	<form action="addCommodityServerlet" method="post"
		enctype="multipart/form-data">
		<table id="addDialog"
			style="background-color:#FFCC33;position:absolute;left:40%;top:10%;display:none">
			<tr>
				<th id="id">新增商品</th>
			</tr>
			<tr>
				<td>图片：</td>
				<td><input type="file" id="img" name="img" /></td>
			</tr>
			<tr>
				<td>商品名称：</td>
				<td><input type="text" id="name" name="name" /></td>
			</tr>
			<tr>
				<td>价格：</td>
				<td><input type="text" id="price" name="price" /></td>
			</tr>
			<tr>
				<td>成本：</td>
				<td><input type="text" id="cost" name="cost" /></td>
			</tr>
			<tr>
				<td>数量：</td>
				<td><input type="text" id="price" name="quantity" /></td>
			</tr>
			<tr>
				<td>重量：</td>
				<td><input type="text" id="weight" name="weight" /></td>
			</tr>
			<tr>
				<td>详情：</td>
				<td><textarea rows="5" cols="40" id="detail" name="detail"></textarea></td>
			</tr>
			<tr>
				<td><input type="submit" value="增加" /></td>
				<td><input type="button" onclick="cancel(this)" value="取消" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
