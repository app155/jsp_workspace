<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="view/color.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="${bodyback_c }">
<div align="center">
	<b>글 상세보기</b><br>
	<form action="">
		<table width="700" border="1" cellpadding="0" cellspacing="0" align="center" bgcolor="${bodyback_c }">
			<tr height="30">
				<td width="125" bgcolor="${value_c }" align="center">글번호</td>
				<td width="125" align="center">${article.num }</td>
				<td width="125" bgcolor="${value_c }" align="center">조회수</td>
				<td width="125" align="center">${article.readcount }</td>
			</tr>
			<tr height="30">
				<td width="125" bgcolor="${value_c }" align="center">작성자</td>
				<td width="125" align="center">${article.writer }</td>
				<td width="125" bgcolor="${value_c }" align="center">작성일</td>
				<td width="125" align="center">${article.regdate }</td>
			</tr>
			<tr height="30">
				<td width="125" bgcolor="${value_c }" align="center">글제목</td>
				<td width="375" align="center" colspan="3">${article.subject }</td>
			</tr>
			<tr height="30">
				<td width="125" bgcolor="${value_c }" align="center">글내용</td>
				<td width="375" align="left" colspan="3">
					<pre>${article.content }</pre>
				</td>
			</tr>
			<tr height="30">
				<td bgcolor="${value_c }" align="right" colspan="4">
					<input type="button" value="글수정" onclick="document.location.href='/board/updateForm.bdo?num=${article.num }&pageNum=${pageNum }'">&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="글삭제" onclick="document.location.href='/board/deleteForm.bdo?num=${article.num }&pageNum=${pageNum }'">&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="답변" onclick="document.location.href='/board/writeForm.bdo?num=${article.num }&ref=${article.ref }&step=${article.step }&depth=${article.depth }'">&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="글목록" onclick="document.location.href='/board/list.bdo?pageNum=${pageNum }'">
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html> 