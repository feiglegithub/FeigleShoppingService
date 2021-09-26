package com.feigle.serverlet.commodity;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feigle.domain.CommoditySqlUtils;
import com.feigle.domain.UserSqlUtils;
import com.feigle.upload.SmartFile;
import com.feigle.upload.SmartFiles;
import com.feigle.upload.SmartRequest;
import com.feigle.upload.SmartUpload;
import com.feigle.upload.SmartUploadException;
import com.feigle.util.Utils;

public class UpdateCommodityServerlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateCommodityServerlet() {
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

		String userName = (String) req.getSession().getAttribute("name");
		if (null != userName && !("").equals(userName)) {
			if (UserSqlUtils.isBackstageStaff(userName)) {
				String id = req.getParameter("id");
				String name = req.getParameter("name");// new String(request.getParameter("name").getBytes("gbk"),
														// "utf-8");
				String price = req.getParameter("price");// new
															// String(request.getParameter("price").getBytes("gbk"),"utf-8");
				String detail = req.getParameter("detail");// new
															// String(request.getParameter("detail").getBytes("ISO-8859-1"),"utf-8");
				String quantity = req.getParameter("quantity");
				String cost = req.getParameter("cost");
				String weight = req.getParameter("weight");

				boolean flag = CommoditySqlUtils.update(id, name, price, detail, quantity, cost, weight);

				resp.getWriter().print(flag);
			} else {
				resp.getWriter().print("你不是后台工作人员！");
			}

		} else {
			resp.sendRedirect("check_login.jsp");
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
