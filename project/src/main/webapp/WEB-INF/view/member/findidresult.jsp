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
			
				<input class="form-control" value="아이디" readonly>
				<input class="form-control" value="${findidcheck }" readonly>
	</div>
				
	<div class="align-right">
		<button type="button" onclick="location.href='${pageContext.request.contextPath }/member/findpw';" class="btn btn-default">비밀번호찾기</button>
	</div>
				<div class="modal-footer">
				<br>
				</div>
			</div>
		</div>
	</div>
</div>
	
<%@ include file="/WEB-INF/view/template/footer.jsp" %> 