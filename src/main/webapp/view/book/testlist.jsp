<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 방명록 목록 조회하기
   1. db에서 방명록 목록 조회하기
   2. 조회된 내용 화면에 출력하기
--%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 조회</title>
<link rel="stylesheet" href="../../css/main.css" >
</head>
<body>
   <h2 class="w3-center">방명록 조회하기</h2>
   <div class="w3-responsive">
   		<table class="w3-table-all">
   			<tr class="w3-grey">
      			<th>작성자</th>
      			<th>제목</th>
      			<th>내용</th>
   			</tr>
   			<c:forEach var="b" items="${list }">
   			<tr>
      			<td>${b.writer }</td>
     			<td>${b.title }</td>
      			<td>${b.content }</td>
   			</tr>
  			 </c:forEach>
		</table>
   </div>   
</body>
</html>