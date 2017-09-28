<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp"%>



	<h1>1:1 문의</h1>
	<form action="${pageContext.request.contextPath}/consumer/b2c" method="post" id="insertBoardFrm" enctype="multipart/form-data">
	닉네임 <input type="text" name="id" value="${nick }" readonly><br>
	문의유형 <select name="type" class="form-control" style="display: inline; width: 13%;" required>
	<option value="환불">환불</option>
	<option value="강의">강의</option>
	<option value="계정">계정</option>
	<option value="일반">일반</option>
	</select><br>
	제목<input type="text" name="title" required><br>
	내용<br>
	<textarea rows="30" cols="30" name="detail" required id="editor"></textarea>
	</form>
	
	
	
<%@ include file="/WEB-INF/view/template/footer.jsp"%>