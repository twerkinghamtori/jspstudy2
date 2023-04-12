<%@page import="model.MemberDao"%>
<%@page import="model.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<script type="text/javascript">
  function inputcheck(f) {
	  if(f.pass.value == "") {
		  alert("비밀번호를 입력하세요.");
		  f.pass.focus();
		  return false;
	  }
  }
  function win_passchg() {
	  let op = "width=500, height=250, left=50, top=150";
	  open("passwordForm", "", op);
  }
  function win_upload() {
	  let op = "width=500, height=150, left=50, top=150";
	  open("pictureForm", "", op);
  }
</script>
</head>
<body>
<div class="container">
  <form action="update" name="f" method="post" onsubmit="return inputcheck(this)">
     <input type="hidden" name="picture" value="${mem.picture }">
     <h2 id="center">회원 정보 수정</h2>
     <table class="table table-hover">
        <tr>
           <td rowspan="4" valign="bottom">
              <img src="picture/${mem.picture }" width="100" height="120" id="pic"><br>
              <font size="1"><a href="javascript:win_upload()">사진수정</a></font>
           </td>
           <th>아이디</th>
           <td>
              <input type="text" name="id" readonly value="${mem.id }"> <!-- read/only => 수정 불가. disabled=true => 파라미터값이 넘어오지 않음. -->
           </td>
        </tr>
        <tr>
           <th>비밀번호</th>
           <td>
              <input type="password" name="pass">
           </td>
        </tr>
        <tr>
           <th>이름</th>
           <td>
              <input type="text" name="name" value="${mem.name }">
           </td>
        </tr>
        <tr>
           <th>성별</th>
           <td>
              <input type="radio" name="gender" value="1" ${mem.gender==1?"checked":"" }>남 <!-- input(radio) 태그 checked => check 되어 있음. -->
              <input type="radio" name="gender" value="2" ${mem.gender==2?"checked":"" }>여 <!-- 회원정보의 성별이 2이면, radio버튼 checked, 아니면 ""(공백) -->
           </td>
        </tr>
        <tr>
           <th>전화번호</th>
           <td colspan="2">
              <input type="text" name="tel" value="${mem.tel }">
           </td>
        </tr>
        <tr>
           <th>이메일</th>
           <td colspan="2">
              <input type="text" name="email" value="${mem.email }">
           </td>
        </tr>
        <tr>
           <td colspan="3">
              <button type="submit" class="btn btn-dark">회원 정보 수정</button>
              <c:if test="${param.id == sessionScope.login }"> <!-- 관리자로 로그인한 경우 비밀번호 수정이 불가하도록. param.id는 info.jsp에서 넘겨준거. -->
              <button type="button" onclick="win_passchg()" class="btn btn-dark">비밀번호 수정</button>
              </c:if>
           </td>
        </tr>
     </table>
  </form>
</div>
</body>
</html>