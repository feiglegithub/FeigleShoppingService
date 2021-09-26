package com.feigle.serverlet.mobile.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feigle.domain.UserSqlUtils;

import net.sf.json.JSONObject;

public class RegisterForMobileServerlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public RegisterForMobileServerlet() {
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
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request,response);
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		resp.setHeader("Conten-type", "text/html;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		try {
			String name = req.getParameter("name");
			String psw = req.getParameter("psw");
			String phone = req.getParameter("phone");

			if (UserSqlUtils.isExist(name, String.valueOf(UserSqlUtils.ROLE_GENERAL_USER))) {
				resp.getWriter().print("该用户名已经存在！");
			} else {
				boolean flag = UserSqlUtils.register(name, psw, phone);
				if (flag)
					resp.sendRedirect("mobile/login.html");
				else
					resp.getWriter().print("注册失败");
			}
		} catch (Exception e) {
			resp.getWriter().print("服务器处理异常");
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
