package com.feigle.serverlet.banner;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feigle.domain.BannerSqlUtils;
import com.feigle.domain.CommoditySqlUtils;
import com.feigle.domain.UserSqlUtils;
import com.feigle.upload.SmartFile;
import com.feigle.upload.SmartFiles;
import com.feigle.upload.SmartRequest;
import com.feigle.upload.SmartUpload;
import com.feigle.upload.SmartUploadException;
import com.feigle.util.Utils;

public class BannerUploadServerlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public BannerUploadServerlet() {
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

		resp.setHeader("Conten-type", "text/html;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		
		String userName = (String) req.getSession().getAttribute("name");
		if (null != userName && !("").equals(userName)) {
			if (UserSqlUtils.isBackstageStaff(userName)) {
				SmartUpload smartUpload = new SmartUpload();
				smartUpload.initialize(getServletConfig(), req, resp);
				try {
					smartUpload.upload();
					SmartFiles files = smartUpload.getFiles();
					SmartFile file = files.getFile(0);

//					String dir = req.getContextPath() + "/img/";
//					File dirFile = new File(dir);
//					if(!dirFile.exists())
//						dirFile.mkdir();
					String fileName = file.getFileExt();
					
					if(null != fileName && !"".equals(fileName)) {
						String realPath = getServletContext().getRealPath("/");
						String relativePath = "/img/" + Utils.getTimeStamp() + "." + fileName;
						String path = realPath + relativePath;

						SmartRequest request = smartUpload.getRequest();
						file.saveAs(path);

						BannerSqlUtils.add(relativePath);

						req.getRequestDispatcher("/bannerImageListServerlet").forward(req, resp);
					}else {
						resp.getWriter().print("空文件！");
					}
					
				} catch (SmartUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				resp.getWriter().print("你不是后台工作人员！");
			}
		}else {
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
