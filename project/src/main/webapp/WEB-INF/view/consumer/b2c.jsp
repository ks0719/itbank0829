<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/boardHeader.jsp"%>
	<h1>1:1 문의</h1>
	<form action="${pageContext.request.contextPath}/consumer/b2c" method="post" id="insertBoardFrm" enctype="multipart/form-data">
	아이디 <input type="text" name="id" required><br>
	문의유형 <select name="type" required>
	<option value="환불">환불</option>
	<option value="강의">강의</option>
	<option value="계정">계정</option>
	<option value="일반">일반</option>
	</select><br>
	제목<input type="text" name="title" required><br>
	내용<br>
	<textarea rows="30" cols="30" name="detail" required id="editor"></textarea>
	파일등록<input type="file">
	<input type="submit" id="submit" value="등록하기">
	</form>
<%@ include file="/WEB-INF/view/template/footer.jsp"%>