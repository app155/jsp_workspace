<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<c:if test="${3 > 4 }">
	화면 출력X
</c:if>
<c:if test="${param.type eq 'guest' }">
	홈에 오신 것을 환영합니다.<br>
	더 많은 정보를 보려면 회원가입 하시기 바랍니다.
</c:if>
<c:if test="${param.type eq 'member' }">
	회원님의 방문을 환영합니다.<br>
	즐거운 시간 보내시기 바랍니다.
</c:if>
</body>
</html>