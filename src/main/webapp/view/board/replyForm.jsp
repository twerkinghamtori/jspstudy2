<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
1. 원글의 num을 파라미터로 저장 : num=원글의 번호
2. db에서 num의 게시물을 조회하여 원글의 num, grp, grplevel, grpstep 정보를 저장.(hidden 값으로 가지고 있는다)
3. 입력 화면 표시 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 답글쓰기</title>
</head>
<body>
   <form action="reply" method="post" name="f">
      <input type="hidden" name="num" value="${b.num }">
      <input type="hidden" name="grp" value="${b.grp }">
      <input type="hidden" name="grplevel" value="${b.grplevel }">
      <input type="hidden" name="grpstep" value="${b.grpstep }">
      <input type="hidden" name="boardid" value="${b.boardid }">
      <div class="w3-container">
		<h2 id="center">게시판 답글 등록</h2>
		<table class="w3-table-all">
         <tr>
            <th>글쓴이</th>
            <td><input type="text" name="writer" class="w3-input"></td>
         </tr>
         
         <tr>
            <th>비밀번호</th>
            <td><input type="password" name="pass" class="w3-input"></td>
         </tr>
         
         <tr>
            <th>제목</th>
            <td><input type="text" name="title" value="RE:${b.title }" class="w3-input"></td>
         </tr>
         
         <tr>
            <th>내용</th>
            <td><textarea name="content" rows="15" class="w3-input" id="content"></textarea></td>
            <script>CKEDITOR.replace("content", {
        	 	filebrowserImageUploadUrl : "imgupload" 
         	})</script>
         </tr>
         
         <tr>
            <td colspan="2">
               <a href="javascript:document.f.submit()">[답변 글 등록]</a>
            </td>
         </tr>
      </table>
      </div>
   </form>
</body>
</html>