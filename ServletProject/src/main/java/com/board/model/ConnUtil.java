package com.board.model;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class ConnUtil {
	private static DataSource ds;
	
	static {
		try { // DBCP API로 연결
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myOracle");
		}
		catch (NamingException e) {
			System.out.println("Error: Connection 생성 실패");
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
}
