<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
</head>
<body>
   <div class="container">
      <h3 id="center">${sessionScope.login }로 로그인 되었습니다.</h3>
      <h3 id="center"><a href="logout">로그아웃</a></h3>
      <h3 id="center"><a href="info?id=${sessionScope.login }">나의 정보</a></h3>
      <c:if test="${sessionScope.login=='admin' }">
         <h3 id="center"><a href="list">회원 목록</a></h3>
      </c:if>
   </div>   
</body>
</html>