<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/view/template/teacherHeader.jsp" %>

<div class="example-modal">
	<div class="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">마일리지 출금</h4>
				</div>
				<div class="modal-body">
				
					<label>[닉네임] ${mynick}</label>
					<br>
					<label>[보유 마일리지] ${point} 포인트</label>
					<br><br>
					<form action="withdrow" method="post">
						<input type="number" class="form-control" name="point" placeholder="출금액">
						<br>
						<input type="text" class="form-control" name="bank" placeholder="은행명">
						<br>
						<input type="text" class="form-control" name="account" placeholder="계좌번호">
						<br>
						<input type="submit" class="btn btn-primary pull-right" value="출금 신청">
					</form>
				
				</div>
				
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/view/template/teacherFooter.jsp" %>