<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp" %>
    <div class="example-modal">
	<div class="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">비밀번호 확인</h4>
				</div>
				<div class="modal-body">    
				<form action="logout" id="checkPw"method="post"  onsubmit="return pwCheck();">
					<input class="form-control" type="password" name="pw"  id="pwcheck" placeholder="PW입력">
					<br>
					<input class="btn btn-primary pull-right" type="submit" value="확인">
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