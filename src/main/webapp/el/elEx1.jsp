<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>elForm.jsp의 결과 화면</title>
</head>
<body>
   <fmt:requestEncoding value="UTF-8"/>
   <%
//     request.setCharacterEncoding("UTF-8");
     String tel = "010-1111-2222";
     pageContext.setAttribute("tel",tel);
     String tel1 = "010-1111-3333";
     pageContext.setAttribute("test", "pageContext 객체의 test 속성");
     request.setAttribute("test", "request 객체의 test 속성");
     application.setAttribute("test", "application 객체의 test 속성");
   %>
   
   <h3>JSP의 스크립트를 이용하여 파라미터와 속성값 출력</h3>
   pageContext test 속성값 : <%=pageContext.getAttribute("test") %> <br>
   session test 속성값 : <%=session.getAttribute("test") %> <br>
   today 속성값 : <%=session.getAttribute("today") %> <br>
   name 파라미터값 : <%=request.getParameter("name") %> <br>
   tel 변수값 : <%=tel %> <br>
   tel 속성값 : <%=pageContext.getAttribute("tel") %> <br>
   noAttr 속성값 : <%=pageContext.getAttribute("noAttr") %> <br>
   noparam 파라미터값 : <%=request.getParameter("noparam") %> <br>
   <hr>
   
   <h3>JSP의 EL(Expression Language)을 이용하여 파라미터와 속성값 출력</h3>
   pageContext test 속성값 : ${pageScope.test} <br> <!-- EL은 null이 없음. 빈문자열 "" -->
   request test 속성값 : ${requestScope.test} <br>
   session test 속성값 : ${sessionScope.test } <br>
   application test 속성값 : ${applicationScope.test} <br>
   test 속성값 : ${test }<br> <!-- pageContext 객체의 속성이 가장 우선순위가 높음.  -->
   
   today 속성값 : ${sessionScope.today } <br>
   today 속성값 : ${today } <br> <!-- 영역에 속성값이 하나만 있을 때는 알아서 영역을 찾아줌. 단, 우선순위 순서대로  -->
   
   name 파라미터값 : ${param.name } <br>
   
   tel1 변수값 : EL로 표현 못함. 속성으로 등록해야 쓸 수 있음. ${tel1 } <br>
   tel 변수값 : EL로 표현 못함. ${tel } <br> <!-- 그냥 tel로 치면 변수값이 아니라 속성값 나오는 거임. --> 
     
   tel 속성값 : ${pageScope.tel } <br>
   noAttr 속성값 : ${pageScope.noAttr } <br>
   noparam 파라미터값 : ${param.noparam } <br>
   <hr>
   
   <h3>영역을 표시하여 test 속성값 출력하기</h3>
   \${test } = ${test }<br>
   \${pageScope.test } = ${pageScope.test }<br>
   \${requestScope.test } = ${requestScope.test }<br>
   \${sessionScope.test } = ${sessionScope.test }<br>
   \${applicationScope.test } = ${applicationScope.test }<br>
   \${today } = ${today }<br>
   \${pageScope.today } = ${pageScope.today }<br> <!-- 해당 영역에 등록된 속성값이 없으면 빈 문자열로 표현됨. -->
   \${requestScope.today } = ${requestScope.today }<br>
   \${sessionScope.today } = ${sessionScope.today }<br>
   \${applicationScope.today } = ${applicationScope.today }<br>
   [우선순위] pageContext > request > session > application
</body>
</html>