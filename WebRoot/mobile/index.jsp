<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String name = (String) session.getAttribute("name");
	/* if(name == null || name.equals("")){
		response.getWriter().print("<p style=\"font-size:50;position:relative;left:40%;top:40%;color:red;\">用户信息不正确或超时，请重新登录！！</p>");
		Thread.sleep(500);
		response.sendRedirect("login.jsp");
	} */
%>

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
<title>index-DIY官网专注做拖拽式可视化微信小程序、Bootstrap、单页动画、Html5
	WebApp设计工具&lt;/a&gt;</title>
<meta name="author" content="DIY官网 http://www.diygw.com" />
<link rel="stylesheet"
	href="http://localhost:8088/FeigleShopping/css/bootstrap.css" />
<link rel="stylesheet"
	href="http://localhost:8088/FeigleShopping/mobile/static/lib/diygw-mobile-all.min.css" />
<link rel="stylesheet"
	href="http://localhost:8088/FeigleShopping/mobile/static/lib/minirefresh.css" />
<script type="text/javascript"
	src="http://localhost:8088/FeigleShopping/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript"
	src="http://localhost:8088/FeigleShopping/mobile/static/lib/minirefresh.js"></script>
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
<script type="text/javascript">
	var url = "http://localhost:8088/FeigleShopping/";
	function addCar(id){
		$.post(url + "shoppingCartAddForMobileServlet",
    {
        commodityId:id
    },
    function(data,status){
        alert(data);
    });
	}
	</script>
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
	data-page_id="35243" data-page_name="index">
	<div class="container">
		<div class="top">
			<h1 class="page__title" style="background-color:#479de6">
				index<span id="PageBack" class="back">返回</span><a
					class="home fa fa-home ajax-page" id="home" href=".html"></a>
			</h1>
		</div>
		<div class="page" id="page"
			style="background-color:#ffffff;margin-bottom:20px;">
			<div class="pagebg"></div>
			<div class="pagemain">
				<div
					style="font-size: 25px; color: rgb(248, 248, 248); background-color: rgb(41, 111, 50);">
					Feigle客家特产</div>
				<div id="carousel698181" class="swiper-container tool"
					data-nav="false" data-direction="horizontal" data-load="1"
					data-ride="swiper" data-indicator="true" data-circular="true"
					data-autoplay="true" data-duration="1000" data-interval="3000"
					style="height:150px">
					<!-- 轮播（Carousel）项目 -->
					<div class="swiper-wrapper">
						<c:forEach items="${bannerList }" var="bannerBean">
							<div class="item swiper-slide active " id="datatemplate698181">
								<a href="" page=""> <img alt="广告" src="${bannerBean.path }" />
								</a>
							</div>
						</c:forEach>
						<!-- <div class="item swiper-slide  active " id="datatemplate698181">
							<a href="" page=""> <img alt="滑块新闻一"
								src="./assets/images/pic1.jpg" />
							</a>
						</div>
						<div class="item swiper-slide ">
							<a href="" page=""> <img alt="滑块新闻二"
								src="./assets/images/pic2.jpg" />
							</a>
						</div>
						<div class="item swiper-slide ">
							<a href="" page=""> <img alt="滑块新闻三"
								src="./assets/images/pic3.jpg" />
							</a>
						</div> -->
					</div>
					<div class="swiper-pagination swiper-pagination-white"></div>
					<div class="swiper-button-next swiper-button-white"></div>
					<div class="swiper-button-prev swiper-button-white"></div>
				</div>
				<div class="" style="padding:10px">
					<table>
						<c:forEach items="${comodityList }" var="comodityBean">
							<tbody>
								<tr class="firstRow">
									<td width="5" valign="top" rowspan="3">
										<p style="text-align:center">
											<img src="${comodityBean.imgPath }" alt="商品图片" width="120"
												height="120" />
										</p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
									</td>
									<td width="307" valign="top" style="word-break: break-all;">${comodityBean.commodityName }</td>
									<td width="157" valign="top" style="word-break: break-all;">
										<button class="weui-btn weui-btn_primary  " onclick="addCar('${comodityBean.id }')">加入购物车</button>
									</td>
								</tr>
								<tr>
									<td width="6" valign="top" style="word-break: break-all;">库存：${comodityBean.quantity }</td>
									<td width="307" valign="top"><br /></td>
								</tr>
								<tr>
									<td width="6" valign="top" style="word-break: break-all;">价格：${comodityBean.price }</td>
									<td width="307" valign="top"><a data-index="7414130"
										href="/xcx/design/empty.html?dashboardid=22200"
										class="weui-btn weui-btn_primary  ">立即购买</a></td>
								</tr>
								<tr>
									<td colspan="3"><hr style="background-color:gray;" /></td>
								</tr>
							</tbody>
						</c:forEach>
					</table>
					<p>
						<br />
					</p>
				</div>
				<div class="weui-tabbar .tabbar96448" id="tabbar96448"
					style="width: 100%;">
					<a href="/xcx/design/empty.html?dashboardid=22200" page="empty"
						class="weui-tabbar__item ajax-page  weui-bar__item_on "> <img
						src="http://localhost:8088/FeigleShopping/mobile/assets/images/icon1.png"
						img="http://localhost:8088/FeigleShopping/mobile/assets/images/icon1c.png"
						selectimg="http://localhost:8088/FeigleShopping/mobile/assets/images/icon1.png"
						alt="" class="weui-tabbar__icon" style="" />
						<p class="weui-tabbar__label weui-tabbar__label96448">主页</p>
					</a> <a href="mobile/shopping_cart.jsp" page="page538510"
						class="weui-tabbar__item ajax-page "> <img
						src="http://localhost:8088/FeigleShopping/mobile/assets/images/icon2c.png"
						img="http://localhost:8088/FeigleShopping/mobile/assets/images/icon2c.png"
						selectimg="http://localhost:8088/FeigleShopping/mobile/assets/images/icon2.png"
						alt="" class="weui-tabbar__icon" style="" />
						<p class="weui-tabbar__label weui-tabbar__label96448">购车</p>
					</a> <a href="mobile/me.html" page="page185830"
						class="weui-tabbar__item ajax-page "> <img
						src="http://localhost:8088/FeigleShopping/mobile/assets/images/icon3c.png"
						img="http://localhost:8088/FeigleShopping/mobile/assets/images/icon3c.png"
						selectimg="http://localhost:8088/FeigleShopping/mobile/assets/images/icon3.png"
						alt="" class="weui-tabbar__icon" style="" />
						<p class="weui-tabbar__label weui-tabbar__label96448">我的</p>
					</a>
					<style type="text/css">
.tabbar96448 {
	
}

.weui-tabbar__label96448 {
	color: #999999 !important;
	"
}

.weui-tabbar__item.weui-bar__item_on .weui-tabbar__label96448 {
	color: #09bb07 !important;
}
</style>
</body>
</html>