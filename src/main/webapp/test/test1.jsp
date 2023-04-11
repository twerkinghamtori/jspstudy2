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
  x:<input type="text" name="x" value="${param.x}"><br> <%--처음에 파라미터 없을 때(입력안했을 때) EL이니까 빈문자열. request.getParameter("x")하면 처음에 박스 안에 null이 뜸. --%>
  y:<input type="text" name="y" value="${param.y}">
   <input type="submit" value="더하기">  <%--submit을 누르면 form 객체에서 test1.jsp가 한번 더 요청됨 => 파라미터 호출됨. --%>
</form>
<c:set var="x" value="${param.x }" />
<c:set var="y" value="${param.y }" />
합 : ${x + y}
<c:set var="sum" value="${x+y }" />

<h3>if 태그를 이용하여 출력하기</h3>
<c:if test="${sum lt 0 }"> <%--if태그는 위에서 걸려도 밑에 문장을 검사함, choose when은 안함. --%>
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