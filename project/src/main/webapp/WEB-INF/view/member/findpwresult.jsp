<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp" %>
    <div class="example-modal">
	<div class="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">비밀번호 변경</h4>
				</div>
				<div class="modal-body"> 
<form action="${pageContext.request.contextPath }/member/findpwresult" method="post"  onsubmit="return findpwsubmit();">
<input type="hidden" value="${id }" name="id" >
<input type="password" class="form-control" name="findnewpw" id="findnewpw" placeholder="새 비밀번호 입력" required><br>
<input type="password" class="form-control"  name="refindnewpw" id="refindnewpw" placeholder="새 비밀번호 재입력" required><br>
<input class="btn btn-primary pull-right" type="submit"  value="변경하기" >
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