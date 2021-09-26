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

	function showUpdateDialog(orderId) {
		$("#updateDialog").show();
		$("#addDialog").hide();
		$("#id").text("修改id:" + orderId + "订单信息");

		$.post("getOrderByIdServerlet", {
			id : orderId
		}, function(data, status) {
			jsonObj = JSON.parse(data);
			$("#updateName").val(jsonObj.name);
			$("#updatePhone").val(jsonObj.phone);
			$("#updateAddress").val(jsonObj.address);
			$("#updatePostalCode").val(jsonObj.postalCode);
			$("#updateCommodity").val(jsonObj.commodityName);
			$("#updateQuantity").val(jsonObj.quantity);
			$("#updateExpressNumber").val(jsonObj.expressNumber);
			$("#updateExpressCompany").val(jsonObj.expressCompany);
			$("#updateStatus").val(jsonObj.status);
			$("#updateCourierFee").val(jsonObj.courierFee);
			$("#updateOrderNumber").val(jsonObj.orderNumber);
			$("#updatePayment").val(jsonObj.payment);
		});

		$("#update").click(function() {
			if ($("#updateName").val() == "" || $("#updatePhone").val() == "" || $("#updateAddress").val() == ""
				|| $("#updatePostalCode").val() == "" || $("#updateCommodity").val() == "" || $("#updateQuantity").val() == "") {
				alert("参数不能为空");
			} else {
				$.post("updateOrderServerlet", {
					id : orderId,
					name : $("#updateName").val(),
					phone : $("#updatePhone").val(),
					address : $("#updateAddress").val(),
					postalCode : $("#updatePostalCode").val(),
					commodity : $("#updateCommodity").val(),
					quantity : $("#updateQuantity").val(),
					expressNumber : $("#updateExpressNumber").val(),
					expressCompany : $("#updateExpressCompany").val(),
					courierFee : $("#updateCourierFee").val(),
					status : $("#updateStatus").val(),
					orderNumber : $("#updateOrderNumber").val(),
					payment : $("#updatePayment").val()
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

	function add() {
		$.post("addOrderServerlet", {
			name : $("#addName").val(),
			phone : $("#addPhone").val(),
			address : $("#addAddress").val(),
			postalCode : $("#addPostalCode").val(),
			commodity : $("#addCommodity").val(),
			quantity : $("#addQuantity").val(),
			orderNumber : $("#addOrderNumber").val(),
			total : $("#addPayment").val()
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
	<form action="orderQueryServerlet">
		<select id="queryStatus" name="queryStatus">
			<option value="-1">全部</option>
			<option value="0">待付款</option>
			<option value="1">待发货</option>
			<option value="2">已发货</option>
			<option value="3">已退货</option>
			<option value="4">已取消</option>
		</select> 
		<input type="text" placeholder="用户id" name="userId" /> 
		<input
			type="text" placeholder="收件人名字" name="name" /> 
			<input type="text"
			placeholder="收件人手机" name="phone" /> 
			<input type="text"
			placeholder="收件人地址" name="address" /> 
			<input type="text"
			placeholder="快递单号" name="expressNumber" /> 
			<input type="text"
			placeholder="订单号" name="orderNumber" /> 
			<input type="submit"
			value="查询">
	</form>
	<table width="100%">
		<tr align="center" style="background-color:#990000">
			<th>id</th>
			<th>用户id</th>
			<th>收件人</th>
			<th>收件人手机</th>
			<th>收件人地址</th>
			<th>邮编</th>
			<th>商品</th>
			<th>数量</th>
			<th>订单号</th>
			<th>订单金额</th>
			<th>下单时间</th>
			<th>快递单号</th>
			<th>快递公司</th>
			<th>快递费</th>
			<th>状态时间</th>
			<th>订单状态</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list }" var="orderBean">
			<tr align="center" style="background-color:#0099FF">
				<td>${orderBean.id }</td>
				<td>${orderBean.userName }</td>
				<td>${orderBean.name }</td>
				<td>${orderBean.phone }</td>
				<td>${orderBean.address }</td>
				<td>${orderBean.postalCode }</td>
				<td>${orderBean.commodityName }</td>
				<td>${orderBean.quantity }</td>
				<td>${orderBean.orderNumber }</td>
				<td>${orderBean.payment }</td>
				<td>${orderBean.createTime }</td>
				<td>${orderBean.expressNumber }</td>
				<td>${orderBean.expressCompany }</td>
				<td>${orderBean.courierFee }</td>
				<td>${orderBean.expressTime }</td>
				<td>${orderBean.status }</td>
				<td><button onclick="showUpdateDialog(${orderBean.id })">修改</button>
					<button onclick="deleteCommodity(${orderBean.id })"
						style="display:none">删除</button></td>
			</tr>
		</c:forEach>
	</table>

	<table id="updateDialog"
		style="background-color:#FFCC33;position:absolute;left:40%;top:10%;display:none">
		<tr>
			<th id="id">修改订单信息</th>
		</tr>
		<tr>
			<td>收件人：</td>
			<td><input type="text" id="updateName" name="name" /></td>
		</tr>
		<tr>
			<td>收件人手机：</td>
			<td><input type="text" id="updatePhone" name="phone" /></td>
		</tr>
		<tr>
			<td>收件人地址：</td>
			<td><textarea rows="5" cols="25" id="updateAddress"
					name="address"></textarea></td>
		</tr>
		<tr>
			<td>邮编：</td>
			<td><input type="text" id="updatePostalCode" name="postalCode" /></td>
		</tr>
		<tr>
			<td>商品：</td>
			<td><input type="text" id="updateCommodity" name="commodity" /></td>
		</tr>
		<tr>
			<td>数量：</td>
			<td><input type="text" id="updateQuantity" name="quantity" /></td>
		</tr>
		<tr>
			<td>订单号：</td>
			<td><input type="text" id="updateOrderNumber" name="orderNumber" /></td>
		</tr>
		<tr>
			<td>订单金额：</td>
			<td><input type="text" id="updatePayment" name="payment" /></td>
		</tr>
		<tr>
			<td>快递单号：</td>
			<td><input type="text" id="updateExpressNumber"
				name="expressNumber" /></td>
		</tr>
		<tr>
			<td>快递公司：</td>
			<td><input type="text" id="updateExpressCompany"
				name="expressCompany" /></td>
		</tr>
		<tr>
			<td>快递费用：</td>
			<td><input type="text" id="updateCourierFee" name="courierFee" /></td>
		</tr>
		<tr>
			<td>订单状态：</td>
			<td><select id="updateStatus">
					<option value="0">待付款</option>
					<option value="1">待发货</option>
					<option value="2">已发货</option>
					<option value="3">已退货</option>
					<option value="4">已取消</option>
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
			<th>新增订单</th>
		</tr>
		<tr>
			<td>收件人：</td>
			<td><input type="text" id="addName" /></td>
		</tr>
		<tr>
			<td>收件人手机：</td>
			<td><input type="text" id="addPhone" /></td>
		</tr>
		<tr>
			<td>收件人地址：</td>
			<td><input type="text" id="addAddress" /></td>
		</tr>
		<tr>
			<td>邮编：</td>
			<td><input type="text" id="addPostalCode" /></td>
		</tr>
		<tr>
			<td>商品：</td>
			<td><input type="text" id="addCommodity" /></td>
		</tr>
		<tr>
			<td>数量：</td>
			<td><input type="text" id="addQuantity" /></td>
		</tr>
		<tr>
			<td>订单号：</td>
			<td><input type="text" id="addOrderNumber" /></td>
		</tr>
		<tr>
			<td>订单金额：</td>
			<td><input type="text" id="addPayment" /></td>
		</tr>
		<tr>
			<td><button id="add" onclick="add()">增加</button></td>
			<td><button onclick="cancel(this)">取消</button></td>
		</tr>
	</table>
</body>
</html>
