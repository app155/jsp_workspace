<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="dao" class="com.memberone.StudentDAO" />
<%
	String id = (String)session.getAttribute("loginID");
	String pass = request.getParameter("pass");
	int check = dao.deleteMember(id, pass);
	
	if (check == 1) {
		session.invalidate();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
</head>
<meta http-equiv="refresh" content="3;url=login.jsp">
<body>
<font size="5">
	<b>성공적으로 회원탈퇴가 되었습니다.</b><br>
	3초 후 Login Page로 이동합니다.
</font>
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