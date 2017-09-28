<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/view/template/header.jsp" %>
        <div class="example-modal">
	<div class="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">포인트 결제</h4>
				</div>
				<div class="modal-body"> 
<form action="${pageContext.request.contextPath}/data/point" method="post">
보유 포인트 <input class="form-control" type="text" value="${point }p" readonly><br>
충전하실 포인트를 선택해 주세요.<select class="form-control" name="point">
<option value="1">1,000p(10,000￦)</option>
<option value="2">3,000p(30,000￦)</option>
<option value="3">5,000p(50,000￦)</option>
<option value="4">7,000p(70,000￦)</option>
<option value="5">10,000p(100,000￦)</option>
</select>
<hr>
정말로 충전하시겠습니까? 아래의 버튼을 누르시면 결제 페이지로 넘어갑니다.
<hr>
<input class="btn btn-primary pull-right" type="submit" value="결제하기"> 
<button class="btn btn-primary pull-right" onclick="javscript:history.back();">이전 페이지로 돌아가기</button>
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
