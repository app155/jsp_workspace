package com.bbs;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bbs/VisitList")
public class VisitList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset = utf-8");
		PrintWriter out = response.getWriter();
		
		try {
			out.println("<html><head><title>방명록 리스트</title></head><body>");
			
			String sql = "SELECT NO, WRITER, MEMO, REGDATE FROM VISIT ORDER BY NO DESC";
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
				
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					int no = rs.getInt("no");
					String writer = rs.getString("writer");
					String memo = rs.getString("memo");
					Date regdate = rs.getDate("regdate");
					
					out.println("<table align = center width = 500 border = 1>");
					
					out.println("<tr>");
					out.println("<th width = 50>번호</th>");
					out.println("<td width = 50 align = center>" + no + "</td>");
					out.println("<th width = 70>작성자</th>");
					out.println("<td width = 180 align = center>" + writer + "</td>");
					out.println("<th width = 50>작성일</th>");
					out.println("<td width = 100 align = center>" + regdate + "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<th width = 50> 내용 </th>");
					out.println("<td colspan = 5>&nbsp;<textarea rows = 3 cols = 50>" + memo + "</textarea></td>");
					out.println("</tr>");
					
					out.println("</table>");
					out.println("<p></p>");
				}
			}
			catch (ClassNotFoundException ce) {
				ce.printStackTrace();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					if (rs != null) {
						rs.close();
					}
				}
				catch (SQLException e2) {
					e2.printStackTrace();
				}
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
			
			
			out.println("<p align = center><a href = /bbs/write.html>방명록 쓰기</a></p>");
			out.println("</body></html>");
		} 
		finally {
			out.close();
		}
	}
}
