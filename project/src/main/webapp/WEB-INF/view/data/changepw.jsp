<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp" %>
    <div class="example-modal">
	<div class="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">비밀번호  변경</h4>
				</div>
				<div class="modal-body"> 
<form name="chpw" action="${pageContext.request.contextPath }/data/changepw" method="post" onsubmit="return changepw()">
<input class="form-control" type="password" id="pw" name="pw" placeholder="현재비밀번호 입력" required><br>
<input class="form-control" type="password"  name="newpw" placeholder="새 비밀번호 입력" required><br>
<input class="form-control" type="password"  name="repw" placeholder="새 비밀번호 재입력" required><br>
<input class="btn btn-primary pull-right" type="submit"  value="수정하기">
</form>
</div>
				<br>
				<div class="modal-footer">
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/view/template/footer.jsp"%>