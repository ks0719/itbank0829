<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/view/template/teacherHeader.jsp" %>

<h1>마일리지 출금</h1>

보유 마일리지 : ${point} 포인트
<br><br>
<form action="withdrow" method="post">
	<input type="number" name="point" placeholder="출금액">
	<br><br>
	<input type="text" name="bank" placeholder="은행명">
	<br><br>
	<input type="text" name="account" placeholder="계좌번호">
	<br><br>
	<input type="submit" value="출금 신청">
</form>

<%@ include file="/WEB-INF/view/template/teacherFooter.jsp" %>