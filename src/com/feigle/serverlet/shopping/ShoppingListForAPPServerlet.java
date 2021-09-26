package com.feigle.serverlet.shopping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feigle.bean.CommodityBean;
import com.feigle.domain.CommoditySqlUtils;
import com.feigle.domain.ShoppingSqlUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ShoppingListForAPPServerlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShoppingListForAPPServerlet() {
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

		ServletInputStream sis = req.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(sis, "utf-8"));
		String json = br.readLine();

		JSONObject jsonObject = JSONObject.fromObject(json);
		String userName = jsonObject.getString("name");
		if (null != userName && !("").equals(userName)) {
			int page = jsonObject.getInt("page");
			
			List list = ShoppingSqlUtils.getCommodityIdList(userName,page);
			List commodityList = new ArrayList();
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				String commodityId = (String) map.get("commodityId");
				CommodityBean commodityBean = CommoditySqlUtils.getComodityById(commodityId);
				if (null != commodityBean) {
					commodityBean.setId((String)map.get("id"));
					commodityList.add(commodityBean);
				}
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
