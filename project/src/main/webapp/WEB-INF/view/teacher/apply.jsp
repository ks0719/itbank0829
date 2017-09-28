<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/view/template/header.jsp" %>
    <div class="example-modal">
	<div class="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">강사 신청</h4>
				</div>
				<div class="modal-body"> 

	<form action="apply" method="post" enctype="multipart/form-data">
		<input type="hidden" name="teacherno" value="${teacherNo}">
		<input type="hidden" name="name" value="${mynick}">
		<input class="form-control" value="[닉네임] ${mynick }" readonly>
		<br><br>
		[사진] <input class="form-control" type="file" name="file" onchange="previewImage(this,'upload_photo')">
		<br><br>
		<div class="upload_photo" id="upload_photo" style="display: in-block; width: 150px; height: 150px;"> <!-- 스타일 지정 필수 -->
			<img width="167px;" id="prev_upload_photo" src="http://www.placehold.it/150x150"/>
		</div>

		<br><br>
		[주 언어] 
		<select class="form-control" name="sort" title="언어 선택">
			<option value="C언어">C언어</option>
			<option value="C++">C++</option>
			<option value="JAVA">JAVA</option>
			<option value="JSP">JSP</option>
			<option value="Javascript">Javascript</option>
			<option value="Python">Python</option>
		</select>
		<br><br>
		<textarea class="textarea" rows="10" cols="80" name="career" placeholder="경력소개" required></textarea>
		<br><br>
		<textarea class="textarea" rows="20" cols="80" name="intro" placeholder="자기소개" required></textarea>
		<br><br>
		<input class="btn btn-primary pull-right" type="submit" value="신청하기"> 
		<button class="btn btn-primary pull-right" onclick="javscript:history.back();">이전 페이지로 돌아가기</button>
	</form>
				</div>
				<br>
				<div class="modal-footer">
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/view/template/footer.jsp" %>