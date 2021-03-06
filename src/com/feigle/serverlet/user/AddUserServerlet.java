package com.feigle.serverlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feigle.domain.UserSqlUtils;

public class AddUserServerlet extends HttpServlet {

	public AddUserServerlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setHeader("Conten-type", "text/html;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		String userName = (String) req.getSession().getAttribute("name");
		if(null != userName && !("").equals(userName)) {
			if(UserSqlUtils.isAdmin(userName)) {
				String name = req.getParameter("name");
				String psw = req.getParameter("psw");
				String role = req.getParameter("role");
				
				if(UserSqlUtils.isExist(name, role)) {
					resp.getWriter().print("该用户名已经存在！");
				}else {
					boolean flag = UserSqlUtils.add(name, psw, role);
					resp.getWriter().print(flag);
				}
			}else {
				resp.getWriter().print("没有权限！");
			}
		}else {
			resp.sendRedirect("check_login.jsp");
		}
	}

}
