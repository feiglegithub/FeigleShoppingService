package com.feigle.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.feigle.bean.AddressBean;
import com.feigle.dao.DBUtils;

public class AddressSqlUtils {

	public AddressSqlUtils() {
		// TODO Auto-generated constructor stub
	}

	public static boolean add(String name, String contact, String phone, String address, String def) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			if (def.equals("1")) {
				String sql = "update address set def = 0";
				int rows = DBUtils.update(con, stmt, rs, sql);
			}
			String sql = "insert into address values('" + name + "','" + contact + "','" + phone + "','" + address
					+ "','" + def + "')";
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

	public static boolean isExist(String name, String contact, String phone, String address) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			String sql = "SELECT * FROM address where name = '" + name + "' and contact = '" + contact
					+ "' and phone = '" + phone + "' and address = '" + address + "'";
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

	public static List<AddressBean> getAddressList(String name, int page) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		List list = new ArrayList();
		try {
			String sql = "SELECT top " + DBUtils.SIZE * page + " * FROM address where name = '" + name
					+ "' and id not in(select top " + DBUtils.SIZE * (page - 1) + " id from address) order by def desc";
			rs = DBUtils.query(con, stmt, rs, sql);
			while (rs.next()) {
				AddressBean addressBean = new AddressBean();
				addressBean.setId(rs.getString(1));
				addressBean.setContact(rs.getString(3));
				addressBean.setPhone(rs.getString(4));
				addressBean.setAddress(rs.getString(5));
				addressBean.setDef(rs.getString(6));
				list.add(addressBean);
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

	public static AddressBean getDefAddressList(String name) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		AddressBean addressBean = new AddressBean();
		try {
			String sql = "SELECT * FROM address where name = '" + name + "' and def = 1";
			rs = DBUtils.query(con, stmt, rs, sql);
			if (rs.next()) {

				addressBean.setId(rs.getString(1));
				addressBean.setContact(rs.getString(3));
				addressBean.setPhone(rs.getString(4));
				addressBean.setAddress(rs.getString(5));
				addressBean.setDef(rs.getString(6));
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
		return addressBean;
	}

	public static boolean update(String id, String contact, String phone, String address, String def) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			if (def.equals("1")) {
				String sql = "update address set def = 0";
				int rows = DBUtils.update(con, stmt, rs, sql);
			}
			String sql = "update address set contact = '" + contact + "',phone = '" + phone + "',address = '" + address
					+ "',def = '" + def + "' where id = " + id;
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
			String sql = "delete from address where id = " + id;
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
