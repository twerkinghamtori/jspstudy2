<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 수정 화면</title>
<script>
   function file_delete() { //[첨부파일 삭제]라는 a 태그를 클릭하면 실행되는 함수
	   document.f.file2.value=""; //hidden 태그의 file2 값을 삭제하고,
	   file_desc.style.display="none"; //div 태그를 삭제 시킴 ex. "dog.jpg[첨부파일 삭제]" 이 태그를 없앰.
   }
</script>	
</head>
<body>
   <form action="update" method="post" enctype="multipart/form-data" name="f">
      <input type="hidden" name="num" value="${b.num }">
      <input type="hidden" name="file2" value="${b.file1 }"> <!-- null이면 null이라는 파일명으로 들어가니까 "" 첨부파일 없을 경우 공백으로 처리 -->
      
      <div class="w3-container">
		<h2 id="center">${boardName} 수정</h2>
		<table class="w3-table-all">
         <tr>
            <td>글쓴이</td>
            <td><input type="text" name="writer" value="${b.writer }" class="w3-input"></td>
         </tr>
         
         <tr>
            <td>비밀번호</td>
            <td><input type="password" name="pass" class="w3-input"></td>
         </tr>
         
         <tr>
            <td>제목</td>
            <td><input type="text" name="title" value="${b.title }" class="w3-input"></td>
         </tr>
         
         <tr>
            <td>내용</td>
            <td><textarea rows="15" name="content" class="w3-input" id="content">${b.content }</textarea></td>
            <script>CKEDITOR.replace("content", {
        	 	filebrowserImageUploadUrl : "imgupload" 
         	})</script>
         </tr>
         
         <tr>
            <td>첨부파일</td>
            <td style="text-align:left">
               <c:if test="${!empty b.file1 }">
               		<div id="file_desc"> ${b.file1 }
                      <a href="javascript:file_delete()">[첨부파일 삭제]</a>
                   </div>
               </c:if>
               <input type="file" name="file1">
            </td>
         </tr>
         
         <tr>
            <td colspan="2">
               <a href="javascript:document.f.submit()">[게시물수정]</a>  <!-- <script> document.f.submit(); </script> -->
            </td>
         </tr>
      </table>
      </div>
   </form>
</body>
</html>