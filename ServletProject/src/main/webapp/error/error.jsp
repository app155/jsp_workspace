<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<% response.setStatus(HttpServletResponse.SC_OK); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예외발생</title>
</head>
<body>
	요청하신 처리과정에서 에러 발생<br>
	긴급 복구를 위해 많은 인력을 투입하여<br>
	빠른 시간내에 복구하겠습니다<br>
	에러 타입: <%=exception.getClass().getName() %><br>
	에러 메시지: <b><%=exception.getMessage() %></b>
</body>
</html>