<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="view/color.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="script.js"></script>
</head>
<body bgcolor="${bodyback_c }">
<div align="center">
	<b>글삭제</b><br>
	<form action="/board/deletePro.bdo?pageNum=${pageNum }" name="delForm" method="post" onsubmit="return deleteSave()">
		<table width="360" border="1" align="center" cellspacing="0" cellpadding="0">
			<tr height="30">
				<td align="center" colspan="2" bgcolor="${value_c }"><b>비밀번호 입력</b></td>
			</tr>
			<tr height="30">
				<td align="center">
					비밀번호: <input type="password" name="pass" size="15" maxlength="15">
					<input type="hidden" name="num" value="${num }">
				</td>
				<td align="center" bgcolor="${value_c }">
					<input type="submit" value="글삭제">
					&nbsp;&nbsp;
					<input type="button" value="글목록" onclick="document.location.href='/board/list.bdo?pageNum=${pageNum }'">
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>