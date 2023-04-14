<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메일 작성</title>
<script type="text/javascript">
	function naverinputchk(f) {
		if(f.naverid.value=="") {
			alert("네이버 아이디를 입력하세요.");
			f.naverid.focus();
			return false;
		}
		if(f.naverpw.value=="") {
			alert("비밀번호를 입력하세요.");
			f.naverpw.focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<div class="container">
		<h2 id="center">메일 보내기</h2>
		<form action="mailSend" name="form1" method="post" onsubmit="return naverinputchk(this)">
			<table class="table">
				<tr>
					<td>보내는 사람</td>
					<td>본인 네이버 ID : 
						<input type="text" name="naverid" class="form-control">
						본인 네이버 비밀번호 : 
						<input type="password" name="naverpw" class="form-control">
					</td>
				</tr>
				
				<tr>
					<td>받는 사람</td>
					<td>
						<input type="text" name="recipient" class="form-control" 
						value="<c:forEach var="m" items="${list }">${m.name } &lt;${m.email }&gt;,</c:forEach>"> <%--lt(<), gt(>) 메일을 보내는 protocol ex.강수빈 <123@aa.bb> 형식--%>
					</td>
				</tr>
				
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" class="form-control"></td>
				</tr>
				
				<tr>
					<td>메세지 형식</td>
					<td>
						<select name="mtype" class="form-control">
							<option value="text/html;charset=utf-8">HTML
							<option value="text/plain;charset=utf-8">TEXT
						</select>
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
						<textarea name="content" cols="40" rows="10" class="form-control"></textarea>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" id="center">
						<button type="submit" class="btn btn-dark">전송</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>