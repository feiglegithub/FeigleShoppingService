package com.feigle.serverlet.commodity;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feigle.bean.CommodityBean;
import com.feigle.domain.CommoditySqlUtils;
import com.feigle.domain.UserSqlUtils;

import net.sf.json.JSONObject;

public class GetCommodityByIdServerlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public GetCommodityByIdServerlet() {
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
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request,response);
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String userName = (String) request.getSession().getAttribute("name");
		if(null != userName && !("").equals(userName)) {
			if(UserSqlUtils.isBackstageStaff(userName)) {
				String id = request.getParameter("id");
				CommodityBean commodityBean = CommoditySqlUtils.getComodityById(id);

				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				response.setHeader("content-type", "text/html;charset=utf-8");
				
				JSONObject jsonObject = JSONObject.fromObject(commodityBean);
				response.getWriter().print(jsonObject.toString());
			}else {
				response.getWriter().print("????????????????????");
			}
			
		}else {
			response.sendRedirect("check_login.jsp");
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
