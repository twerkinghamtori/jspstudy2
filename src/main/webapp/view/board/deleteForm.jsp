<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 삭제</title>
</head>
<body>
<div class="w3-container">
   <h2 id="center">게시물 삭제</h2>
   <form action="delete" method="post">
      <input type="hidden" name="num" value="${param.num }">
      <label>Password: </label>
      <input type="password" class="form-control" name="pass">
      <div id="center" style="padding:3px">
         <button type="submit">게시물 삭제</button>
      </div>
   </form>
</div>
</body>
</html>
