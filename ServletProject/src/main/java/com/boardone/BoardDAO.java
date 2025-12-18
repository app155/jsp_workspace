package com.boardone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
	private static BoardDAO instance = null;

	public static BoardDAO getInstance() {
		if (instance == null) {
			synchronized(BoardDAO.class) {
				instance = new BoardDAO();
			}
		}
		
		return instance;
	}
	
	public void insertArticle(BoardVO article) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int num = article.getNum();
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
		
		int number = 0;
		String sql = "";
		
		try {
			con = ConnUtil.getConnection();
			sql = "select max(num) from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				number = rs.getInt(1) + 1;
			}
			else {
				number = 1;
			}
			
			if (num != 0) {
				sql = "update set step = step + 1 where ref = ? and step > ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, step);
				pstmt.executeUpdate();
				step = step + 1;
				depth = depth + 1;
			}
			else {
				ref = number;
				step = 0;
				depth = 0;
			}
			
			sql = "insert into board(num, writer, email, subject, pass, readcount, regdate, ref, step, depth, content, ip) "
					+ "values(board_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getEmail());
			pstmt.setString(3, article.getSubject());
			pstmt.setString(4, article.getPass());
			pstmt.setInt(5, article.getReadcount());
			pstmt.setTimestamp(6, article.getRegdate());
			pstmt.setInt(7, ref);
			pstmt.setInt(8, step);
			pstmt.setInt(9, depth);
			pstmt.setString(10, article.getContent());
			pstmt.setString(11, article.getIp());
			
			pstmt.executeUpdate();
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
	}
	
	// list.jsp에 출력하기 위해 db에서 게시글을 긁어오기
	public int getArticleCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		
		try {
			con = ConnUtil.getConnection();
			String sql = "select count(*) from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				x = rs.getInt(1);
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
	
	// db에서 데이터를 가져와 list에 저장
	public List<BoardVO> getArticles() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> articleList = null;
		
		try {
			con = ConnUtil.getConnection();
			String sql = "select * from board order by num desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				articleList = new ArrayList<BoardVO>();
				
				do {
					BoardVO article = new BoardVO();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPass(rs.getString("pass"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRegdate(rs.getTimestamp("regdate"));
					article.setRef(rs.getInt("ref"));
					article.setStep(rs.getInt("step"));
					article.setDepth(rs.getInt("depth"));
					article.setContent(rs.getString("content"));
					article.setIp(rs.getString("ip"));
					
					articleList.add(article);
				} while (rs.next());
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
		
		return articleList;
	}
	
	// 게시글의 num을 매개변수로 해서 하나의 글에 대한 상세정보를 DB로부터 가져오기
	public BoardVO getArticle(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO article = null;
		
		try {
			con = ConnUtil.getConnection();
			String sql1 = "update board set readcount = readcount + 1 where num = ?";
			pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			String sql2 = "select * from board where num = ?";
			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				article = new BoardVO();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setEmail(rs.getString("email"));
				article.setSubject(rs.getString("subject"));
				article.setPass(rs.getString("pass"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRegdate(rs.getTimestamp("regdate"));
				article.setRef(rs.getInt("ref"));
				article.setStep(rs.getInt("step"));
				article.setDepth(rs.getInt("depth"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
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
		
		return article;
	}
	
	// 수정할 게시글 가져오기
	public BoardVO updateGetArticle(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO article = null;
		
		try {
			con = ConnUtil.getConnection();
			String sql = "select * from board where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				article = new BoardVO();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setEmail(rs.getString("email"));
				article.setSubject(rs.getString("subject"));
				article.setPass(rs.getString("pass"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRegdate(rs.getTimestamp("regdate"));
				article.setRef(rs.getInt("ref"));
				article.setStep(rs.getInt("step"));
				article.setDepth(rs.getInt("depth"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
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
		
		return article;
	}
	
	// 게시글 수정
	public int updateArticle(BoardVO article) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;
		String sql = "";
		String dbPass = "";
		
		try {
			con = ConnUtil.getConnection();
			sql = "select pass from board where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, article.getNum());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dbPass = rs.getString("pass");
				
				if (dbPass.equals(article.getPass())) {
					sql = "update board set writer = ?, email = ?, subject = ?, content = ? where num = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, article.getWriter());
					pstmt.setString(2, article.getEmail());
					pstmt.setString(3, article.getSubject());
					pstmt.setString(4, article.getContent());
					pstmt.setInt(5, article.getNum());
					pstmt.executeUpdate();
					
					result = 1;
				}
				else {
					result = 0;
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
		
		return result;
	}
	
	// 게시글 삭제
	public int deleteArticle(int num, String pass) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;
		String sql = "";
		String dbPass = "";
		
		try {
			con = ConnUtil.getConnection();
			sql = "select pass from board where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dbPass = rs.getString("pass");
				
				if (dbPass.equals(pass)) {
					sql = "delete from board where num = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
					
					result = 1;
				}
				else {
					result = 0;
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
		
		return result;
	}
}
