package com.feigle.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.feigle.bean.APPBean;
import com.feigle.bean.CommodityBean;
import com.feigle.dao.DBUtils;
import com.feigle.util.Utils;

public class APPSqlUtils {

	public APPSqlUtils() {
		// TODO Auto-generated constructor stub
	}

	public static List<APPBean> getAPPList() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		List<APPBean> list = new ArrayList<APPBean>();
		try {
			String sql = "SELECT * FROM app";
			rs = DBUtils.query(con, stmt, rs, sql);
			while (rs.next()) {
				APPBean appBean = new APPBean();
				appBean.setId(rs.getInt(1));
				appBean.setNewVersion(rs.getInt(2));
				appBean.setApkFileUrl(Utils.SERVER_ADRRESS + "/FeigleShopping" + rs.getString(3));
				appBean.setUpdateLog(rs.getString(4));
				appBean.setTargetSize(rs.getString(5));
				appBean.setCreateTime(rs.getString(6));
				appBean.setVersionName(rs.getString(7));
				list.add(appBean);
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

	public static boolean add(String newVersion, String apkFileUrl, String updateLog, String targetSize,
			String versionName) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			String sql = "insert into app(new_version,apk_file_url,update_log,target_size,version_name) values('"
					+ newVersion + "', '" + apkFileUrl + "', '" + updateLog + "', '" + targetSize + "', '" + versionName
					+ "')";
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
			String sql = "delete from app where id = " + id;
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

	public static APPBean getNewAPP() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		APPBean appBean = new APPBean();
		try {
			String sql = "SELECT * FROM app where new_version in(select max(new_version) from app)";
			rs = DBUtils.query(con, stmt, rs, sql);

			if (rs.next()) {
				appBean.setId(rs.getInt(1));
				appBean.setNewVersion(rs.getInt(2));
				appBean.setApkFileUrl(Utils.SERVER_ADRRESS + "/FeigleShopping" + rs.getString(3));
				appBean.setUpdateLog(rs.getString(4));
				appBean.setTargetSize(rs.getString(5));
				appBean.setCreateTime(rs.getString(6));
				appBean.setVersionName(rs.getString(7));
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
		return appBean;
	}

	public static String getApkPathById(String id) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		String path = "";
		try {
			String sql = "SELECT apk_file_url FROM app where id = " + id;
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

	public static String getNewApkPath() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		String path = "";
		try {
			String sql = "SELECT apk_file_url FROM app where id in(select max(id) from app)";
			rs = DBUtils.query(con, stmt, rs, sql);
			if (rs.next()) {
				path = "/FeigleShopping" +rs.getString(1);
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
