<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<!DOCTYPE html>
<html>
 <head> 
  <meta charset="utf-8" /> 
  <meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
  <meta name="title" content="" /> 
  <meta name="description" content="" /> 
  <meta name="keywords" content="" /> 
  <title></title> 
  <meta name="author" content="http://www.diygw.com" /> 
  <link rel="stylesheet" href="./static/lib/diygw-mobile-all.min.css" /> 
  <script charset="utf-8" type="text/javascript" src="./static/lib/underscore.js"></script> 
  <script charset="utf-8" type="text/javascript">
	<%-- _.templateSettings = {
		interpolate : /[\$\{]\{(.+?)\}\}?/g,
		evaluate : /<%([\s\S]+?)%>/g
	}; --%>
	window.GlobalConfig={
		loginUrl:'/xcx/login/login.html',
		registerUrl:'/xcx/login/register.html',
		loginCheckUrl:'/xcx/login/loginCheck.html'
	};
	window.STATIC_URL = "";
	window.UEDITOR_SERVER_URL = "/ueditor/index";
	</script> 
  <script charset="utf-8" type="text/javascript" src="./static/lib/diygw-mobile-all.min.js"></script> 
  <style type="text/css">
	
	.top{display:none}.page{top:0;}
	</style> 
 </head> 
 <body data-ride="animatebox" style="background-color:#ffffff;" data-mpid="22200" data-homepage="" data-dashboardid="22200" data-page_id="35244" data-page_name="shopping_cart"> 
  <div class="container"> 
   <div class="top">
    <h1 class="page__title" style="background-color:#479de6">shopping_cart<span id="PageBack" class="back">è¿å</span><a class="home fa fa-home ajax-page" id="home" href=".html"></a></h1>
   </div> 
   <div class="page" id="page" style="background-color:#ffffff;margin-bottom:20px;"> 
    <div class="pagebg"></div> 
    <div class="pagemain"> 
     <div style="font-size: 25px; background-color: rgb(41, 111, 50); color: rgb(255, 255, 255);">
       Feigle客家特产
     </div> 
     <div class="weui-tabbar .tabbar366581" id="tabbar366581" style="width: 100%;"> 
      <a href="http://localhost:8088/FeigleShopping/showBannerAndCommodityServerlet" page="page810041" class="weui-tabbar__item ajax-page "> <img src="./assets/images/icon1c.png" img="./assets/images/icon1c.png" selectimg="./assets/images/icon1.png" alt="" class="weui-tabbar__icon" style="" /> <p class="weui-tabbar__label weui-tabbar__label366581">主页</p> </a> 
      <a href="/xcx/design/empty.html?dashboardid=22200" page="empty" class="weui-tabbar__item ajax-page  weui-bar__item_on "> <img src="./assets/images/icon2.png" img="./assets/images/icon2c.png" selectimg="./assets/images/icon2.png" alt="" class="weui-tabbar__icon" style="" /> <p class="weui-tabbar__label weui-tabbar__label366581">购物车</p> </a> 
      <a href="me.html" page="page185830" class="weui-tabbar__item ajax-page "> <img src="./assets/images/icon3c.png" img="./assets/images/icon3c.png" selectimg="./assets/images/icon3.png" alt="" class="weui-tabbar__icon" style="" /> <p class="weui-tabbar__label weui-tabbar__label366581">我</p> </a> 
      <style type="text/css">
            .tabbar366581 {}
            
            .weui-tabbar__label366581 {
                color: #999999 !important;
                "

            }
            
            .weui-tabbar__item.weui-bar__item_on .weui-tabbar__label366581 {
                color: #09bb07 !important;
            }
        </style> 
     </div> 
     <div class=""> 
      <table> 
       <tbody> 
        <tr class="firstRow"> 
         <td width="112" valign="top" rowspan="3"><br /></td> 
         <td width="112" valign="top" rowspan="3"><img src="http://lib.diygw.com/upload/18023/image/20191017/1571301257411.png" title="1571301257411.png" alt="1571301257411.png" width="50" height="50" /></td> 
         <td width="112" valign="top" style="word-break: break-all;">商品名称</td> 
         <td width="112" valign="top"><br /></td> 
        </tr> 
        <tr> 
         <td width="112" valign="top" style="word-break: break-all;">价格</td> 
         <td width="112" valign="top"><br /></td> 
        </tr> 
        <tr> 
         <td width="112" valign="top" style="word-break: break-all;">库存</td> 
         <td width="112" valign="top"><br /></td> 
        </tr> 
       </tbody> 
      </table> 
      <p><br /></p> 
     </div> 
     <form class="form-horizontal ui-sortable formtable_333036" data-listhref="" data-ride="form" role="form" id="form333036" name="table_333036" data-url="/data/data.html" action="/data/save.html" enctype="multipart/form-data" method="post" data-bv-group=".weui-cell" data-bv-container="#errorsform333036" data-bv-feedbackicons-valid="weui-icon-success" data-bv-feedbackicons-invalid="weui-icon-warn" data-bv-feedbackicons-validating="weui-icon-warn"> 
      <div class="row-form clearfix"> 
       <div class=""> 
        <div class="weui-form"> 
         <div class="weui-form-item"> 
          <a data-index="7414130" href="/xcx/design/empty.html?dashboardid=22200" class="weui-btn weui-btn_primary  ">按钮</a> 
         </div> 
        </div> 
       </div> 
      </div> 
      <input type="hidden" class="hide" name="dashboardId" value="22200" /> 
      <input type="hidden" class="hide" name="formId" value="333036" /> 
      <input type="hidden" class="hide" name="tableName" value="table_333036" /> 
      <input type="hidden" class="hide" name="id" value="" /> 
      <div id="errorsform333036" class="hide"></div> 
     </form> 
    </div> 
   </div> 
  </div>   
 </body>
</html>