<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%@ include file="/WEB-INF/view/template/teacherHeader.jsp" %>

<h1>강의 상세정보</h1>

<br><br>
닉네임 : ${mynick}
<br><br>
언어 : ${mylecture.tag}
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
<br><br>
강의 제목 : ${mylecture.title}
<br><br>
강의 형태 : ${mylecture.type}
<br><br>
강의 기간 : ${mylecture.period}
<br><br>
강의 시간 : ${mylecture.time}
<br><br>
강의 가격 : ${mylecture.price}
<br><br>
강의소개
<br>
<textarea rows="10" cols="80" readonly>${mylecture.intro}</textarea>
<br><br>
강의계획
<br>
<textarea rows="20" cols="80" readonly>${mylecture.detail}</textarea>
<br><br>
<c:choose>
	<c:when test="${mylecture.state eq '종료'}">
		지식 평가 : ${mylecture.kin_grade}
		<br>
		가격 평가 : ${mylecture.price_grade}
		<br>
		태도 평가 : ${mylecture.kind_grade}
		<br><br>
		<Button onclick="location.href='assessView?no=${mylecture.no}&${url}';">평가보기</Button>
	</c:when>
	<c:when test="${mylecture.state eq '등록 가능'}">
		<Button onclick="location.href='lectureEdit?no=${mylecture.no}&${url}';">수정하기</Button>
		<c:choose>
			<c:when test="${mylecture.type eq '인강'}">
				<Button onclick="location.href='videoList?no=${mylecture.no}&${url}';">동영상 관리</Button>
			</c:when>
		</c:choose>
	</c:when>
</c:choose>
<c:choose>
	<c:when test="${mylecture.type eq '원격강의'}">
		<Button onclick="#">원격강의</Button>
	</c:when>
</c:choose>
<Button onclick="location.href='myLectures?${url}';">목록으로</Button>
    
<%@ include file="/WEB-INF/view/template/teacherFooter.jsp" %>