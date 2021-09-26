package com.feigle.serverlet.commodity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feigle.bean.CommodityBean;
import com.feigle.domain.CommoditySqlUtils;

import net.sf.json.JSONObject;

public class GetCommodityByIdForAPPServerlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetCommodityByIdForAPPServerlet() {
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("content-type", "text/html;charset=utf-8");

		ServletInputStream sis = request.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(sis, "utf-8"));
		String json = br.readLine();
		JSONObject jsonObject = JSONObject.fromObject(json);

		String userName = jsonObject.getString("userName");

		if (null != userName && !("").equals(userName)) {
			String id = jsonObject.getString("id");
			CommodityBean commodityBean = CommoditySqlUtils.getComodityById(id);

			JSONObject responseObject = JSONObject.fromObject(commodityBean);
			response.getWriter().print(responseObject.toString());
		} else {
			response.getWriter().print(false);
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
