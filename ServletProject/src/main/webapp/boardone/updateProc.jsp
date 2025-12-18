<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.boardone.*" %>
<%@ page import="java.sql.*" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="article" class="com.boardone.BoardVO">
	<jsp:setProperty name="article" property="*"/>
</jsp:useBean>
<%
	String pageNum = request.getParameter("pageNum");
	BoardDAO boardDAO = BoardDAO.getInstance();
	int check = boardDAO.updateArticle(article);
	
	if (check == 1) {
%>

<!DOCTYPE html>
<html>
<head><meta http-equiv="refresh" content="3;url=login.jsp">
<meta charset="UTF-8">
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