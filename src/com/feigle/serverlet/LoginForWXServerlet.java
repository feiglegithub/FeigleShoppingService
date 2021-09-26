package com.feigle.serverlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUtils;

import com.feigle.domain.UserSqlUtils;

import net.sf.json.JSONObject;

public class LoginForWXServerlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginForWXServerlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request  the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException      if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request  the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException      if an error occurred
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		resp.setHeader("Conten-type", "text/html;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		String js_code = req.getParameter("code");

		String appid = "wx58e35559ee03c869";
		String secret = "22d00001b5398cfe811dad3a24ee604a";
		String WXLoginStr = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret
				+ "&js_code=" + js_code + "&grant_type=authorization_code";
		String json = com.feigle.util.HttpUtils.doGet(WXLoginStr);
		JSONObject jsonObject = JSONObject.fromObject(json);

		String sessionKey = jsonObject.getString("session_key");
		String openId = jsonObject.getString("openid");

		if (null != sessionKey && !"".equals(sessionKey) && null != openId && !"".equals(openId)) {
//			resp.sendRedirect("index.jsp");
			resp.getWriter().print(openId);
			System.out.println(json);
		} else {
			resp.getWriter().print(false);
		}

	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
