package com.jdbc;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.naming.*;

public class TempMemberDAO {
	
	/*
	private final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private final String USER = "scott";
	private final String PASS = "tiger";
	*/
	
	DataSource ds;
	
	public TempMemberDAO() {
		/* JavaBean으로 연결
		try {
			Class.forName(JDBC_DRIVER);
		}
		catch (Exception e) {
			System.out.println("Error: JDBC 드라이버 로딩 실패");
		}
		*/
		try { // DBCP API로 연결
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myOracle");
		}
		catch (Exception e) {
			System.out.println("Error: JDBC 드라이버 로딩 실패");
		}
	}
	
	public Vector<TempMemberVO> getMemberList() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		Vector<TempMemberVO> vecList = new Vector<TempMemberVO>();
		
		try {
			// con = DriverManager.getConnection(JDBC_URL, USER, PASS);
			con = ds.getConnection();
			String sql = "SELECT * FROM TEMPMEMBER";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				TempMemberVO vo = new TempMemberVO();
				vo.setId(rs.getString("id"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setName(rs.getString("name"));
				vo.setMem_num1(rs.getString("mem_num1"));
				vo.setMem_num2(rs.getString("mem_num2"));
				vo.setE_mail(rs.getString("e_mail"));
				vo.setPhone(rs.getString("phone"));
				vo.setZipcode(rs.getString("zipcode"));
				vo.setAddress(rs.getString("address"));
				vo.setJob(rs.getString("job"));
				vecList.add(vo);
			}
		}
		catch(Exception e) {
			System.out.println("Exception: " + e);
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
			
			if (stmt != null) {
				try {
					stmt.close();
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
		
		return vecList;
	}
	
}
