package com.feigle.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.feigle.bean.CommodityBean;
import com.feigle.dao.DBUtils;
import com.feigle.util.Utils;

import net.sf.json.JSONObject;

public class CommoditySqlUtils {

	private static int size = 10;

	public CommoditySqlUtils() {
		// TODO Auto-generated constructor stub
	}

	public static List getComodityList() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		List list = new ArrayList();
		try {
			String sql = "SELECT * FROM commodity";
			rs = DBUtils.query(con, stmt, rs, sql);
			while (rs.next()) {
				CommodityBean commodityBean = new CommodityBean();
				commodityBean.setId(rs.getString(1));
				commodityBean.setImgPath(Utils.SERVER_ADRRESS + "/FeigleShopping" + rs.getString(2));
				commodityBean.setCommodityName(rs.getString(3));
				commodityBean.setPrice(rs.getString(4));
				commodityBean.setDetail(rs.getString(5));
				commodityBean.setQuantity(rs.getString(6));
				commodityBean.setCreateTime(rs.getString(7));
				commodityBean.setCost(rs.getString(8));
				commodityBean.setWeight(rs.getString(9));
				list.add(commodityBean);
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

	public static List getComodityListForWX(int page) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		List list = new ArrayList();
		try {
			String sql = "SELECT top " + DBUtils.SIZE * page + " * FROM commodity where id not in(select top "
					+ (page - 1) * DBUtils.SIZE + " id from commodity)";
			rs = DBUtils.query(con, stmt, rs, sql);
			while (rs.next()) {
				CommodityBean commodityBean = new CommodityBean();
				commodityBean.setId(rs.getString(1));
				commodityBean.setImgPath(Utils.SERVER_ADRRESS + "/FeigleShopping" + rs.getString(2));
				commodityBean.setCommodityName(rs.getString(3));
				commodityBean.setPrice(rs.getString(4));
				commodityBean.setDetail(rs.getString(5));
				commodityBean.setQuantity(rs.getString(6));
				commodityBean.setCreateTime(rs.getString(7));
//				commodityBean.setCost(rs.getString(8));
				commodityBean.setWeight(rs.getString(9));
				list.add(commodityBean);
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

	public static CommodityBean getComodityById(String id) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		CommodityBean commodityBean = new CommodityBean();
		try {
			String sql = "SELECT * FROM commodity where id = " + id;
			rs = DBUtils.query(con, stmt, rs, sql);
			if (rs.next()) {
				commodityBean.setId(rs.getString(1));
				commodityBean.setImgPath(Utils.SERVER_ADRRESS + "/FeigleShopping" + rs.getString(2));
				commodityBean.setCommodityName(rs.getString(3));
				commodityBean.setPrice(rs.getString(4));
				commodityBean.setDetail(rs.getString(5));
				commodityBean.setQuantity(rs.getString(6));
				commodityBean.setCreateTime(rs.getString(7));
				commodityBean.setCost(rs.getString(8));
				commodityBean.setWeight(rs.getString(9));
			} else
				commodityBean = null;
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
		return commodityBean;
	}

	public static boolean add(String path, String name, String price, String detail, String quantity, String cost,
			String weight) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			String sql = "insert into commodity(img_path,commodity_name,price,detail,quantity,cost,weight) values('"
					+ path + "','" + name + "','" + price + "','" + detail + "','" + quantity + "','" + cost + "','"
					+ weight + "')";
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

	public static boolean delete(String id) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			String sql = "delete from commodity where id = " + id;
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

	public static boolean update(String id, String name, String price, String detail, String quantity, String cost,
			String weight) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			String sql = "update commodity set commodity_name = '" + name + "',price = '" + price + "',quantity = '"
					+ quantity + "',detail = '" + detail + "',cost = '" + cost + "',weight = " + weight + " where id = "
					+ id;
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

	public static boolean isExist(String commodityName) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			String sql = "SELECT * FROM commodity where commodity_name = '" + commodityName + "'";
			rs = DBUtils.query(con, stmt, rs, sql);
			if (rs.next())
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

	public static String getImagePathById(String id) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		String path = "";
		try {
			String sql = "SELECT img_path FROM commodity where id = " + id;
			rs = DBUtils.query(con, stmt, rs, sql);
			if (rs.next()) {
				path = rs.getString(1);
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
		return path;
	}
}
