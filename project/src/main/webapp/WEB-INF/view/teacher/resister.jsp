<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/view/template/teacherHeader.jsp" %>

<h1>강의 등록</h1>

<form action="resister" method="post" onsubmit="return resisterOK();" enctype="multipart/form-data">
	<input type="hidden" name="teacherno" value="${teacherNo}">
	<input type="text" name="teacher" value="${mynick}" readonly>
	<br><br>
	<select name="tag" title="언어 선택">
		<option value="C언어">C언어</option>
		<option value="C++">C++</option>
		<option value="JAVA">JAVA</option>
		<option value="JSP">JSP</option>
		<option value="Javascript">Javascript</option>
		<option value="Python">Python</option>
	</select>
	<br><br>
	<input type="file" name="file" onchange="previewImage(this,'upload_photo')">
	<br><br>
	<div class="upload_photo" id="upload_photo" style="display: in-block; width: 150px; height: 150px;"> <!-- 스타일 지정 필수 -->
		<img width="167px;" id="prev_upload_photo" src="http://www.placehold.it/150x150"/>
	</div>
	<br><br>
	<input type="text" name="title" placeholder="강의 제목" required>
	<br><br>
	<select name="type" title="강의 형태">
		<option value="인강">인강</option>
		<option value="원격강의">원격강의</option>
	</select>
	<br><br>
	동영상
	<input type="file" name="video" multiple>
	<br><br>
	<input type="text" name="period" id="period" placeholder="강의 기간 (YY.MM.DD~YY.MM.DD)" required>
	<br><br>
	<input type="text" name="time" id="time" placeholder="강의 시간 (HH:mm~HH:mm)" required>
	<br><br>
	<input type="text" name="price" id="price" placeholder="강의 가격" required>
	<br><br>
	<textarea rows="10" cols="80" name="intro" placeholder="강의소개" required></textarea>
	<br><br>
	<textarea rows="20" cols="80" name="detail" placeholder="강의계획" required></textarea>
	<br><br>
	<input type="submit" value="등록하기">
</form>
    
<%@ include file="/WEB-INF/view/template/teacherFooter.jsp" %>