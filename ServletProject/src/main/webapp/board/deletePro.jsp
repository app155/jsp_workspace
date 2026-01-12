<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${check == 1 }">
	<meta http-equiv="refresh" content="0;url=list.bdo?pageNum=${pageNum }">	
</c:if>
<c:if test="${check == 0 }">
	비밀번호가 틀렸습니다.<br>
	<a href="javascript:history.go(-1)">[글삭제폼으로 돌아가기]</a>
</c:if>