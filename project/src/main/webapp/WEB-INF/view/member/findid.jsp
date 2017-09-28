<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ include file="/WEB-INF/view/template/header.jsp" %> 
<div class="example-modal">
	<div class="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">아이디찾기</h4>
				</div>
				<div class="modal-body">     
     
     
	<form action="" method="post" onsubmit="return findid();">
		<input class="form-control" type="text" name="name" id="name" placeholder="이름입력" required>
		<br><br>
		<input class="form-control" type="number" name="phone" id="phone" placeholder="전화번호입력" required>
		<br><br>
		<input class="btn btn-primary pull-right"   type="submit" value="확인" >
	</form>
	<br>
	</div>
				
				<div class="modal-footer">
				<br>
				</div>
			</div>
		</div>
	</div>
</div>
	
