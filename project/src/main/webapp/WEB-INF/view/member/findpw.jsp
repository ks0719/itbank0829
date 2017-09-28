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
				<div class="modal-body">     
    
	<form action="${pageContext.request.contextPath }/member/findpw" method="post" onsubmit="return findpw();">
		<input class="form-control" type="text" name="id"  id="id" placeholder="ID입력">
		<br><br>
		<input class="form-control" type="text" name="name" id="name" placeholder="이름입력">
		<br><br>
		<input class="form-control" type="number" name="phone" id="phone" placeholder="전화번호입력">
		<br><br>
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