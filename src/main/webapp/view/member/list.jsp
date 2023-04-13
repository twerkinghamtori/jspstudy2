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
 
 $(function(){
	$('#toggle').click(function(){
		$('.chk').prop('checked', $(this).is(':checked'))
	})
 })
</script>
</head>
<body>
<div class="container">
	<h2 id="center">회원목록</h2>
	<table class="table table-hover">
   		<tr>
      		<th>아이디</th>
      		<th>사진</th>
      		<th>이름</th>
      		<th>성별</th>
      		<th>전화번호</th>
      		<th>&nbsp;</th>
      		<th><input type="checkbox" name="check" value="checkall" id="toggle"></th>
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
      				<input type="checkbox" class="chk" value="1" name="check">
      			</td>
   			</tr>
   		</c:forEach>
	</table>
</div>
</body>
</html>