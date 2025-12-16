<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.memberone.*" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="dao" class="com.memberone.StudentDAO" />
<jsp:useBean id="vo" class="com.memberone.StudentVO">
	<jsp:setProperty property="*" name="vo" />
</jsp:useBean>

<%
	String loginID = (String)session.getAttribute("loginID");
	vo.setId(loginID);
	dao.updateMember(vo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
</head>
<meta http-equiv="refresh" content="3;url=login.jsp">
<body>
<font size="5">
	입력하신 내용대로 <b>회원정보가 수정되었습니다.</b><br>
	3초 후 Login Page로 이동합니다.
</font>
</body>
</html>