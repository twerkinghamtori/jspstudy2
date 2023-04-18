<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	window.parent.CKEDITOR.tools.callFunction(${param.CKEditorFuncNum}, '../upload/imgfile/${fileName}', '이미지업로드완료') //(index(내부적으로 사용), 파일 경로, alert msg)
</script>