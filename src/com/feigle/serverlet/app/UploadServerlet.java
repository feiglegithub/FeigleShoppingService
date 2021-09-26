package com.feigle.serverlet.app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feigle.domain.APPSqlUtils;
import com.feigle.domain.BannerSqlUtils;
import com.feigle.domain.CommoditySqlUtils;
import com.feigle.domain.UserSqlUtils;
import com.feigle.upload.SmartFile;
import com.feigle.upload.SmartFiles;
import com.feigle.upload.SmartRequest;
import com.feigle.upload.SmartUpload;
import com.feigle.upload.SmartUploadException;
import com.feigle.util.Utils;

public class UploadServerlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UploadServerlet() {
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
				SmartUpload smartUpload = new SmartUpload();
				smartUpload.initialize(getServletConfig(), req, resp);
				try {
					smartUpload.upload();
					SmartFiles files = smartUpload.getFiles();
					SmartFile file = files.getFile(0);

					String realPath = getServletContext().getRealPath("/");
					String relativePath = "/apk/FeigleShopping" + Utils.getTimeStamp() + "." + file.getFileExt();
					String path = realPath + relativePath;

					SmartRequest request = smartUpload.getRequest();
					String newVersion = request.getParameter("newVersion");
					String updateLog = request.getParameter("updateLog");
					String targetSize = request.getParameter("targetSize");
					String versionName = request.getParameter("versionName");

					if (file.getSize() > 0 && !newVersion.equals("") && !updateLog.equals("") && !targetSize.equals("")
							&& !versionName.equals("")) {

						file.saveAs(path);
						APPSqlUtils.add(newVersion, relativePath, updateLog, targetSize, versionName);

						req.getRequestDispatcher("/appListServerlet").forward(req, resp);
					} else {
						resp.getWriter().print("请完整输入表单！");
					}
				} catch (SmartUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				resp.getWriter().print("你不是后台工作人员！");
			}
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
