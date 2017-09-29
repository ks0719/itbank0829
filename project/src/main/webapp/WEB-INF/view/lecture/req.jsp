<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp" %>

    <div class="example-modal">
	<div class="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">비밀번호 찾기</h4>
				</div>

<form target="" method="post">
	<input type="hidden" name="no" value="${lecture.no}">
	<input type="hidden" name="page" value="${page}">
	과목명 <br><input class="form-control" type="text" value="${lecture.title}" readonly><br>
	강사명<br><input class="form-control" type="text" value="${lecture.teacher}" readonly><br>
	보유 포인트 <input class="form-control" type="number" value="${mileage}" readonly><br>
	차감 포인트<input class="form-control" type="number" value="${lecture.price}" readonly><br> 
	잔여 포인트<input class="form-control" type="number" name="mileage" value="${mileage-lecture.price}" readonly><br>
	<c:choose>
		<c:when test="${mileage-lecture.price<0}">
			<h3>마일리지가 부족합니다</h3><br>
			<input class="btn btn-success" type="button" value="충전하기">
		</c:when>
		<c:otherwise>
			<h3>정말로 신청하시겠습니까? <br>보유 포인트가 차감됩니다.</h3>
			<input class="btn btn-primary" type="submit" value="신청하기">
		</c:otherwise>
	</c:choose>
	<input class="btn btn-default" type="button" onclick="javascript:history.back()" value="돌아가기">
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