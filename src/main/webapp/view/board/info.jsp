<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세 보기</title>
</head>
<body>
<div class="w3-container w3-center">
	<h2 id="center">게시물 상세보기</h2>
	<table class="w3-table-all">
      <tr>
         <th width="20%">글쓴이</th>
         <td width="80%" style="text-align:left">
            ${b.writer }
         </td>
      </tr>
      
      <tr>
         <th>제목</th>
         <td style="text-align:left;">${b.title }</td>
      </tr>
      
      <tr>
         <th>내용</th>
         <td>
            <table style="width:100%; height:250px;">
               <tr>
                  <td style="border-width:0px; vertical-align:top; text-align:left">
                      ${b.content }
                  </td>
               </tr>
            </table>
         </td>
      </tr>
      
      <tr>
         <th>첨부파일</th>
         <td>
         	<c:if test="${empty b.file1 }">
         		&nbsp;
         	</c:if>
         	<%-- http://localhost:8080/jspstudy2/board/info?num=1 
         	     http://localhost:8080/jspstudy2/upload/board/dochi.jpg--%>
         	<c:if test="${!empty b.file1 }">
         		<a href="../upload/board/${b.file1 }">${b.file1 }</a> <!-- 그냥 하면 왜 .jsp로 넘어가지? MskimMapping 때문에 그런가? ../이렇게 쓰는건 절대경로?ㄴㄴ 상대경로임 -->
         		<!-- upload/board => jspstudy2/board/upload/board/...
         		     /upload/board => /upload/board/...
         		     ../upload/board => jspstudy2/upload/board/... -->
         	</c:if>
         </td>
      </tr>
      
      <tr>
         <td colspan="2">
            <c:if test="${!empty sessionScope.login}">
            <a href="replyForm?num=${b.num }">[답변]</a>
            	<c:if test="${boardid != '1' || (sessionScope.login=='admin')}">
            		<a href="updateForm?num=${b.num }">[수정]</a>
                    <a href="deleteForm?num=${b.num }">[삭제]</a>
            	</c:if> 
            </c:if>          
            <a href="list">[목록]</a>
         </td>
      </tr>
   </table>
</div>
</body>
</html>