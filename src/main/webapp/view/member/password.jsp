<%@page import="model.MemberDao"%>
<%@page import="model.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 비밀번호 변경은 로그인한 정보로만 할 수 있도록 막아놨음. => session.getAttribute("login") --%>
    <c:choose>
    	<c:when test="${empty login }">
    		<script>
    	  		alert("로그인하세요.");
    	  		opener.location.href="loginForm";
    	  		self.close();
    		</script>
    	</c:when>
    	
    	<c:when test="${dbpass != pass }">
    		<script>
		     	alert("비밀번호 오류입니다.");
		     	location.href="passwordForm";
		    </script>
    	</c:when>
    	
    	<c:when test="${able}">
    		<script>
        	     alert("비밀번호 변경 성공. 변경된 비밀번호로 다시 로그인하세요.");   	     
        	     opener.location.href="loginForm";
        	     self.close();
        	     session.invalidate();        	     
        	   </script>
    	</c:when>
    	
    	<c:when test="${! able }">
    		<script>
                 alert("비밀번호 변경 실패");
                 opener.location.href="updateForm?id=${login}";
                 self.close();
               </script>
    	</c:when>
    	
    </c:choose>    	
