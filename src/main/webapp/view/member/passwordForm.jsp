<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 수정</title>
<link rel="stylesheet" href="../../css/main.css">
<script type="text/javascript">
   function inchk(f) {
	   if(f.pass.value=="") {
		   alert("현재 비밀번호를 입력하세요.");
		   f.pass.focus();
		   return false; 
	   }
	   if(f.chgpass.value=="") {
		   alert("변경할 비밀번호를 입력하세요.");
		   f.chgpass.focus();
		   return false;
	   } 
	   if(f.chgpass2.value=="") {
		   alert("변경할 비밀번호를 재입력하세요.");
		   f.chgpass2.focus();
		   return false;
	   } 
	   if(f.chgpass.value != f.chgpass2.value) {
		   alert("변경할 비밀번호가 다릅니다.");
		   f.chgpass2.value="";
		   f.chgpass2.focus();
		   return false;
	   } 
	   return true;
   }
</script>
</head>
<body>
   <form action="password" method="post" name="f" onsubmit="return inchk(this)">
      <table>
         <caption>비밀번호 변경</caption>
         <tr>
            <th>현재 비밀번호</th>
            <td><input type="password" name="pass"></td>
         </tr>
         
         <tr>
            <th>변경 비밀번호</th>
            <td><input type="password" name="chgpass"></td>
         </tr>
         
         <tr>
            <th>변경 비밀번호 재입력</th>
            <td><input type="password" name="chgpass2"></td>
         </tr>
         
         <tr>
            <td colspan="2">
               <input type="submit" value="비밀번호 변경">
               <input type="reset" value="입력 초기화">
            </td>
         </tr>
      </table>
   </form>
</body>
</html>