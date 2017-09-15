<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp" %>
<form name="chpw" action="${pageContext.request.contextPath }/data/changepw" method="post" onsubmit="return changepw()">
현재 비밀번호 입력<input type="password" id="pw" name="pw" required><br>
새 비밀번호 입력<input type="password"  name="newpw" required><br>
새 비밀번호 재입력<input type="password"  name="repw" required><br>
<input type="submit"  value="수정하기">
<button onclick="javscript:history.back();">이전 페이지로 돌아가기</button>

</form>
<%@ include file="/WEB-INF/view/template/footer.jsp"%>