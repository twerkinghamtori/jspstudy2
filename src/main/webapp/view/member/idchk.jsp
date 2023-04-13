<%@page import="model.Member"%>
<%@page import="model.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${empty mem }">
	${param.id }<br>
	<h5 style="color:green">사용가능한 아이디 입니다.</h5>
	<input type="button" value="닫기" onclick="javascript:self.close()">
</c:if>

<c:if test="${!empty mem }">
	<script>
    	opener.document.f.id.value="";
	</script>
	${mem.id }<br>
	<h5 style="color:red">사용할 수 없는 아이디 입니다.</h5>
	<input type="button" value="닫기" onclick="javascript:self.close()">
</c:if>

</body>
</html>