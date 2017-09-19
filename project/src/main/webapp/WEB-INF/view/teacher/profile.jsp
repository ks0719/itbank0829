<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%@ include file="/WEB-INF/view/template/teacherHeader.jsp" %>

<h1>강사프로필 관리</h1>

<div>
	<form action="profile" method="post" enctype="multipart/form-data">
		<input type="hidden" name="name" value="${profile.name}">
		닉네임 : ${profile.name}
		<br><br>
		사진 : <input type="file" name="file" onchange="previewImage(this,'upload_photo')">
		<br><br>
		<c:choose>
			<c:when test="${profile.picture_name ne null}">
				<div class="upload_photo" id="upload_photo" style="display: in-block;"> <!-- 스타일 지정 필수 -->
					<img width="150px;" height="150px;" id="prev_upload_photo" src="<c:url value="/file/${profile.picture_name}"/>"/>
				</div>
			</c:when>
			<c:otherwise>
				<div class="upload_photo" id="upload_photo" style="display: in-block;"> <!-- 스타일 지정 필수 -->
					<img width="150px;" height="150px;" id="prev_upload_photo" src="http://www.placehold.it/150x150"/>
				</div>
			</c:otherwise>
		</c:choose>
		<br><br>
		주언어 :
		<select name="sort" title="언어 선택">
			<option value="C언어">C언어</option>
			<option value="C++">C++</option>
			<option value="JAVA">JAVA</option>
			<option value="JSP">JSP</option>
			<option value="Javascript">Javascript</option>
			<option value="Python">Python</option>
		</select>
		<br><br>
		<textarea rows="10" cols="80" name="career" placeholder="경력소개" required>${profile.career}</textarea>
		<br><br>
		<textarea rows="20" cols="80" name="intro" placeholder="자기소개" required>${profile.intro}</textarea>
		<br><br>
		<div class="align-right">
			<input type="submit" value="수정하기">
		</div>
	</form>
</div>

<%@ include file="/WEB-INF/view/template/teacherFooter.jsp" %>