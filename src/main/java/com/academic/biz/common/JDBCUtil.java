package com.academic.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {
	
	public static Connection getConnection() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			return conn;
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 에러");
		} catch (SQLException e) {
			System.out.println("JDBC 연동 에러");
		}
		return null;
	}
	
	public static void close(Connection conn, PreparedStatement pstmt) {

			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				System.out.println("연결 종료 에러");
			}
	}
	
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		
		try {
			if(rs != null) rs.close();
			if(conn != null) conn.close();
			if(pstmt != null) pstmt.close();
		} catch (SQLException e) {
			System.out.println("연결 종료 에러");
		}
	}
	
}
	
