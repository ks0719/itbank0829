<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/view/template/header.jsp" %>

<form action="${pageContext.request.contextPath}/lecture/assess" class="borN" method="post">
	<input type="hidden" name="no" value="${no}">
	지식 평점 : 
	<select name="kin_grade" class="form-control">
		<c:forEach var="i" begin="1" end="10" step="1">
			<option value="${i}">${i}</option>
		</c:forEach>
	</select>
	<br><br>
	가격 평점 : 
	<select name="price_grade" class="form-control">
		<c:forEach var="i" begin="1" end="10" step="1">
			<option value="${i}">${i}</option>
		</c:forEach>
	</select>
	<br><br>
	태도 평점 : 
	<select name="kind_grade" class="form-control">
		<c:forEach var="i" begin="1" end="10" step="1">
			<option value="${i}">${i}</option>
		</c:forEach>
	</select>
	<br><br>
	강사 평점 :
	<select name="teacher_grade" class="form-control">
		<c:forEach var="i" begin="1" end="10" step="1">
			<option value="${i}">${i}</option>
		</c:forEach>
	</select>
	<br><br>
	<textarea rows="10" cols="60" name="detail" placeholder="강사님께 한 마디" required></textarea>
	<br><br>
	<input type="submit" value="평가 완료">
</form>
	
<%@ include file="/WEB-INF/view/template/footer.jsp" %>