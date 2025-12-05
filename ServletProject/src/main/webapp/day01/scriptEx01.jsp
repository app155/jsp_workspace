<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Script Tag</title>
</head>
<%! // 선언문
		int count = 3;

		String makeLower(String data) {
			return data.toLowerCase();
		}
%>
<body>
<%
		for (int i = 1; i <= 3; i++) {
			out.println(i + "<br>");
		}
		// out.println(count++);
%>

<%= 
		makeLower("AaBbCc")
%>
<br>
</body>
</html>