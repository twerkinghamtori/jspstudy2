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
   
   <%--댓글 등록 및 조회 --%>
   <span id="comment"></span>
   <form action="comment" method="post">
   		<input type="hidden" name="num" value="${b.num }">
   		<div class="w3-row">
   			<div class="w3-col s2 w3-center">
   				<p><input type="text" name="writer" class="w3-input w3-border"></p>
   			</div>
   			<div class="w3-col s9 w3-center">
   				<p><input type="text" name="content" class="w3-input w3-border"></p>
   			</div>
   			<div class="w3-col s1 w3-center">
   				<p><button type="submit" class="w3-btn w3-border">댓글등록</button>
   			</div>
   		</div>
   </form>
   <div class="w3-container">
   		<table class="w3-table-all">
   			<c:if test="${!empty commlist }">
   			<tr>
   				<th>번호</th>
   				<th>작성자</th>
   				<th>내용</th>
   			</tr>
   			</c:if>
   			<c:forEach var="c" items="${commlist }">
   				<tr>
   					<td>${c.seq }</td>
   					<td>${c.writer}</td>
   					<td>${c.content }</td>
   					<td class="w3-right">
   						<a class="w3-btn w3-border" href="commdel?num=${param.num }&seq=${c.seq}">삭제</a>
   					</td>
   				</tr>
   			</c:forEach>
   		</table>
   </div>
</div>
</body>
</html>