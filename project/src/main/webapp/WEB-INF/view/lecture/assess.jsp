<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/view/template/header.jsp" %>

<h1>수강평가하기</h1>

<form action="assess">
	지식 평점 : 
	<select>
		<c:forEach var="i" begin="1" end="10" step="1">
			<option value="${i}">${i}</option>
		</c:forEach>
	</select>
	<br><br>
	가격 평점 : 
	<select>
		<c:forEach var="i" begin="1" end="10" step="1">
			<option value="${i}">${i}</option>
		</c:forEach>
	</select>
	<br><br>
	태도 평점 : 
	<select>
		<c:forEach var="i" begin="1" end="10" step="1">
			<option value="${i}">${i}</option>
		</c:forEach>
	</select>
	<br><br>
	<textarea rows="10" cols="80" placeholder="강사님께 한 마디 (5자 이상)" required></textarea>
	<br><br>
	<input type="submit" value="평가 완료">
</form>
	
<%@ include file="/WEB-INF/view/template/footer.jsp" %>