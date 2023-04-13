<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- [Controller]
     1. 아이디 파라미터값을 조회
     2. login 상태 검증
        - 로그아웃 상태 : "로그인하세요" 메세지 출력후에 loginForm.jsp로 이동
        - admin으로 로그인된 상태 : 모든 회원정보를 볼 수 있음.
        - 로그인 된 상태 : 다른 id를 조회하려고 하면 "내 정보 조회만 가능합니다." 메세지 출력후에 main.jsp로 이동
     3. DB에서 id에 해당하는 데이터를 조회
     -------------------------------------------
     [View]
     4. 조회된 내용을 화면에 출력 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 정보 조회</title>
</head>
<body> 
<div class="container">
	<h2 id="center">회원 상세 정보</h2>
   <table class="table table-hover"> <!-- 마우스 올렸을 때 색 변경 -->
     <tr>
        <td rowspan="6" width="30%">
           <img src="/jspstudy2/picture/${mem.picture }" width="200" height="210">
        </td>
        <th width="20%">아이디</th>
        <td>${mem.id }</td>
     </tr>
     
     <tr>
        <th>이름</th>
        <td>${mem.name }</td>
     </tr>
     
     <tr>
        <th>성별</th>
        <td>${mem.gender==1?"남":"여" }</td>
     </tr>
     
     <tr>
        <th>전화번호</th>
        <td>${mem.tel }</td>
     </tr>
     
     <tr>
        <th>이메일</th>
        <td>${mem.email }</td>
     </tr>
     
     <tr>
        <td colspan="2" id="center">
           <a href="updateForm?id=${mem.id }">수정</a>
           <c:if test="${param.id !='admin' }">
              <a href="deleteForm?id=${mem.id }">탈퇴</a>
           </c:if>
        </td>
     </tr>
     
   </table>
</div>
</body>
</html>