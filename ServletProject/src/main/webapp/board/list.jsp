<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="view/color.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="${bodyback_c }">
<div align="center"><b>글 목록(전체 글: ${count })</b>
	<table width="700">
		<tr>
			<td align="right" bgcolor="${value_c }">
				<a href="/board/writeForm.bdo">글쓰기</a>
			</td>
		</tr>
	</table>
	<c:if test="${count == 0 }">
		<table width="700" border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td align="center">
					게시판에 저장된 글이 없습니다.
				</td>
			</tr>
		</table>
	</c:if>
	<c:if test="${count > 0 }">
	
	<table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
		<tr height="30" bgcolor="${value_c }">
			<td align="center" width="50">번호</td>
			<td align="center" width="250">제목</td>
			<td align="center" width="100">작성자</td>
			<td align="center" width="150">작성일</td>
			<td align="center" width="50">조회수</td>
			<td align="center" width="100">IP</td>
		</tr>
		<c:forEach var="article" items="${articleList }">
		<tr height="30">
			<td align="center" width="50">
				<c:out value="${number }" />
				<c:set var="number" value="${number - 1 }" />
			</td>
			<td width="250">
				<c:if test="${article.depth > 0 }">
					<img src="img/level.gif" width="${5 * article.depth }" height="16">
					<img src="img/re.gif">
				</c:if>
				<c:if test="${article.depth == 0 }">
					<img src="img/level.gif" width="${5 * article.depth }" height="16">
				</c:if>
				<a href="/board/content.bdo?num=${article.num }&pageNum=${currentPage }">${article.subject }</a>
				<c:if test="${article.readcount >= 10 }">
					<img src="img/hot.gif" border="0" height="16">
				</c:if>
			</td>
			<td align="center" width="100">
				<a href="mailto: ${article.email }">${article.writer }</a>
			</td>
			<td align="center" width="150">
				${article.regdate }
			</td>
			<td align="center" width="50">${article.readcount }</td>
			<td align="center" width="100">${article.ip }</td>
		</tr>
		</c:forEach>
	</table>
	</c:if>
	<%-- 
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
	 --%>
	<form action="/board/list.bdo" method="post">
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