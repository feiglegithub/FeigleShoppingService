package com.feigle.domain;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.feigle.bean.UserBean;
import com.feigle.dao.DBUtils;

public class UserSqlUtils {

	public static final int ROLE_ADMIN = 0;
	public static final int ROLE_OPERATOR = 1;
	public static final int ROLE_GENERAL_USER = 2;

	// Create a variable for the connection string.
	public static String connectionUrl = "jdbc:sqlserver://127.0.0.1:1433;databaseName=feigle;";// integratedSecurity=true;
	public static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static String dbName = "sa";
	public static String dbPsw = "123456";
	public static Connection con = null;
	public static Statement stmt = null;

	public static String[] roles = new String[] { "管理员", "操作员", "普通用户" };

	public UserSqlUtils() {

	}

	public static boolean isExist(String name, String role) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			String sql = "SELECT * FROM users where name = '" + name + "' and role = '" + role + "'";
			rs = DBUtils.query(con, stmt, rs, sql);

			if (rs.next())
				flag = true;
			else
				flag = false;
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

	/**
	 * 系统后台用户验证
	 * 
	 * @param name 用户名
	 * @param psw  密码
	 * @return 登录结果，普通用户返回false
	 */
	public static boolean userVerify(String name, String psw) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			String sql = "SELECT * FROM users where name = '" + name + "' and psw = '" + psw + "' and role in(0,1)";
			rs = DBUtils.query(con, stmt, rs, sql);

			if (rs.next())
				flag = true;
			else
				flag = false;
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

	public static boolean userVerifyForWX(String name) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			String sql = "SELECT * FROM users where name = '" + name + "' and role = 2";
			rs = DBUtils.query(con, stmt, rs, sql);

			if (rs.next())
				flag = true;
			else
				flag = false;
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

	public static boolean userVerifyForAPP(String name, String psw) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			String sql = "SELECT * FROM users where name = '" + name + "' and psw = '" + psw + "' and role = 2";
			rs = DBUtils.query(con, stmt, rs, sql);

			if (rs.next())
				flag = true;
			else
				flag = false;
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

	public static boolean isAdmin(String name) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			String sql = "SELECT * FROM users where name = '" + name + "' and role = 0";
			rs = DBUtils.query(con, stmt, rs, sql);

			if (rs.next())
				flag = true;
			else
				flag = false;
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

	public static boolean isBackstageStaff(String name) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			String sql = "SELECT * FROM users where name = '" + name + "' and role in(0,1) ";
			rs = DBUtils.query(con, stmt, rs, sql);

			if (rs.next())
				flag = true;
			else
				flag = false;
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

	/**
	 * 获取所有用户信息
	 * 
	 * @return 用户信息列表
	 */
	public static List getUserInfoList() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		List userInfoList = new ArrayList();

		try {
			String sql = "SELECT * FROM users";
			rs = DBUtils.query(con, stmt, rs, sql);
			while (rs.next()) {
				UserBean user = new UserBean();
				user.setId(rs.getString(1));
				user.setName(rs.getString(2));
				user.setRole(roles[rs.getInt(4)]);
				userInfoList.add(user);
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

		return userInfoList;
	}

	public static boolean delete(String id) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			String sql = "delete from users where id = " + id;
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

	public static boolean update(String id, String psw, String role) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			String sql = "update users set psw = '" + psw + "',role = '" + role + "' where id = " + id;
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

	public static boolean updatePsw(String name, String psw) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			String sql = "update users set psw = '" + psw + "' where name = '" + name + "'";
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
	
	public static boolean updatePhone(String name, String phone) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			String sql = "update users set phone = '" + phone + "' where name = '" + name + "'";
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

	public static boolean add(String name, String psw, String role) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			String sql = "insert into users(name,psw,role) values('" + name + "','" + psw + "','" + role + "')";
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

	public static boolean register(String name, String psw, String phone) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			String sql = "insert into users(name,psw,phone,role) values('" + name + "','" + psw + "','" + phone + "','"
					+ ROLE_GENERAL_USER + "')";
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

	public static boolean addForWX(String name, String phone, String role) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			String sql = "insert into users(name,phone,role) values('" + name + "','" + phone + "','" + role + "')";
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
