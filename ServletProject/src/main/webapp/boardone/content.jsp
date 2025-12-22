<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.boardone.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="view/color.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	try {
		BoardDAO dbPro = BoardDAO.getInstance();
		BoardVO article = dbPro.getArticle(num);
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
%>
<body bgcolor="<%=bodyback_c %>">
<div align="center">
	<b>글 상세보기</b><br>
	<form action="">
		<table width="700" border="1" cellpadding="0" cellspacing="0" align="center" bgcolor="<%=bodyback_c %>">
			<tr height="30">
				<td width="125" bgcolor="<%=value_c %>" align="center">글번호</td>
				<td width="125" align="center"><%=article.getNum() %></td>
				<td width="125" bgcolor="<%=value_c %>" align="center">조회수</td>
				<td width="125" align="center"><%=article.getReadcount() %></td>
			</tr>
			<tr height="30">
				<td width="125" bgcolor="<%=value_c %>" align="center">작성자</td>
				<td width="125" align="center"><%=article.getWriter() %></td>
				<td width="125" bgcolor="<%=value_c %>" align="center">작성일</td>
				<td width="125" align="center"><%=sdf.format(article.getRegdate()) %></td>
			</tr>
			<tr height="30">
				<td width="125" bgcolor="<%=value_c %>" align="center">글제목</td>
				<td width="375" align="center" colspan="3"><%=article.getSubject() %></td>
			</tr>
			<tr height="30">
				<td width="125" bgcolor="<%=value_c %>" align="center">글내용</td>
				<td width="375" align="left" colspan="3">
					<pre><%=article.getContent() %></pre>
				</td>
			</tr>
			<tr height="30">
				<td bgcolor="<%=value_c %>" align="right" colspan="4">
					<input type="button" value="글수정" onclick="document.location.href='updateForm.jsp?num=<%=article.getNum() %>&pageNum=<%=pageNum %>'">&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="글삭제" onclick="document.location.href='deleteForm.jsp?num=<%=article.getNum() %>&pageNum=<%=pageNum %>'">&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="답변" onclick="document.location.href='writeForm.jsp?num=<%=num %>&ref=<%=ref %>&step=<%=step %>&depth=<%=depth %>'">&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="글목록" onclick="document.location.href='list.jsp?pageNum=<%=pageNum %>'">
				</td>
			</tr>
		</table>
		<%
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		%>
	</form>
</div>
</body>
</html> 