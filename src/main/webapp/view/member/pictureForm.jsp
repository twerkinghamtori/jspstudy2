<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원사진 등록</title>
</head>
<body>
   <h3>사진업로드</h3>
   <table>
      <tr>
         <td>
            <img id="preview" src="">
         </td>
      </tr>
      
      <tr>
         <td>
            <form action="picture" method="post" enctype="multipart/form-data">
               <input type="file" name="picture" id="imageFile" accept="img/*"> <!-- accept="img/*" => img만 업로드 할 것임. -->
               <input type="submit" value="사진등록">
            </form>
         </td>
      </tr>
   </table>
   
   <script type="text/javascript"> <!-- 화면에서 사진 등록 전, 미리보기 하기 위한 코드 -->
      let imagefile=document.querySelector('#imageFile');
      let preview = document.querySelector('#preview');
      imagefile.addEventListener('change', function(e) {//change : 이미지 파일 새롭게 선택시(변경시) 이벤트 listener => function(event handler) 실행
    	  let get_file = e.target.files; //선택된 file..?
    	  let reader = new FileReader(); //파일을 읽기 위한 stream
    	  reader.onload = (function(Img) { //(Img)==(preview), preview가 매개변수. 
    		 return function(e) { //함수1 return 함수2(의 return 값);
    			 Img.src=e.target.result; //e.target.result = 선택된 파일의 이름?
    		 }  
    	  })(preview);
    	  if(get_file){ //선택된 파일이 있다면, js는 조건문 안에 무조건 boolean 아니어도 됨. => (if(get_file) : 선택된 파일이 존재한다면,)
    		  reader.readAsDataURL(get_file[0]); //[0]은 뭐지? 배열로 받나? ㅇㅇ e.target.file"s" 니까. 첫번째 선택된 파일.
    	      //readAsDataURL : 파일 읽기 => onload 이벤트 발생.
    	  }
      });
   </script>
</body>
</html>