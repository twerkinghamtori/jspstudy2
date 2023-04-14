<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 글쓰기</title>
<link rel="stylesheet" href="../../css/main.css" >
<script type="text/javascript">
   function inputcheck(f) {
       if(f.writer.value == '') {
		   alert("방문자를 입력하세요");
		   f.writer.focus();
		   return false;
       }
       if(f.title.value == '') {
		   alert("제목 입력하세요");
		   f.title.focus();
		   return false;
       }
       if(f.content.value == '') {
		   alert("내용 입력하세요");
		   f.content.focus();
		   return false;
       }
       return true;
   }
</script>
</head>
<body>
<form action="testlist" method="post" 
      onsubmit="return inputcheck(this)">
      <h2 class="w3-center">방명록 쓰기</h2>
      <table class="w3-table w3-border">
			<tr><td>방문자</td><td><input type="text" name="writer" class="w3-input"></td></tr>
			<tr><td>제목</td><td><input type="text" name="title" class="w3-input"></td></tr>
			<tr><td>내용</td>
   		    <td><textarea rows="10" cols="60" name="content" class="w3-input"></textarea></td></tr>
			<tr><td colspan="2" align="center">
     		<input type="submit" value="글쓰기"></td></tr>
	</table>
</form>
</body>
</html>