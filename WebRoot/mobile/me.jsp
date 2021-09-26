<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String
path = request.getContextPath(); String basePath = request.getScheme() +
"://" + request.getServerName() + ":" + request.getServerPort() + path +
"/"; String name = (String) session.getAttribute("name"); /* if(name ==
null || name.equals("")){ response.getWriter().print("
<p style=\"font-size:50;position:relative;left:40%;top:40%;color:red;\">用户信息不正确或超时，请重新登录！！</p>
"); Thread.sleep(500); response.sendRedirect("login.jsp"); } */ %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="title" content="可视化设计工具" />
<meta name="description"
	content="DIY官网做好的拖拽式可视化设计工具，提供最微信小程序设计、Bootstrap页面设计、单页动画设计、WebApp可视化设计、代码生成器及导出工具" />
<meta name="keywords"
	content="DIY官网,diygw.com,做好的拖拽式可视化设计工具,微信小程序设计工具,微信小程序可视化设计工具,可视化代码生成器" />
<title>me-DIY官网专注做拖拽式可视化微信小程序、Bootstrap、单页动画、Html5
	WebApp设计工具&lt;/a&gt;</title>
<meta name="author" content="DIY官网 http://www.diygw.com" />
<link rel="stylesheet"
	href="http://localhost:8088/FeigleShopping/mobile/static/lib/diygw-mobile-all.min.css" />
<script charset="utf-8" type="text/javascript"
	src="http://localhost:8088/FeigleShopping/mobile/static/lib/underscore.js"></script>
<script charset="utf-8" type="text/javascript">
	<%-- _.templateSettings = {
		interpolate : /[\$\{]\{(.+?)\}\}?/g,
		evaluate : /<%([\s\S]+?)%>/g
	}; --%>
	window.GlobalConfig = {
		loginUrl : '/xcx/login/login.html',
		registerUrl : '/xcx/login/register.html',
		loginCheckUrl : '/xcx/login/loginCheck.html'
	};
	window.STATIC_URL = "";
	window.UEDITOR_SERVER_URL = "/ueditor/index";
</script>
<script charset="utf-8" type="text/javascript"
	src="http://localhost:8088/FeigleShopping/mobile/static/lib/diygw-mobile-all.min.js"></script>
<style type="text/css">
.top {
	display: none
}

.page {
	top: 0;
}
</style>
</head>
<body data-ride="animatebox" style="background-color:#ffffff;"
	data-mpid="22200" data-homepage="" data-dashboardid="22200"
	data-page_id="35245" data-page_name="me">
	<div class="container">
		<div class="top">
			<h1 class="page__title" style="background-color:#479de6">
				me<span id="PageBack" class="back">返回</span><a
					class="home fa fa-home ajax-page" id="home" href=".html"></a>
			</h1>
		</div>
		<div class="page" id="page"
			style="background-color:#ffffff;margin-bottom:20px;">
			<div class="pagebg"></div>
			<div class="pagemain">
				<div
					style="font-size: 25px; background-color: rgb(41, 111, 50); color: rgb(255, 255, 255);">
					Feigle客家特产</div>
				<div class="weui-tabbar .tabbar39393" id="tabbar39393"
					style="width: 100%;">
					<a
						href="http://localhost:8088/FeigleShopping/showBannerAndCommodityServerlet"
						page="page810041" class="weui-tabbar__item ajax-page "> <img
						src="http://localhost:8088/FeigleShopping/mobile/assets/images/icon1c.png"
						img="http://localhost:8088/FeigleShopping/mobile/assets/images/icon1c.png"
						selectimg="http://localhost:8088/FeigleShopping/mobile/assets/images/icon1.png"
						alt="" class="weui-tabbar__icon" style="" />
						<p class="weui-tabbar__label weui-tabbar__label39393">主页</p>
					</a> <a href="shopping_cart.jsp" page="page538510"
						class="weui-tabbar__item ajax-page "> <img
						src="http://localhost:8088/FeigleShopping/mobile/assets/images/icon2c.png"
						img="http://localhost:8088/FeigleShopping/mobile/assets/images/icon2c.png"
						selectimg="http://localhost:8088/FeigleShopping/mobile/assets/images/icon2.png"
						alt="" class="weui-tabbar__icon" style="" />
						<p class="weui-tabbar__label weui-tabbar__label39393">购物车</p>
					</a> <a href="/xcx/design/empty.html?dashboardid=22200" page="empty"
						class="weui-tabbar__item ajax-page  weui-bar__item_on "> <img
						src="./assets/images/icon3.png"
						img="http://localhost:8088/FeigleShopping/mobile/assets/images/icon3c.png"
						selectimg="http://localhost:8088/FeigleShopping/mobile/assets/images/icon3.png"
						alt="" class="weui-tabbar__icon" style="" />
						<p class="weui-tabbar__label weui-tabbar__label39393">我的</p>
					</a>
					<style type="text/css">
.tabbar39393 {
	
}

.weui-tabbar__label39393 {
	color: #999999 !important;
	"
}

