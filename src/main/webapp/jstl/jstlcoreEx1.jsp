<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- taglib : 접두사(prefix)가 c인 태그의 상세 정보는 uri="http://java.sun.com/jsp/jstl/core"인 파일(c.tld)에 존재함을 의미 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%--반드시 tablib 지시어 지정할 것. --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL core 태그 1(set, remove, out)</title>
</head>
<body>
   <h3>속성 관련 태그 : set, remove, out 태그</h3>
   <%-- set 태그 : session.setAttribute("test","Hello JSTL"); 과 같은거. scope 지정 안하면 default=pageContext --%>
   <c:set var="test" value="${'Hello JSTL' }" scope="session" /> <!-- <name>: 태그 이름, <rtexprvalue>:표현식 사용가능? <required>:꼭 써야함? c.tld에서 확인 가능. -->
   <% session.setAttribute("test","<div stye='color:blue'>Hello JSTL</div>"); %>
   test 속성 : ${sessionScope.test }<br>
   <%-- out 태그 : session.getAttribute("test"); EL보다 보안에 안전함. --%>
   test 속성 : <c:out value="${test }" /><br> <!-- out쓰면 태그 안에 있는 위험한 문자열(ex. <>)을 자동으로 무력화됨.(escape xml) -->
   test 속성 : ${test }<br>
   <%-- remove 태그 : session.removeAttribute("test"); scope 생략되면 찾아서 삭제해줌. 여러개면 다 삭제? --%>
   <c:remove var="test" scope="session" />
   test 속성 : ${test }<br>
</body>
</html>