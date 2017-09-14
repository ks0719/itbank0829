<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp" %>

<form target="" method="post">
	<input type="hidden" name="no" value="${lecture.no}">
	과목명 <br><input type="text" value="${lecture.title}" readonly><br>
	강사명<br><input type="text" value="${lecture.teacher}" readonly><br>
	보유 포인트 <input type="number" value="${mileage}" readonly><br>
	차감 포인트<input type="number" value="${lecture.price}" readonly><br> 
	잔여 포인트<input type="number" name="mileage" value="${mileage-lecture.price}" readonly><br>
	<c:choose>
		<c:when test="${mileage-lecture.price<0}">
			<h3>마일리지가 부족합니다</h3><br>
			<input type="button" value="충전하기">
		</c:when>
		<c:otherwise>
			<h3>정말로 신청하시겠습니까? <br>보유 포인트가 차감됩니다.</h3>
			<input type="submit" value="신청하기">
		</c:otherwise>
	</c:choose>
	<input type="button" onclick="javascript:history.back()" value="돌아가기">
</form>
<%@ include file="/WEB-INF/view/template/footer.jsp" %>