<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h3>이름 : ${param.name }</h3>
  <h3>나이 : ${param.age }</h3>
  <h3>성별 : ${param.gender==1?"남":"여" }</h3>
  <h3>출생연도 : ${param.year }</h3>
  <h3>나이(2023년 기준) : 만 ${2023- param.year}</h3>
</body>
</html>