package com.bbs;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/bbs/VisitInsert")
public class VisitInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 처리
		request.setCharacterEncoding("utf-8");
		
		// 클라이언트가 http 요청으로 전송한 값을 읽어옴
		String writer = request.getParameter("writer");
		String memo = request.getParameter("memo");
		
		//System.out.println("작성자: " + writer);
		//System.out.println("내용: " + memo);
		
		String sql = "INSERT INTO VISIT(NO, WRITER, MEMO, REGDATE) VALUES(visit_seq.nextval, ?, ?, sysdate)";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, memo);
			
			pstmt.executeUpdate();
		}
		catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			}
			catch (SQLException e2) {
				e2.printStackTrace();
			}
			
			try {
				if (con != null) {
					con.close();
				}
			}
			catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		response.sendRedirect("VisitList");
	}

}