.weui-tabbar__item.weui-bar__item_on .weui-tabbar__label39393 {
	color: #09bb07 !important;
}
</style>
				</div>
				<div class="">
					<table>
						<tbody>
							<tr class="firstRow">
								<td width="45" valign="top"><img
									src="http://lib.diygw.com/upload/18023/image/20191017/1571301750922.png"
									title="1571301750922.png" alt="1571301750922.png" width="50"
									height="50" /></td>
								<td width="443" valign="top"><a data-index="7414130"
									href="./login.html" class="weui-btn weui-btn_plain-primary"
									style="">点击登录/注册</a></td>
							</tr>
						</tbody>
					</table>
					<p>
						<br />
					</p>
				</div>
				<div class="">
					<table>
						<tbody>
							<tr class="firstRow">
								<td width="157" valign="top">
									<p style="text-align:center">
										<img
											src="http://lib.diygw.com/upload/18023/image/20191018/all_order.png"
											title="all_order.png" alt="all_order.png" />
									</p>
								</td>
								<td width="157" valign="top">
									<p style="text-align:center">
										<img
											src="http://lib.diygw.com/upload/18023/image/20191018/wait_pay.png"
											title="wait_pay.png" alt="wait_pay.png" />
									</p>
								</td>
								<td width="157" valign="top">
									<p style="text-align:center">
										<img
											src="http://lib.diygw.com/upload/18023/image/20191018/wait_receive.png"
											title="wait_receive.png" alt="wait_receive.png" />
									</p>
								</td>
							</tr>
						</tbody>
					</table>
					<p>
						<br />
					</p>
				</div>
				<div class="">
					<table>
						<tbody>
							<tr class="firstRow">
								<td width="5" valign="top"><img
									src="http://lib.diygw.com/upload/18023/image/20191018/location.png"
									title="location.png" alt="location.png" /></td>
								<td width="430" valign="top" style="word-break: break-all;">地址管理</td>
								<td width="5" valign="top"><img
									src="http://lib.diygw.com/upload/18023/image/20191018/go.png"
									title="go.png" alt="go.png" /></td>
							</tr>
							<tr>
								<td colspan="3"><hr style="background-color:gray;" /></td>
							</tr>
						</tbody>
					</table>
					<p>
						<br />
					</p>
				</div>
				<div class="">
					<table>
						<tbody>
							<tr class="firstRow">
								<td width="5" valign="top"><img
									src="http://lib.diygw.com/upload/18023/image/20191018/pay_guide.png"
									title="pay_guide.png" alt="pay_guide.png" /></td>
								<td width="430" valign="top" style="word-break: break-all;">支付指引</td>
								<td width="5" valign="top"><img
									src="http://lib.diygw.com/upload/18023/image/20191018/go.png"
									title="go.png" alt="go.png" /></td>
							</tr>
							<tr>
								<td colspan="3"><hr style="background-color:gray;" /></td>
							</tr>
						</tbody>
					</table>
					<p>
						<br />
					</p>
				</div>
				<div class="">
					<table>
						<tbody>
							<tr class="firstRow">
								<td width="5" valign="top"><img
									src="http://lib.diygw.com/upload/18023/image/20191018/cooperation.png"
									title="cooperation.png" alt="cooperation.png" /></td>
								<td width="430" valign="top" style="word-break: break-all;">合作洽谈</td>
								<td width="5" valign="top"><img
									src="http://lib.diygw.com/upload/18023/image/20191018/go.png"
									title="go.png" alt="go.png" /></td>
							</tr>
							<tr>
								<td colspan="3"><hr style="background-color:gray;" /></td>
							</tr>
						</tbody>
					</table>
					<p>
						<br />
					</p>
				</div>
				<div class="">
					<table>
						<tbody>
							<tr class="firstRow">
								<td width="5" valign="top"><img
									src="http://lib.diygw.com/upload/18023/image/20191018/service.png"
									title="service.png" alt="service.png" /></td>
								<td width="430" valign="top" style="word-break: break-all;">客服反馈</td>
								<td width="5" valign="top"><img
									src="http://lib.diygw.com/upload/18023/image/20191018/go.png"
									title="go.png" alt="go.png" /></td>
							</tr>
							<tr>
								<td colspan="3"><hr style="background-color:gray;" /></td>
							</tr>
						</tbody>
					</table>
					<p>
						<br />
					</p>
				</div>
				<div class="">
					<table>
						<tbody>
							<tr class="firstRow">
								<td width="5" valign="top"><img
									src="http://lib.diygw.com/upload/18023/image/20191018/setting.png"
									title="setting.png" alt="setting.png" /></td>
								<td width="430" valign="top" style="word-break: break-all;">设置</td>
								<td width="5" valign="top"><img
									src="http://lib.diygw.com/upload/18023/image/20191018/go.png"
									title="go.png" alt="go.png" /></td>
							</tr>
							<tr>
								<td colspan="3"><hr style="background-color:gray;" /></td>
							</tr>
						</tbody>
					</table>
					<p>
						<br />
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>