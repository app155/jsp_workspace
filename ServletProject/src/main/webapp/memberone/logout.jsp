<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.invalidate(); // 세션 무효화 (로그아웃)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Logout</title>
</head>
<meta http-equiv="refresh" content="3;url=login.jsp">
<body>
<font size="5">
	<b>성공적으로 로그아웃 되었습니다.</b><br>
	3초 후 Login Page로 이동합니다.
</font>
</body>
</html>