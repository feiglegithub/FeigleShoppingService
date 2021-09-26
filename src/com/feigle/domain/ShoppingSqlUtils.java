package com.feigle.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.feigle.bean.OrderBean;
import com.feigle.dao.DBUtils;

public class ShoppingSqlUtils {

	public ShoppingSqlUtils() {
		// TODO Auto-generated constructor stub
	}

	public static boolean add(String name, String commodityId) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			String sql = "insert into shopping_cart(commodity_id,name) values('" + commodityId + "','" + name + "')";
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

	public static boolean isExist(String name, String commodityId) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			String sql = "SELECT * FROM shopping_cart where name = '" + name + "' and commodity_id = " + commodityId;
			rs = DBUtils.query(con, stmt, rs, sql);
			if (rs.next()) {
				flag = true;
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
		return flag;
	}

	public static List<Map> getCommodityIdList(String name, int page) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		List<Map> list = new ArrayList();
		try {
			String sql = "SELECT top " + DBUtils.SIZE * page + " commodity_id,id FROM shopping_cart where name = '"
					+ name + "' and id not in(SELECT top " + DBUtils.SIZE * (page - 1)
					+ " id FROM shopping_cart where name = '" + name + "')";
			rs = DBUtils.query(con, stmt, rs, sql);
			while (rs.next()) {
				Map map = new HashMap();
				map.put("commodityId", rs.getString(1));
				map.put("id", rs.getString(2));
				list.add(map);
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
			String sql = "delete from shopping_cart where id = " + id;
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
