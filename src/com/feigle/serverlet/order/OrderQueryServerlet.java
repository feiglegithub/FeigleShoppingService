package com.feigle.serverlet.order;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feigle.domain.OrderSqlUtils;
import com.feigle.domain.UserSqlUtils;

public class OrderQueryServerlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public OrderQueryServerlet() {
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
				String status = req.getParameter("queryStatus");
				String userId = req.getParameter("userId");
				String phone = req.getParameter("phone");
				String address = req.getParameter("address");
				String expressNumber = req.getParameter("expressNumber");
				String orderNumber = req.getParameter("orderNumber");

				String sql = "select * from orders where 1=1";
				if (null != status && !status.equals("") && !status.equals("-1"))
					sql += " and status = '" + status + "'";
				if (null != userId && !userId.equals(""))
					sql += " and user_name = '" + userId + "'";
				if (null != phone && !phone.equals(""))
					sql += " and phone = '" + phone + "'";
				if (null != address && !address.equals(""))
					sql += " and address = '" + address + "'";
				if (null != expressNumber && !expressNumber.equals(""))
					sql += " and express_number = '" + expressNumber + "'";
				if(null != orderNumber && !orderNumber.equals(""))
					sql += " and order_number = '" + orderNumber + "'";
				sql += "order by create_time";
				
				List list = OrderSqlUtils.getOrderListBySql(sql);
				req.setAttribute("list", list);
				req.getRequestDispatcher("/order.jsp").forward(req, resp);
			} else
				resp.getWriter().print("你不是后台工作人员！");
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
