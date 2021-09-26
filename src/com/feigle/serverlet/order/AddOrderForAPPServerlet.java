package com.feigle.serverlet.order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feigle.domain.OrderSqlUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AddOrderForAPPServerlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddOrderForAPPServerlet() {
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
		try {
			ServletInputStream sis = req.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(sis, "utf-8"));
			String json = br.readLine();

			JSONObject jsonObject = JSONObject.fromObject(json);
			String userName = jsonObject.getString("name");

			if (null != userName && !("").equals(userName)) {
				JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("orders"));
				boolean flag = false;
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);

					String contact = jsonObject1.getString("contact");
					String phone = jsonObject1.getString("phone");
					String address = jsonObject1.getString("address");
					String postalCode = jsonObject1.getString("postalCode");
					String commodity = jsonObject1.getString("commodity");
					String quantity = jsonObject1.getString("quantity");
					String orderNumber = jsonObject1.getString("orderNumber");
					String payment = jsonObject1.getString("payment");

					if (null != contact && !"".equals(contact) && null != phone && !"".equals(phone) && null != address
							&& !"".equals(address) && null != postalCode && null != commodity && !"".equals(commodity)
							&& null != quantity && !"".equals(quantity) && null != orderNumber
							&& !"".equals(orderNumber) && null != payment
									&& !"".equals(payment)) {
						flag = OrderSqlUtils.add(contact, phone, address, postalCode, commodity, quantity, userName,
								orderNumber,payment);
					} else {
						resp.getWriter().print("下单失败！请检查收件人信息是否完整！");
						return;
					}
				}
				if (flag)
					resp.getWriter().print(true);
				else
					resp.getWriter().print("下单失败！");
			} else {
				resp.getWriter().print("用户验证不通过!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().print("服务器处理异常，下单失败");
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
