<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 목록 보기</title>
</head>
<body>
<div class="w3-container">
<h2 id="center">${boardName }</h2>
<table class="w3-table-all">
   <c:if test="${boardcount==0 }">
   <tr>
      <td colspan="5"> 등록된 게시글이 없습니다.</td>
   </tr>
   </c:if>
   
   <c:if test="${boardcount !=0 }">
   <tr>
      <td colspan="5" style="text-align:right">글개수:${boardcount }</td>
   </tr>
   <tr>
      <th width="8%">번호</th>
      <th width="50%">제목</th>
      <th width="14%">작성자</th>
      <th width="17%">등록일</th>
      <th width="11%">조회수</th>
   </tr>
       <c:forEach var="b" items="${list }">
       <tr>
           <!-- 번호 -->
          <td>${boardnum }</td>
          <c:set var="boardnum" value="${boardnum-1 }" />
          
          <!-- 제목  -->
          <td style="text-align:left">
          	<c:if test="${empty b.file1 }">
          		 &nbsp;&nbsp;&nbsp;
          	</c:if>
          	<c:if test="${!empty b.file1 }">
          		<a href="../upload/board/${b.file1 }">@</a>
          	</c:if>
          	
          	<c:if test="${b.grplevel > 0 }">
          		<c:forEach var="i" begin="1" end="${b.grplevel }">
                        &nbsp;&nbsp;	
                 </c:forEach>
                 └
          	</c:if>
          	
          	<a href="info?num=${b.num }">${b.title }</a>
          	
          	<c:if test="${b.commcnt >0 }">
             	<a href="info?num=${b.num }#comment">
             		<span class="w3-badge w3-blue w3-tiny">${b.commcnt }</span>
             	</a>
          	</c:if>
          </td>

          <!-- 작성자 -->
          <td>${b.writer }</td>
          
          <!-- 등록일 -->
          <fmt:formatDate value="${today }" pattern="yyyy-MM-dd" var="t" />
          <fmt:formatDate value="${b.regdate }" pattern="yyyy-MM-dd" var="r" />
          <c:if test="${t==r }">          	  
        	  <td>
        	  	<fmt:formatDate value="${b.regdate }" pattern="HH:mm:ss" />
        	  </td>
          </c:if>
          <c:if test="${t!=r }">          	  
        	  <td>
        	  	<fmt:formatDate value="${b.regdate }" pattern="yyyy-MM-dd HH:mm" />
        	  </td>
          </c:if>          
          
          <!-- 조회수 -->
          <td>${b.readcnt }</td>          
       </tr>
    </c:forEach> <!-- list for 구문 끝 -->
    
    <%--페이지 처리 : [이전] 1,...,10 [다음]--%>
    <tr>
       <td colspan="5">
       <c:if test="${pageNum <=1 }">
          [이전] 
       </c:if>
       <c:if test="${pageNum >1 }">
       	  <a href="list?pageNum=${pageNum -1 }">[이전]</a>
       </c:if>
       <c:forEach var="a" begin="${startpage }" end="${endpage }">
       	 	<c:if test="${a==pageNum }">
       	 		[${a }]
       	 	</c:if>
       	 	<c:if test="${a!=pageNum }">
       	 		<a href="list?pageNum=${a }">
                [${a }]
                </a>
       	 	</c:if>
       </c:forEach> 
       <c:if test="${pageNum >= maxpage }">
       		[다음]
       </c:if>
       <c:if test="${pageNum < maxpage }">
       		<a href="list?pageNum=${pageNum +1 }">
               [다음]
            </a>
       </c:if>                    
       </td>
    </tr>
	</c:if>
    <tr>
       <td colspan="5" style="text-align:right">
       <c:if test="${boardid != '1' || (sessionScope.login=='admin')}"> <!-- 관리자만 공지사항에 글쓰기가 가능.  -->       
             <p align="right">
                <a href="writeForm">[글쓰기]</a> <!-- boardcount가 0이어도 글쓰기 버튼이 있어야함. -->
             </p>
        </c:if>
       </td>
    </tr>
</table>
</div>
</body>
</html>