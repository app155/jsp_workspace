<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
</head>
<meta http-equiv="refresh" content="3;url=mvcmem.mdo?cmd=login">
<body>
<c:set var="result" value="${result }" />
<c:if test="${result eq 0 }">
	<script type="text/javascript">
		alert("비밀번호가 틀렸습니다.")
		history.go(-1);
	</script>
</c:if>
<font size="5">
	<b>성공적으로 회원탈퇴가 되었습니다.</b><br>
	3초 후 Login Page로 이동합니다.
</font>
</body>
</html>