package com.memberone;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class StudentDAO {
	
	private Connection getConnection() {
		Connection con = null;
		
		try { // DBCP API로 연결
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/myOracle");
			con = ds.getConnection();
		}
		catch (Exception e) {
			System.out.println("Error: Connection 생성 실패");
		}
		
		return con;
	}
	
	// 아이디 체크
	public boolean idCheck(String id) {
		return false;
	}
}
