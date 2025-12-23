package com.login;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;


public class LoginDBBean {
	private static LoginDBBean instance = new LoginDBBean();

	public static LoginDBBean getInstance() {
		return instance;
	}
	
	private LoginDBBean() { }
	
	private Connection getConnection() throws Exception {
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/myOracle");
		
		return ds.getConnection();
	}
	
	public int userCheck(String id, String passwd) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		String dbPass = "";
		
		try {
			con = getConnection();
			String sql = "select passwd from tempmember where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dbPass = rs.getString("passwd");
				if (dbPass.equals(passwd)) {
					x = 1;
				}
				else {
					x = 0;
				}
			}
			
		}
		catch (Exception se) {
			se.printStackTrace();
		}
		
		finally {
			if (rs != null) {
				try {
					rs.close();
				}
				catch (SQLException ss) {
					ss.printStackTrace();
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				}
				catch (SQLException ss) {
					ss.printStackTrace();
				}
			}
			
			if (con != null) {
				try {
					con.close();
				}
				catch (SQLException ss) {
					ss.printStackTrace();
				}
			}
		}
		
		return x;
	}
}
