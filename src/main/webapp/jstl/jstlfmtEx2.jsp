<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 형식화 예제2(형식화된 문자 -> 숫자) parse</title>
</head>
<body>
  <h3>Format 된 숫자형 문자열을 숫자로 변경하기</h3>
  <fmt:parseNumber value="20,000" var="num1" pattern="##,###" />
  <fmt:parseNumber value="10,000" var="num2" pattern="##,###" />
  합 : ${num1 } + ${num2 } = ${num1+num2 }<br>
  
  <c:set value="${num1+num2 }" var="sum" />
  <fmt:formatNumber value="${num1 }" var="num1" pattern="##,###" /> <%--var이 없으면 출력, 있으면 변수에 대입(출력 x) --%>
  <fmt:formatNumber value="${num2 }" var="num2" pattern="##,###" />
  <fmt:formatNumber value="${sum }" var="sum" pattern="##,###" />
  합 : ${num1 } + ${num2 } = ${sum }<br>
  
  <h3>Format 된 문자열형 날짜를 날짜형으로 변경 </h3>
  <fmt:parseDate value="2023-12-25 12:00:00" pattern="yyyy-MM-dd HH:mm:ss" var="day" />
  ${day }<br>
  <fmt:formatDate value="${day }" pattern="E요일" var="weekday" /> <%--var이 있으면 속성에 등록하는 것. EL로 출력 가능. --%>
  ${weekday }
</body>
</html>