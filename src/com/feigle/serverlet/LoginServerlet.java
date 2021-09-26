package com.feigle.serverlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feigle.domain.UserSqlUtils;

public class LoginServerlet extends HttpServlet {

	public LoginServerlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doGet(req, resp);
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doPost(req, resp);
		req.setCharacterEncoding("utf-8");
		
		resp.setHeader("Conten-type", "text/html;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		String name = req.getParameter("name");
		String psw = req.getParameter("psw");
		
		if (UserSqlUtils.userVerify(name, psw)) {
//			resp.sendRedirect("index.jsp");
			req.getSession().setAttribute("name", name);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		} else {
			resp.getWriter().print(
					"<p style=\"font-size:50;position:absolute;left:30%;top:40%;color:red;\">用户名、密码不正确！<br>或者权限不够，本系统禁止普通用户登入</p>");
		}

	}

}
