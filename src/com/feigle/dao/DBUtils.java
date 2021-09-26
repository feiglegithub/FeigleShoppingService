package com.feigle.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtils {

	public static final int SIZE = 10;
	
	// Create a variable for the connection string.
//	public static String connectionUrl = "jdbc:sqlserver://127.0.0.1:1433;databaseName=feigle;";// integratedSecurity=true;
//	public static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static String connectionUrl = "jdbc:mysql://localhost:3306/feigle?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
	public static String driverName = "com.mysql.cj.jdbc.Driver";
	public static String dbName = "root";
	public static String dbPsw = "123456";
	public static Connection con = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;

	public DBUtils() {
		// TODO Auto-generated constructor stub
	}

	public static ResultSet query(Connection con, Statement stmt, ResultSet rs, String sql) {

		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(connectionUrl, dbName, dbPsw);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return rs;

	}

	public static int update(Connection con, Statement stmt, ResultSet rs,String sql) {
		int rows = 0;
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(connectionUrl, dbName, dbPsw);
			stmt = con.createStatement();
			rows = stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	public static void close() {
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
}
