package com.feigle.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.feigle.bean.BannerBean;
import com.feigle.bean.CommodityBean;
import com.feigle.dao.DBUtils;
import com.feigle.util.Utils;

public class BannerSqlUtils {

	public BannerSqlUtils() {
		// TODO Auto-generated constructor stub
	}

	public static List getBannerList() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		List list = new ArrayList();
		try {
			String sql = "SELECT * FROM banner";
			rs = DBUtils.query(con, stmt, rs, sql);
			while (rs.next()) {
				BannerBean bannerBean = new BannerBean();
				bannerBean.setId(rs.getString(1));
				bannerBean.setPath(Utils.SERVER_ADRRESS + "/FeigleShopping" + rs.getString(2));
				bannerBean.setCreateTime(rs.getString(3));
				list.add(bannerBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}

			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
				}

			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
				}
		}
		return list;
	}

	public static boolean delete(String id) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			String sql = "delete from banner where id = " + id;
			int rows = DBUtils.update(con, stmt, rs, sql);
			if (rows > 0)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}

			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
				}

			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
				}
		}
		return flag;
	}

	public static boolean add(String path) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			String sql = "insert into banner(path) values('" + path + "' )";
			int rows = DBUtils.update(con, stmt, rs, sql);
			if (rows > 0)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}

			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
				}

			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
				}
		}
		return flag;
	}
}
