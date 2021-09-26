package com.feigle.serverlet.order;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feigle.domain.OrderSqlUtils;
import com.feigle.domain.UserSqlUtils;

public class UpdateOrderServerlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateOrderServerlet() {
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
		if(null != userName && !("").equals(userName)) {
			if(UserSqlUtils.isBackstageStaff(userName)) {
				String id = req.getParameter("id");
				String name = req.getParameter("name");
				String phone = req.getParameter("phone");
				String address = req.getParameter("address");
				String postalCode = req.getParameter("postalCode");
				String commodity = req.getParameter("commodity");
				String quantity = req.getParameter("quantity");
				String expressNumber = req.getParameter("expressNumber");
				String expressCompany = req.getParameter("expressCompany");
				String status = req.getParameter("status");
				String courierFee = req.getParameter("courierFee");
				String orderNumber = req.getParameter("orderNumber");
				String payment = req.getParameter("payment");

				boolean flag = OrderSqlUtils.update(id, name, phone, address, postalCode, commodity, quantity, expressNumber,
						expressCompany, status, courierFee,orderNumber,payment);
				resp.getWriter().print(flag);
			}else
				resp.getWriter().print("你不是后台工作人员！");
			
		}else {
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
