package com.feigle.serverlet.address;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feigle.domain.AddressSqlUtils;

import net.sf.json.JSONObject;

public class UpdateAddressServerlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateAddressServerlet() {
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
			String id = jsonObject.getString("id");
			String contact = jsonObject.getString("contact");
			String phone = jsonObject.getString("phone");
			String address = jsonObject.getString("address");
			String def = jsonObject.getString("def");

			boolean flag = AddressSqlUtils.update(id, contact, phone, address, def);
			if (flag)
				resp.getWriter().print("更新成功");
			else
				resp.getWriter().print("更新失败");

		} catch (Exception e) {
			resp.getWriter().print("服务器处理异常，更新失败");
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
