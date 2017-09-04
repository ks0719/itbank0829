<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp" %>

<form target="${pageContext.request.contextPath}/lecture/req" method="post">
과목명 <br><input type="text" value="" readonly><br>
강사명<br><input type="text" value="" readonly><br>
보유 포인트 <input type="number" value="" readonly><br>
차감 포인트<input type="number" value="" readonly><br> 
잔여 포인트<input type="number" value="" readonly><br>
<h3>정말로 신청하시겠습니까? <br>보유 포인트가 차감됩니다.</h3>
<input type="button" onclick="javascript:history.back()" value="돌아가기">
<input type="submit" value="신청하기">
</form>
<%@ include file="/WEB-INF/view/template/footer.jsp" %>