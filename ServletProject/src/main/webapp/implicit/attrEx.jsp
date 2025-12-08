<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// pageContext Scope에 속성 저장
pageContext.setAttribute("pageAttribute", "홍길동");
//pageContext.setAttribute("pageAttribute", "홍길동", PageContext.PAGE_SCOPE);

// request Scope에 속성 저장
request.setAttribute("requestAttribute", "010-1234-5678");
//pageContext.setAttribute("requestAttribute", "010-1234-5678", PageContext.REQUEST_SCOPE);

// session Scope에 속성 저장
session.setAttribute("sessionAttribute", "hong@naver.com");
//pageContext.setAttribute("sessionAttribute", "hong@naver.com", PageContext.SESSION_SCOPE);

// application Scope에 속성 저장
application.setAttribute("applicationAttribute", "GlobalIn");
//pageContext.setAttribute("applicationAttribute", "GlobalIn", PageContext.APPLICATION_SCOPE);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<ul>
		<li>이름: <%=pageContext.getAttribute("pageAttribute") %></li>
		<li>번호: <%=request.getAttribute("requestAttribute") %></li>
		<li>메일: <%=session.getAttribute("sessionAttribute") %></li>
		<li>회사: <%=application.getAttribute("applicationAttribute") %></li>
	</ul>
</body>
</html>