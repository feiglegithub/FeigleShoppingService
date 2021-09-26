package com.feigle.serverlet.order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feigle.dao.DBUtils;
import com.feigle.domain.OrderSqlUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetOrderByUserForAPPServerlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetOrderByUserForAPPServerlet() {
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
				String status = jsonObject.getString("status");
				int page = jsonObject.getInt("page");

				String sql2 = "(select top " + DBUtils.SIZE * (page - 1) + " id from orders where 1=1";
				String sql = "select * from orders where 1=1";
				if (!status.equals("-1")) {
					sql2 += " and status = '" + status + "'";
					sql += " and status = '" + status + "'";
				}

				sql2 += " and user_name = '" + userName + "')";
				sql += " and user_name = '" + userName + "' and id not in" + sql2;
				List list = OrderSqlUtils.getOrderListBySql(sql);

				JSONArray jsonArray = JSONArray.fromObject(list);

				resp.getWriter().print(jsonArray.toString());
			} else {
				resp.getWriter().print("用户验证不通过!");
			}
		} catch (Exception e) {
			e.printStackTrace();
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
