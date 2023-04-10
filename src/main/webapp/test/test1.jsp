<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>두개의 파라미터값을 계산하기</title>
</head>
<body>
<form method="post" >
  x:<input type="text" name="x" value="${param.x}"><br>
  y:<input type="text" name="y" value="${param.y}">
   <input type="submit" value="더하기">  
</form>
<c:set var="x" value="${param.x }" />
<c:set var="y" value="${param.y }" />
합 : ${x + y}
<c:set var="sum" value="${x+y }" />

<h3>if 태그를 이용하여 출력하기</h3>
<c:if test="${sum lt 0 }">
${sum }은 음수입니다.
</c:if>
<c:if test="${sum gt 0 }">
${sum }은 양수입니다.
</c:if>

<h3>choose when 태그를 이용하여 출력하기</h3>
<c:choose>
   <c:when test="${sum lt 0 }">
   ${sum }은 음수입니다.
   </c:when>
   <c:when test="${sum gt 0 }">
   ${sum }은 양수입니다.
   </c:when>
</c:choose>
</body>
</html>