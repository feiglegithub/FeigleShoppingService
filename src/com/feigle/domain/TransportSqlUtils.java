package com.feigle.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.feigle.bean.AddressBean;
import com.feigle.bean.CommodityBean;
import com.feigle.bean.TransportBean;
import com.feigle.dao.DBUtils;
import com.feigle.util.Utils;

public class TransportSqlUtils {

	public TransportSqlUtils() {
		// TODO Auto-generated constructor stub
	}

	public static boolean add(String name, String def, String price, String enable) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			if (enable.equals("1")) {
				String sql = "update transport set enable = 0";
				int rows = DBUtils.update(con, stmt, rs, sql);
			}
			String sql = "insert into transport values('" + name + "','" + def + "','" + price + "','" + enable + "')";
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

	public static List<TransportBean> getTransportList() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		List list = new ArrayList();
		try {
			String sql = "SELECT * FROM transport";
			rs = DBUtils.query(con, stmt, rs, sql);
			while (rs.next()) {
				TransportBean transportBean = new TransportBean();
				transportBean.setId(Integer.parseInt(rs.getString(1)));
				transportBean.setName(rs.getString(2));
				transportBean.setDef(Integer.parseInt(rs.getString(3)));
				transportBean.setPrice(Integer.parseInt(rs.getString(4)));
				transportBean.setEnable(Integer.parseInt(rs.getString(5)));
				list.add(transportBean);
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

	public static TransportBean geEnableTransport() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		TransportBean transportBean = new TransportBean();
		try {
			String sql = "SELECT * FROM transport where enable = 1";
			rs = DBUtils.query(con, stmt, rs, sql);
			if (rs.next()) {
				transportBean.setId(Integer.parseInt(rs.getString(1)));
				transportBean.setName(rs.getString(2));
				transportBean.setDef(Integer.parseInt(rs.getString(3)));
				transportBean.setPrice(Integer.parseInt(rs.getString(4)));
				transportBean.setEnable(Integer.parseInt(rs.getString(5)));
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
		return transportBean;
	}

	public static boolean update(String id, String name, String def, String price, String enable) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			if (enable.equals("1")) {
				String sql = "update transport set enable = 0";
				int rows = DBUtils.update(con, stmt, rs, sql);
			}
			String sql = "update transport set name = '" + name + "',def = '" + def + "',price = '" + price
					+ "',enable = '" + enable + "' where id = " + id;
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
			String sql = "delete from transport where id = " + id;
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

	public static TransportBean getTransportById(String id) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		TransportBean transportBean = new TransportBean();
		try {
			String sql = "SELECT * FROM transport where id = " + id;
			rs = DBUtils.query(con, stmt, rs, sql);
			if (rs.next()) {
				transportBean.setId(Integer.parseInt(rs.getString(1)));
				transportBean.setName(rs.getString(2));
				transportBean.setDef(Integer.parseInt(rs.getString(3)));
				transportBean.setPrice(Integer.parseInt(rs.getString(4)));
				transportBean.setEnable(Integer.parseInt(rs.getString(5)));
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
		return transportBean;
	}
	
	public static TransportBean getEnableTransport() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		TransportBean transportBean = new TransportBean();
		try {
			String sql = "SELECT * FROM transport where enable = 1";
			rs = DBUtils.query(con, stmt, rs, sql);
			if (rs.next()) {
				
				transportBean.setId(Integer.parseInt(rs.getString(1)));
				transportBean.setName(rs.getString(2));
				transportBean.setDef(Integer.parseInt(rs.getString(3)));
				transportBean.setPrice(Integer.parseInt(rs.getString(4)));
				transportBean.setEnable(Integer.parseInt(rs.getString(5)));
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
		return transportBean;
	}
}
