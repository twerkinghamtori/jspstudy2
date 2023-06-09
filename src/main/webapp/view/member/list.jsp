<%@page import="model.MemberDao"%>
<%@page import="model.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
<script>
 /* function checkAll(checkall) {
	 const checkboxes = document.getElementsByName("check");
	 checkboxes.forEach((checkbox) => { checkbox.checked = checkAll.checked;})
 } */
 
 document.addEventListener('DOMContentLoaded',function(){
     const toggle = document.querySelector('#toggle');

     toggle.addEventListener('click',function(){
       const chkList = document.querySelectorAll('.chk');

       for (const chk of chkList) {
         if(toggle.checked){
           chk.checked = true;
         }else{
           chk.checked = false;
         }
       }
     });
   });
</script>
</head>
<body>
<div class="container">
	<h2 id="center">회원목록</h2>
	<form action="mailForm" name="f" method="post">
	<table class="table table-hover">
   		<tr>
      		<th>아이디</th>
      		<th>사진</th>
      		<th>이름</th>
      		<th>성별</th>
      		<th>전화번호</th>
      		<th>&nbsp;</th>
      		<th><input type="checkbox" name="checkall" value="checkall" id="toggle"></th>
   		</tr>
   		<c:forEach var="m" items="${list }">
   			<tr>
      			<td><a href="info?id=${m.id }">${m.id }</a></td>
      			<td><img src="/jspstudy2/picture/${m.picture }" width="30" height="30"></td>
      			<td>${m.name }</td>
      			<td>${m.gender==1?"남":"여" }</td>
      			<td>${m.tel }</td>
      			<td>
       			 <a href="updateForm?id=${m.id }">수정</a>
       			 <c:if test="${m.id != 'admin' }">
       				 <a href="deleteForm?id=${m.id }">강제탈퇴</a>
       			 </c:if>
      			</td>
      			<td>
      				<input type="checkbox" name="check" value="${m.id }" class="chk" >
      			</td>
   			</tr>
   		</c:forEach>
   		<tr>
   		   <td colspan="7" id="center">
   		       <button type="submit" class="btn btn-dark" >메일 보내기</button>
   		   </td>
   		</tr>   		
	</table>
	</form>
</div>
</body>
</html>