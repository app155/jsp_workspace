<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.login.LoginDBBean" %>
<%
	request.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	
	LoginDBBean dbPro = LoginDBBean.getInstance();
	
	int check = dbPro.userCheck(id, passwd);
	
	if (check == 1) {
		Cookie cookie = new Cookie("memId", id);
		cookie.setMaxAge(10);
		response.addCookie(cookie);
		response.sendRedirect("cookieLoginConfirm.jsp");
	}
	else if (check == 0) {
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<script type="text/javascript">
	alert("비밀번호가 틀렸습니다.")
	history.go(-1);
</script>
<%
	}
	else {
%>
<script type="text/javascript">
	alert("아이디가 존재하지 않습니다.")
	history.go(-1);
</script>
<%
	}
%>
</body>
</html>