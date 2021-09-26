package com.feigle.serverlet.shopping;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feigle.bean.CommodityBean;
import com.feigle.domain.CommoditySqlUtils;
import com.feigle.domain.ShoppingSqlUtils;

import net.sf.json.JSONArray;

public class ShoppingListServerlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShoppingListServerlet() {
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

		String userName = req.getParameter("name");
		if (null != userName && !("").equals(userName)) {
			List list = ShoppingSqlUtils.getCommodityIdList(userName,100);
			List commodityList = new ArrayList();
			for (int i = 0; i < list.size(); i++) {
				String commodityId = (String) list.get(i);
				CommodityBean commodityBean = CommoditySqlUtils.getComodityById(commodityId);
				if (null != commodityBean)
					commodityList.add(commodityBean);
			}
			JSONArray jsonArray = JSONArray.fromObject(commodityList);
			resp.getWriter().print(jsonArray.toString());
		} else {
			resp.getWriter().print("false");
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
