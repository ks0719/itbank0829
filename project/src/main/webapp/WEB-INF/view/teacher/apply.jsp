<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/view/template/header.jsp" %>

<html>
<head>
<title>Insert title here</title>
</head>
<body>

<h1>강사신청</h1>

<div>
	<form action="apply" method="post">
		<input type="hidden" name="nick" value="${nick}">
		닉네임 : ${nick}
		<br><br>
		사진 : <input type="file" name="file" onchange="previewImage(this,'upload_photo')">
		<br><br>
		<div class="upload_photo" id="upload_photo" style="display: in-block; width: 150px; height: 150px;"> <!-- 스타일 지정 필수 -->
			<img width="167px;" id="prev_upload_photo" src="http://www.placehold.it/150x150"/>
		</div>
		<br><br>
		<textarea rows="10" cols="80" name="career" placeholder="경력소개"></textarea>
		<br><br>
		<textarea rows="20" cols="80" name="intro" placeholder="자기소개"></textarea>
		<br><br>
		<div class="align-right">
			<input type="submit" value="신청하기">
		</div>
	</form>
</div>

</body>
</html>

<%@ include file="/WEB-INF/view/template/footer.jsp" %>