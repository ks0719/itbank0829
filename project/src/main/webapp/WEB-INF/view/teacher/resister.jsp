<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/view/template/header.jsp" %>

<div class="example-modal">
	<div class="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">강의 등록</h4>
				</div>
				<div class="modal-body">
				
					<form action="resister" method="post" onsubmit="return resisterOK();" enctype="multipart/form-data" style="magin: 0px;">
						<input type="hidden" name="teacherno" value="${teacherNo}">
						<label>[닉네임] ${mynick}</label>
						<br><br>
						<label>언어</label>
						<select name="tag" title="언어 선택">
							<option value="C언어">C언어</option>
							<option value="C++">C++</option>
							<option value="JAVA">JAVA</option>
							<option value="JSP">JSP</option>
							<option value="Javascript">Javascript</option>
							<option value="Python">Python</option>
						</select>
						<br><br>
						<label>[사진]</label>
						<input type="file" name="file" onchange="previewImage(this,'upload_photo')">
						<br>
						<div class="upload_photo" id="upload_photo" style="display: in-block; width: 150px; height: 150px;">
							<img width="167px;" id="prev_upload_photo" src="http://www.placehold.it/150x150"/>
						</div>
						<br><br>
						<input type="text" class="form-control" name="title" placeholder="강의 제목" required>
						<br>
						<label>강의 형태</label>
						<select name="type" title="강의 형태">
							<option value="인강">인강</option>
							<option value="원격강의">원격강의</option>
						</select>
						<br><br>
						<label>[동영상]</label>
						<input type="file" name="video" id="video" multiple>
						<br>
						<input type="text" class="form-control" name="period" id="period" placeholder="강의 기간 (YY.MM.DD~YY.MM.DD)" required>
						<br>
						<input type="text" class="form-control" name="time" id="time" placeholder="강의 시간 (HH:mm~HH:mm)" required>
						<br>
						<input type="text" class="form-control" name="price" id="price" placeholder="강의 가격" required>
						<br>
						<label>[강의소개]</label>
						<textarea class="needResize" onkeydown="resize(this)" onkeyup="resize(this)" placeholder="강의 소개" name="intro" style="font-size:15; color: black; resize: none;" rows="10" required>${mylecture.intro}</textarea>
						<br><br>
						<label>[강의계획]</label>
						<textarea class="needResize" onkeydown="resize(this)" onkeyup="resize(this)" placeholder="강의 계획" name="detail" style="font-size:15; color: black; resize: none;" rows="20" required>${mylecture.detail}</textarea>
						<br><br>
						<input type="submit" class="btn btn-primary pull-right" value="등록하기">
					</form>
				
					<Button class="btn btn-default pull-right" onclick="location.href='myLectures?${url}';">목록으로</Button>
				</div>
				
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>
</div>
    
<%@ include file="/WEB-INF/view/template/teacherFooter.jsp" %>