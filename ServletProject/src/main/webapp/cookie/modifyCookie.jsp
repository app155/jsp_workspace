<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.net.URLDecoder" %>
<%
	Cookie[] cookies = request.getCookies();

	if (cookies != null && cookies.length > 0) {
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("name")) {
				cookies[i].setValue(URLEncoder.encode("임꺽정", "UTF-8"));
				response.addCookie(cookies[i]);
			}
		}
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
name 쿠키의 값을 변경합니다.
</body>
</html>