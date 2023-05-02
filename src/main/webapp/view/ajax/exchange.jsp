<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table>
	<caption>수출입은행<br>${date }</caption>
	<tr>
		<th>통화</th>
		<th>기준율</th>
		<th>매도시</th>
		<th>매수시</th>
	</tr>
	<c:forEach items="${list }" var="td">		
			<tr>
				<td>${td[0] }</td>
				<td>${td[4] }</td>
				<td>${td[2] }</td>
				<td>${td[3] }</td>
			</tr>
	</c:forEach>
</table>