<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--2. opener 화면에 결과 전달 => javascript
    3. 현재화면 close() => javascript --%>
<script type="text/javascript">
   //opener : 현재 window를 open한 window => joinForm.jsp가 여기서 opener. 
   img = opener.document.getElementById("pic"); //joinForm.jsp에 pic아이디를 가진 태그가 있음.
   img.src = "/jspstudy2/picture/${fname}"; //joinForm.jsp에 이미지가 보여지게 됨.
   opener.document.f.picture.value="${fname}"; //joinForm.jsp에 form태그(f)의 hidden태그(picture) => DB에 저장하기 위해서.(joinForm -> join )
   self.close(); //현재 창 닫기.
</script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>