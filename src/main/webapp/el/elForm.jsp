<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <c:set var="test" value="session test 속성" scope="session" />
   <c:set var="today" value="<%=new java.util.Date() %>" scope="session" />
   <form action="elEx1.jsp" method="post">
      이름
      <input type="text" name="name" >
      <input type="submit" value="전송">
   </form>
</body>
</html>