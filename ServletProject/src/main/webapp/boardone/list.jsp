<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.boardone.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="view/color.jsp" %>
<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	int pageSize = 5;
	String pageNum = request.getParameter("pageNum");
	String searchWhat = request.getParameter("searchWhat");
	String searchText = request.getParameter("searchText");
	
	// 받아온 파라미터 값 변환처리
	if (searchText != null) {
		searchText = new String(searchText.getBytes("UTF-8"), "UTF-8");
	}
	
	if (pageNum == null) {
		pageNum = "1";
	}
	
	int currentPage = Integer.parseInt(pageNum);
	int startRow = (currentPage - 1) * pageSize + 1;
	int endRow = currentPage * pageSize;
	int count = 0;
	int number = 0;
	List<BoardVO> articleList = null;
	BoardDAO dbPro = BoardDAO.getInstance();
	
	// 검색 아닐 시 전체 리스트 출력, 검색일시 검색조건에 해당하는 리스트만 출력
	if (searchText == null) {
		count = dbPro.getArticleCount();
		
		if (count > 0) {
			articleList = dbPro.getArticles(startRow, endRow);
		}
	}
	else {
		count = dbPro.getArticleCount(searchWhat, searchText);
		
		if (count > 0) {
			articleList = dbPro.getArticles(searchWhat, searchText, startRow, endRow);
		}
	}
	

	number = count - (currentPage - 1) * pageSize;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="<%=bodyback_c %>">
<div align="center"><b>글 목록(전체 글: <%=count %>)</b>
	<table width="700">
		<tr>
			<td align="right" bgcolor="<%=value_c %>">
				<a href="writeForm.jsp">글쓰기</a>
			</td>
		</tr>
	</table>
	<%
		if (count == 0) {
	%>
	<table width="700" border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td align="center">
				게시판에 저장된 글이 없습니다.
			</td>
		</tr>
	</table>
	<%
		}
		else {
	%>
	<table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
		<tr height="30" bgcolor="<%=value_c %>">
			<td align="center" width="50">번호</td>
			<td align="center" width="250">제목</td>
			<td align="center" width="100">작성자</td>
			<td align="center" width="150">작성일</td>
			<td align="center" width="50">조회수</td>
			<td align="center" width="100">IP</td>
		</tr>
		<%
			for (int i = 0; i < articleList.size(); i++) {
				BoardVO article = articleList.get(i);
		%>
		<tr height="30" bgcolor="<%=value_c %>">
			<td align="center" width="50"><%=number-- %></td>
			<td align="center" width="250">
				<%
					int wid = 0;
					if (article.getDepth() > 0) {	
				%>
				<img src="img/level.gif" width="<%=wid %>" height="16">
				<img src="img/re.gif">
				<%
					}
					else {
				%>
				<img src="img/level.gif" width="<%=wid %>" height="16">
				<%
					}
				%>
				<a href="content.jsp?num=<%=article.getNum() %>&pageNum=1"><%=article.getSubject() %></a>
				<%
					if (article.getReadcount() >= 5) {
				%>
				<img src="img/hot.gif" border="0" height="16">
				<%
					}
				%>
			</td>
			<td align="center" width="100">
				<a href="mailto: <%=article.getEmail() %>"><%=article.getWriter() %></a>
			</td>
			<td align="center" width="150">
				<%=sdf.format(article.getRegdate()) %>
			</td>
			<td align="center" width="50"><%=article.getReadcount() %></td>
			<td align="center" width="100"><%=article.getIp() %></td>
		</tr>
		<%
			}
		%>
	</table>
	<%
		}
		if (count > 0) {
			int pageBlock = 5;
			int temp = count % pageSize == 0 ? 0 : 1;
			int pageCount = count / pageSize + temp;
			
			int startPage = (int)((currentPage - 1) / pageBlock) * pageBlock + 1;
			int endPage = startPage + pageBlock - 1;
			
			if (endPage > pageCount) {
				endPage = pageCount;
			}
			
			// 검색일 경우, 아닐경우 페이징 처리 구분
			if (startPage > pageBlock) {
				if (searchText == null) {
	%>
	<a href="list.jsp?pageNum=<%=startPage - pageBlock %>">[이전]</a>
	<%
				}
				else {
	%>
	<a href="list.jsp?pageNum=<%=startPage - pageBlock %>&searchWhat=<%=searchWhat %>&searchText=<%=searchText %>">[이전]</a>
	<%
				}
			}
			for (int i = startPage; i <= endPage; i++) {
				if (searchText == null) {
	%>
	<a href="list.jsp?pageNum=<%=i %>">[<%=i %>]</a>
	<%
				}
				else {
	%>
	<a href="list.jsp?pageNum=<%=i %>&searchWhat=<%=searchWhat %>&searchText=<%=searchText %>">[<%=i %>]</a>
	<%
				}
			}
			if (endPage < pageCount) {
				if (searchText == null) {
	%>
	<a href="list.jsp?pageNum=<%=startPage + pageBlock %>">[다음]</a>
	<% 
				}
				else {
	%>
	<a href="list.jsp?pageNum=<%=startPage + pageBlock %>&searchWhat=<%=searchWhat %>&searchText=<%=searchText %>">[다음]</a>
	<%
				}
			}
		}
	%>
	<form action="list.jsp" method="post">
		<select name="searchWhat">
			<option value="writer">작성자</option>
			<option value="subject">제목</option>
			<option value="content">내용</option>
		</select>
		<input type="text" name="searchText">
		<input type="submit" value="검색">
	</form>
</div>
</body>
</html>