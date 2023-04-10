<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문제</title>
</head>
<body>
   <h3>1부터 10까지 숫자 3개씩 출력하기</h3>
   <c:forEach var="i" begin="1" end="10">
      ${i } &nbsp;&nbsp;
      <c:if test="${i%3==0 }">
         <br>
      </c:if>
   </c:forEach>
   
   <br>
   
   <h3>1부터 10까지 숫자 3개씩 출력하기</h3>
   <% List<Integer> list = new ArrayList<>();
      for(int i=1; i<=10; i++) list.add(i*10);
      pageContext.setAttribute("list", list);
   %>
   <c:forEach var="i" begin="0" end="9" varStatus="st">
      ${list[st.index] } &nbsp;&nbsp;
      <c:if test="${st.count%3==0 }">
         <br>
      </c:if>
   </c:forEach>
   
   
</body>
</html>