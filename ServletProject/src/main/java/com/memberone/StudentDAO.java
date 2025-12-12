package com.memberone;
import java.sql.*;
import java.util.Vector;

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
		boolean result = true;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			String sql = "select * from student where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (!rs.next()) {
				result = false;
			}
		}
		catch (SQLException se) {
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
		return result;
	}
	
	// 우편번호를 데이터베이스에서 검색해서 Vector에 저장해서 리턴해주는 메소드
	public Vector<ZipCodeVO> zipcodeRead(String dong) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Vector<ZipCodeVO> vecList = new Vector<ZipCodeVO>();
		
		try {
			con = getConnection();
			String sql = "select * from zipcode where dong like '" + dong + "%'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ZipCodeVO tempZipcode = new ZipCodeVO();
				tempZipcode.setZipcode(rs.getString("zipcode"));
				tempZipcode.setSido(rs.getString("sido"));
				tempZipcode.setGugun(rs.getString("gugun"));
				tempZipcode.setDong(rs.getString("dong"));
				tempZipcode.setRi(rs.getString("ri"));
				tempZipcode.setBunji(rs.getString("bunji"));
				
				vecList.addElement(tempZipcode);
			}
		}
		catch (SQLException se) {
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
		
		return vecList;
	}
}
