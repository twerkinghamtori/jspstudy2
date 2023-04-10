<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL core 태그 : 반복문</title>
</head>
<body>
   <h3>반복 관련 태그 : forEach</h3>
   <c:forEach var="i" begin="1" end="10">
      ${i }&nbsp;&nbsp;
   </c:forEach>
   <br>
   <c:forEach var="i" begin="1" end="10" step="2"> <!-- step : 2씩 건너뛰면서 -->
      ${i }&nbsp;&nbsp;
   </c:forEach>
   <br>
   <c:forEach var="i" begin="2" end="10" step="2"> 
      ${i }&nbsp;&nbsp;
   </c:forEach>
   <br>
   <c:forEach var="i" begin="1" end="100">
      <c:set var="sum" value="${sum+i }"/> <!-- 자료형은 선언하지 않아도 되나? -->
   </c:forEach>
   1부터 100까지의 합 : ${sum }
   <br>
   
   <c:set var="sum" value="${0 }"/> <!-- sum 변수를 0으로 초기화 -->
   <c:forEach var="i" begin="2" end="100" step="2">
      <c:set var="sum" value="${sum+i }"/> 
   </c:forEach>
   1부터 100까지의 짝수 합 : ${sum }
   <br>
   
   <c:set var="sum" value="${0 }"/>
   <c:forEach var="i" begin="1" end="100">
      <c:if test="${i%2==0 }">
        <c:set var="sum" value="${sum+i }"/> 
      </c:if>
   </c:forEach>
   1부터 100까지의 짝수 합 : ${sum }
   <br>
   
   <c:set var="sum" value="${0 }"/> 
   <c:forEach var="i" begin="1" end="100" step="2">
      <c:set var="sum" value="${sum+i }"/> 
   </c:forEach>
   1부터 100까지의 홀수 합 : ${sum }
   <br>
   
   <c:set var="sum" value="${0 }"/>
   <c:forEach var="i" begin="1" end="100">
      <c:if test="${i%2!=0 }">
        <c:set var="sum" value="${sum+i }"/> 
      </c:if>
   </c:forEach>
   1부터 100까지의 짝수 합 : ${sum }
   <br>
   
   <h3>forEach 태그를 이용하여 구구단 출력하기</h3>
   <c:forEach var="i" begin="2" end="9">
      <h4>${i }단</h4>
      <c:forEach var="j" begin="2" end="9">
         ${i }*${j }=${i*j }<br>
      </c:forEach>
   </c:forEach>
   
   <h3>forEach 태그를 이용하여 List 객체 출력하기</h3>
   <% List<Integer> list = new ArrayList<>();
      for(int i=1; i<=10; i++) list.add(i*10);
      pageContext.setAttribute("list",list); //EL과 JSTL을 쓰기 위해서 속성을 저장해 주어야함.
   %>
   <c:forEach var="i" items="${list }"> <!-- list를 속성값으로 불러옴. -->
      ${i }&nbsp;&nbsp;
   </c:forEach>
   <br>
   <c:forEach var="i" begin="0" end="20"> <!-- arrayindexoutofbound 예외 안남 감사합니다.../ i: 인덱스값. 0부터 시작. end="9" 까지만 실행됨. -->
      ${list[i] }&nbsp;&nbsp;
   </c:forEach>
   <br>
   <%--varStatus="st" 반복문의 상태를 st변수로 설정.
       st.index : 0~. 첨자를 의미함.
       st.count : 1~. 순서를 의미함.
   --%>
   <c:forEach var="i" items="${list }" varStatus="stat"> <!-- 개선된 for문 처럼.--> <!-- stat은 변수명임!! varStatus(반복문의 상태를 의미하는 것). varStatus에서는 첨자를 사용할 수 있음.-->
      <c:if test="${stat.index==0 }">
         <h4>forEach 태그의 varStatus 속성 연습</h4>
      </c:if>
      ${stat.index } : ${i } , ${list[stat.index]} &nbsp;&nbsp;&nbsp;
      ${stat.count } : ${i } , ${list[stat.count-1] } <!-- count : 1부터 시작. 반복되는 횟수를 의미. index==count-1 -->
      <br> <!-- stat.index = 배열의 index값을 의미함. -->
   </c:forEach>
</body>
</html>