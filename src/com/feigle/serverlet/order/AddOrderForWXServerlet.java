package com.feigle.serverlet.order;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feigle.domain.OrderSqlUtils;

public class AddOrderForWXServerlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddOrderForWXServerlet() {
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
		String userName = req.getParameter("userId");

		if (null != userName && !("").equals(userName)) {
			String name = req.getParameter("name");
			String phone = req.getParameter("phone");
			String address = req.getParameter("address");
			String postalCode = req.getParameter("postalCode");
			String commodity = req.getParameter("commodity");
			String quantity = req.getParameter("quantity");
			String orderNumber = req.getParameter("orderNumber");
			String payment = req.getParameter("payment");

			if (null != name && !"undefined".equals(name) && null != phone && !"undefined".equals(phone)
					&& null != address && !"undefined".equals(address) && null != postalCode
					&& !"undefined".equals(postalCode) && null != commodity && !"undefined".equals(commodity)
					&& null != quantity && !"undefined".equals(quantity)) {
				boolean flag = OrderSqlUtils.add(name, phone, address, postalCode, commodity, quantity, userName,
						orderNumber,payment);
				if (flag)
					resp.getWriter().print("下单成功！");
				else
					resp.getWriter().print("下单失败！");
			} else
				resp.getWriter().print("下单失败！请检查收件人信息是否不完整！");

		} else {
			resp.getWriter().print("用户验证不通过!");
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
