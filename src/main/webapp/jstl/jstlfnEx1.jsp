<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL EL 함수</title>
</head>
<body>
<c:set var="str1" value="Functions <태그>를 사용합니다.        "/>
<c:set var="str2" value="사용" />
<c:set var="tokens" value="1,2,3,4,5,6,7,8,9,10" />
length(str1)=${fn:length(str1) }  :str1문자열의 길이 <br>
toUpperCase(str1)=${fn:toUpperCase(str1) }  :str1문자열을 대문자로 변경<br>
toLowerCase(str1)=${fn:toLowerCase(str1) }  :str1문자열을 소문자로 변경<br>
substring(str1,3,6)=${fn:substring(str1,3,6) }  :str1문자열의 3~5인덱스 부분문자열<br>
substringAfter(str1,str2)=${fn:substringAfter(str1,str2) }  :str1문자열에서 str2 부분 이후의 부분문자열<br> <%--공백은 포함x? EL함수 안에 EL함수 못쓰나? ㅇㅇ 그러면 변수로 저장해놨다가 다시 함수 써야함?ㅠㅠ --%>
substringBefore(str1,str2)=${fn:substringBefore(str1,str2) }  :str1문자열에서 str2 부분 이전의 부분문자열<br>
contains(str1, str2) = ${fn:contains(str1,str2) } : str1내에 str2문자열이 존재하니? <br> <%--boolean --%>
trim(str1) = ${fn:trim(str1) } : 공백 제거<br>
replace(str1," ","-")=${fn:replace(str1," ","-") } : 공백을 -로 치환<br>
split(tokens, ",") = ${fn:split(tokens, ",") } <br> <%--배열로 출력됨. --%>
<c:set var="arr" value="${fn:split(tokens, ',') }" />
<c:forEach var="i" items="${arr }">
 ${i }&nbsp;&nbsp;
</c:forEach>
<br>
join(arr,"-") = ${fn:join(arr,"-") } : 배열을 하나의 문자열로 연결시켜서 출력하는 것<br> <%--여기서 arr은 EL로 안불러와도 되나..? 그냥 변수처럼 씀? ㄴㄴ 이미 EL로 불러 온거임--%> 
</body>
</html>