package com.feigle.serverlet.address;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feigle.bean.AddressBean;
import com.feigle.domain.AddressSqlUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetAddressListServerlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetAddressListServerlet() {
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
			String name = jsonObject.getString("name");
			int page = jsonObject.getInt("page");

			List<AddressBean> list = AddressSqlUtils.getAddressList(name, page);

			JSONArray jsonArray = JSONArray.fromObject(list);

			resp.getWriter().print(jsonArray);

		} catch (Exception e) {
			resp.getWriter().print("服务器处理异常");
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
