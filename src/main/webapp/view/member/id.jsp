<%@page import="model.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디찾기</title>
<script>
  function idsend(id) {
	  opener.document.f.id.value = id;
	  self.close();
  }           
</script>
</head>
<body>
    <h3>아이디는 ${id} 입니다.</h3>
    <input type="button" value="아이디전송" onclick="idsend('${id}')">
</body>
</html>
