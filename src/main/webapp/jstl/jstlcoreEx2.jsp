<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL core 태그 2(if, choose)</title>
</head>
<body>
   <h3>조건문 관련 태그 : if, choose 태그</h3>
   <c:if test="${5<10 }"> <!-- else 태그가 없음. -->
      <h4>5는 10보다 작다</h4>
   </c:if>
   <% if(5<10) {%>
         <h4>5는 10보다 작다</h4>
   <% } %>
   <c:choose>
      <c:when test="${5+10==25 }">
         <h4>5+10은 25다</h4>
      </c:when>
      
      <c:when test="${5+10==15 }"> <!-- 여기서 걸리면 밑에는 실행 안함. -->
         <h4>5+10은 15다</h4>
      </c:when>
      
      <c:when test="${5+10==240 }">
         <h4>5+10은 510다</h4>
      </c:when>
      
      <c:otherwise>
         <h4>5+10은 모른다</h4>
      </c:otherwise>
   </c:choose>
   
   <h3>5+25=
   <c:choose>
      <c:when test="${5+25==20 }">
         20
      </c:when>
      
      <c:otherwise>
         30
      </c:otherwise>
   </c:choose>
   </h3>
</body>
</html>