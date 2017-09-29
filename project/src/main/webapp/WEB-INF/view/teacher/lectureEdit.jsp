<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%@ include file="/WEB-INF/view/template/header.jsp" %>

<div class="example-modal">
	<div class="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">강의 수정</h4>
					<small>수정 시 다시 심사가 진행됩니다.</small>
				</div>
				<div class="modal-body">
					<form action="lectureEdit" method="post" enctype="multipart/form-data">
						<input type="hidden" name="no" value="${mylecture.no}">
						<input type="hidden" name="teacherno" value="${teacherNo}">
						<input type="hidden" name="url" value="${url}">
						<input type="hidden" name="picture_name" value="${mylecture.picture_name}">
						<input type="hidden" name="picture_realname" value="${mylecture.picture_realname}">
						<input type="hidden" name="picture_type" value="${mylecture.picture_type}">
						<input type="hidden" name="picture_size" value="${mylecture.picture_size}">
				
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
						<input type="file" name="file" onchange="previewImage(this,'upload_photo')">
						<br>
						<c:choose>
							<c:when test="${mylecture.picture_name ne null}">
								<div class="upload_photo" id="upload_photo" style="display: in-block;"> <!-- 스타일 지정 필수 -->
									<img width="150px;" height="150px;" id="prev_upload_photo" src="<c:url value="/file/${mylecture.picture_name}"/>"/>
								</div>
							</c:when>
							<c:otherwise>
								<div class="upload_photo" id="upload_photo" style="display: in-block;"> <!-- 스타일 지정 필수 -->
									<img width="150px;" height="150px;" id="prev_upload_photo" src="http://www.placehold.it/150x150"/>
								</div>
							</c:otherwise>
						</c:choose>
						<br>
						<label>강의 제목</label>
						<input type="text" name="title" value="${mylecture.title}" placeholder="강의 제목" required>
						<br><br>
						<label>강의 기간</label>
						<input type="text" name="period" value="${mylecture.period}" placeholder="강의 기간" required>
						<br><br>
						<label>강의 시간</label>
						<input type="text" name="time" value="${mylecture.time}" placeholder="강의 시간" required>
						<br><br>
						<label>강의 형태</label>
						<select name="type" title="강의 형태">
							<option value="인강">인강</option>
							<option value="원격강의">원격강의</option>
						</select>
						<br><br>
						<label>강의 가격</label>
						<input type="text" name="price" value="${mylecture.price}" placeholder="강의 가격" required>
						<br><br>
						<label>[강의소개]</label>
						<br>
						<textarea class="needResize" onkeydown="resize(this)" onkeyup="resize(this)" placeholder="강의 소개" name="career" style="font-size:15; color: black; resize: none;" rows="10" cols="70" required>${mylecture.intro}</textarea>
						<br><br>
						<label>[강의계획]</label>
						<br>
						<textarea class="needResize" onkeydown="resize(this)" onkeyup="resize(this)" placeholder="강의 계획" name="detail" style="font-size:15; color: black; resize: none;" rows="20" cols="70" required>${mylecture.detail}</textarea>
						<br><br>
						
						<input type="submit" class="btn btn-primary pull-right" value="수정하기">
						<input type="button" class="btn btn-default pull-right" onclick="location.href='myLecture?no=${mylecture.no}&${url}';" value="돌아가기">
						
					</form>
				</div>
				
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>
</div>
    
<%@ include file="/WEB-INF/view/template/footer.jsp" %>