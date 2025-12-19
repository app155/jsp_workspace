<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.boardone.*" %>
<%@ page import="java.sql.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	String pass = request.getParameter("pass");
	
	BoardDAO dbPro = BoardDAO.getInstance();
	int check = dbPro.deleteArticle(num, pass);
	
	if (check == 1) {
%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<head>
<title></title>
</head>
<meta http-equiv="refresh" content="0;url=list.jsp?pageNum=<%=pageNum %>">
<body>
<%
	}
	else {
%>
<script type="text/javascript">
	alert("비밀번호가 틀렸습니다.")
	history.go(-1);
</script>
<%
	}
%>
</body>
</html>