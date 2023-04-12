<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>탈퇴 전 비밀번호 입력화면</title>
<script>
   function inputcheck(f) {
	   if(f.pass.value=="") {
		   alert("비밀번호를 입력하세요.");
		   f.pass.focus();
		   return false;
	   } else return true;
   }
</script>
</head>
<body>
   <form action="delete" method="post" onsubmit="return inputcheck(this)">
     <input type="hidden" name="id" value="${param.id }">
     <h2 id="center">회원 비밀번호 입력</h2>
     <table class="table table-hover">
        
        <tr>
           <th>비밀번호</th>
           <td><input type="password" name="pass"></td>
        </tr>
        <tr>
           <td colspan="2">
              <input type="submit" value="탈퇴하기">
           </td>
        </tr>
     </table>
   </form>
</body>
</html>