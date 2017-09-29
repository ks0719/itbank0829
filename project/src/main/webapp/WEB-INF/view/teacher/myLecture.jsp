<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%@ include file="/WEB-INF/view/template/header.jsp" %>

<div class="example-modal">
	<div class="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">강의 정보</h4>
				</div>
				<div class="modal-body">
				
					<label>[닉네임] ${mynick}</label>
					<br><br>
					<label>[주언어] ${mylecture.tag}</label>
					<br><br>
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
					<label>[강의 제목] ${mylecture.title}</label>
					<br><br>
					<label>[강의 형태] ${mylecture.type}</label>
					<br><br>
					<label>[강의 기간] ${mylecture.period}</label>
					<br><br>
					<label>[강의 시간] ${mylecture.time}</label>
					<br><br>
					<label>[강의 가격] ${mylecture.price}</label>
					
					<br><br>
					<label>[강의소개]</label>
					<br>
					<textarea class="needResize" onkeydown="resize(this)" onkeyup="resize(this)" placeholder="강의 소개" name="intro" style="font-size:15; color: black; resize: none;" rows="10" readonly>${mylecture.intro}</textarea>
					<br><br>
					<label>[강의계획]</label>
					<br>
					<textarea class="needResize" onkeydown="resize(this)" onkeyup="resize(this)" placeholder="강의 계획" name="detail" style="font-size:15; color: black; resize: none;" rows="20" readonly>${mylecture.detail}</textarea>
					<br><br>
					
					<c:choose>
						<c:when test="${mylecture.state eq '종료'}">
							<label>[지식 평가] ${mylecture.kin_grade}</label>
							<br>
							<label>[가격 평가] ${mylecture.price_grade}</label>
							<br>
							<label>[태도 평가] ${mylecture.kind_grade}</label>
							<br><br>
							<Button class="btn btn-primary pull-right" onclick="location.href='assessView?no=${mylecture.no}&${url}';">평가보기</Button>
						</c:when>
						<c:when test="${mylecture.state eq '등록 가능'}">
							<Button class="btn btn-primary pull-right" onclick="location.href='lectureEdit?no=${mylecture.no}&${url}';">수정하기</Button>
							<c:choose>
								<c:when test="${mylecture.type eq '인강'}">
									<Button class="btn btn-success pull-right" onclick="location.href='videoList?no=${mylecture.no}&${url}';">동영상 관리</Button>
								</c:when>
							</c:choose>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${mylecture.type eq '원격강의'}">
							<Button onclick="#">원격강의</Button>
						</c:when>
					</c:choose>
					<Button class="btn btn-default pull-right" onclick="location.href='myLectures?${url}';">목록으로</Button>
				</div>
				
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>
</div>
    
<%@ include file="/WEB-INF/view/template/footer.jsp" %>