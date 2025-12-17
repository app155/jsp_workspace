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
	article.setRegdate(new Timestamp(System.currentTimeMillis()));
	article.setIp(request.getRemoteAddr());
	BoardDAO boardDAO = BoardDAO.getInstance();
	boardDAO.insertArticle(article);
	response.sendRedirect("list.jsp");
%>