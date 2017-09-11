<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp" %>
<form target="${pageContext.request.contextPath}/teacher/remote" method="post">
상대방 아이피 입력 <input type="text" name="ip"><br>
<input type="submit" value="시작하기">
</form>



<%@ include file="/WEB-INF/view/template/footer.jsp"%>