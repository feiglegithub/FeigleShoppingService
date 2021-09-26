package com.feigle.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.feigle.bean.CommodityBean;
import com.feigle.bean.OrderBean;
import com.feigle.dao.DBUtils;
import com.feigle.util.Utils;

public class OrderSqlUtils {
	public static String[] status = new String[] {"待付款", "待发货", "已发货", "已退货", "已取消" };

	public OrderSqlUtils() {
		// TODO Auto-generated constructor stub
	}

	public static List getOrderList() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		List list = new ArrayList();
		try {
			String sql = "SELECT * FROM orders order by create_time";
			rs = DBUtils.query(con, stmt, rs, sql);
			while (rs.next()) {
				OrderBean orderBean = new OrderBean();
				orderBean.setId(rs.getString(1));
				orderBean.setName(rs.getString(2));
				orderBean.setPhone(rs.getString(3));
				orderBean.setAddress(rs.getString(4));
				orderBean.setPostalCode(rs.getString(5));
				orderBean.setCommodityName(rs.getString(6));
				orderBean.setQuantity(rs.getString(7));
				orderBean.setCreateTime(rs.getString(8));
				orderBean.setExpressNumber(rs.getString(9));
				orderBean.setExpressTime(rs.getString(10));
				orderBean.setExpressCompany(rs.getString(11));
				orderBean.setStatus(status[Integer.parseInt(rs.getString(12))]);
				orderBean.setCourierFee(rs.getString(13));
				orderBean.setUserName(rs.getString(14));
				orderBean.setOrderNumber(rs.getString(15));
				orderBean.setPayment(rs.getString(16));
				list.add(orderBean);
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

	public static OrderBean getOrderById(String id) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		OrderBean orderBean = new OrderBean();
		try {
			String sql = "SELECT * FROM orders where id = " + id;
			rs = DBUtils.query(con, stmt, rs, sql);
			if (rs.next()) {
				orderBean.setId(rs.getString(1));
				orderBean.setName(rs.getString(2));
				orderBean.setPhone(rs.getString(3));
				orderBean.setAddress(rs.getString(4));
				orderBean.setPostalCode(rs.getString(5));
				orderBean.setCommodityName(rs.getString(6));
				orderBean.setQuantity(rs.getString(7));
				orderBean.setCreateTime(rs.getString(8));
				orderBean.setExpressNumber(rs.getString(9));
				orderBean.setExpressTime(rs.getString(10));
				orderBean.setExpressCompany(rs.getString(11));
				orderBean.setStatus(status[Integer.parseInt(rs.getString(12))]);
				orderBean.setCourierFee(rs.getString(13));
				orderBean.setUserName(rs.getString(14));
				orderBean.setOrderNumber(rs.getString(15));
				orderBean.setPayment(rs.getString(16));
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
		return orderBean;
	}

	public static List getOrderListByUser(String name) {
		List list = new ArrayList();

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM orders where user_name = '" + name + "' order by create_time desc";
			rs = DBUtils.query(con, stmt, rs, sql);
			while (rs.next()) {
				OrderBean orderBean = new OrderBean();
				orderBean.setId(rs.getString(1));
				orderBean.setName(rs.getString(2));
				orderBean.setPhone(rs.getString(3));
				orderBean.setAddress(rs.getString(4));
				orderBean.setPostalCode(rs.getString(5));
				orderBean.setCommodityName(rs.getString(6));
				orderBean.setQuantity(rs.getString(7));
				orderBean.setCreateTime(rs.getString(8));
				orderBean.setExpressNumber(rs.getString(9));
				orderBean.setExpressTime(rs.getString(10));
				orderBean.setExpressCompany(rs.getString(11));
				orderBean.setStatus(status[Integer.parseInt(rs.getString(12))]);
				list.add(orderBean);
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

	public static boolean update(String id, String name, String phone, String address, String postalCode,
			String commodity, String quantity, String expressNumber, String expressCompany, String status,
			String courierFee, String orderNumber,String payment) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			String sql = "update orders set name = '" + name + "',phone = '" + phone + "',address = '" + address
					+ "',postal_code = '" + postalCode + "',commodity = '" + commodity + "',quantity = '" + quantity
					+ "',express_number = '" + expressNumber + "',express_company = '" + expressCompany + "',status = '"
					+ status + "',express_time = (getdate()),courier_fee = '" + courierFee + "',order_number = '"
					+ orderNumber + "',payment = '" +  payment + "' where id = " + id;
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

	public static boolean add(String name, String phone, String address, String postalCode, String commodity,
			String quantity, String userName, String orderNumber,String payment) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			String sql = "insert into orders(name,phone,address,postal_code,commodity,quantity,status,user_name,"
					+ "order_number,payment) values('"
					+ name + "','" + phone + "','" + address + "','" + postalCode + "','" + commodity + "','" + quantity
					+ "',0,'" + userName + "','" + orderNumber + "','" + payment + "')";
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

	public static List getOrderListBySql(String sql) {
		List list = new ArrayList();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			rs = DBUtils.query(con, stmt, rs, sql);
			while (rs.next()) {
				OrderBean orderBean = new OrderBean();
				orderBean.setId(rs.getString(1));
				orderBean.setName(rs.getString(2));
				orderBean.setPhone(rs.getString(3));
				orderBean.setAddress(rs.getString(4));
				orderBean.setPostalCode(rs.getString(5));
				orderBean.setCommodityName(rs.getString(6));
				orderBean.setQuantity(rs.getString(7));
				orderBean.setCreateTime(rs.getString(8));
				orderBean.setExpressNumber(rs.getString(9));
				orderBean.setExpressTime(rs.getString(10));
				orderBean.setExpressCompany(rs.getString(11));
				orderBean.setStatus(status[Integer.parseInt(rs.getString(12))]);
				orderBean.setCourierFee(rs.getString(13));
				orderBean.setUserName(rs.getString(14));
				orderBean.setOrderNumber(rs.getString(15));
				orderBean.setPayment(rs.getString(16));
				list.add(orderBean);
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
}
