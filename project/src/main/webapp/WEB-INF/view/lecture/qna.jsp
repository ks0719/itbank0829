<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/view/template/header.jsp" %>

<h1>강사님께 질문하기</h1>

<div>
	<form action="qna" method="post" enctype="multipart/form-data">
		<input type="hidden" name="student" value="${nick}">
		질문 내용
		<br>
		<textarea rows="8" cols="60" required></textarea>
		<br><br>
		<input type="file" name="file">
		<small>사진파일만 올릴 수 있습니다</small>
		<br><br>
		<input type="submit" value="등록하기">
	</form>
</div>
	
<%@ include file="/WEB-INF/view/template/footer.jsp" %>