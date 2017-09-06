<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp"%>
	<h1>1:1 문의</h1>
	<form >
	아이디 <input type="text" value="${dto.id }" readonly><br>
	문의유형 <input type="text" value="${dto.type }" readonly>
	<br>
	제목<input type="text" value="${dto.title }" readonly><br>
	내용<br>
	<textarea rows="30" cols="30" name="detail" readonly>${dto.detail }</textarea>
	</form>
<%@ include file="/WEB-INF/view/template/footer.jsp"%>