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
						<label>닉네임  [${mynick}]</label>
						<br><br>
						<input class="form-control" type="file" name="file" onchange="previewImage(this,'upload_photo')">
						<br>
						<div class="upload_photo" id="upload_photo" style="display: in-block; width: 150px; height: 150px;">
							<img width="167px;" id="prev_upload_photo" src="http://www.placehold.it/150x150"/>
						</div>
						<br><br>
						<label>언어선택</label>
						<select name="sort" title="언어 선택">
							<option value="C언어">C언어</option>
							<option value="C++">C++</option>
							<option value="JAVA">JAVA</option>
							<option value="JSP">JSP</option>
							<option value="Javascript">Javascript</option>
							<option value="Python">Python</option>
						</select>
						<br><br>
						<textarea class="needResize" onkeydown="resize(this)" onkeyup="resize(this)" placeholder="경력 소개" name="career" style="font-size:15; color: black; resize: none;" rows="10" cols="70" required></textarea>
						<br><br>
						<textarea class="needResize" onkeydown="resize(this)" onkeyup="resize(this)" placeholder="자기 소개" name="intro" style="font-size:15; color: black; resize: none;" rows="20" cols="70" required></textarea>
						<br><br>
						<input type="submit" class="btn btn-primary pull-right" value="신청하기">
						<button class="btn btn-default pull-right" onclick="javscript:history.back();">이전 페이지로 돌아가기</button>
					</form>
					
				</div>
				
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/view/template/footer.jsp" %>