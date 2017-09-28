<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/view/template/header.jsp" %>
<html>
<head>
<title>비밀번호 찾기</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/member/findpw" method="post" onsubmit="return findpw();">
		<h1>비밀번호찾기</h1>
		<input type="text" name="id"  id="id" placeholder="ID입력">
		<br><br>
		<input type="text" name="name" id="name" placeholder="이름입력">
		<br><br>
		<input type="number" name="phone" id="phone" placeholder="전화번호입력">
		<br><br>
		<input type="submit" value="확인">
	</form>
</body>
</html>
<%@ include file="/WEB-INF/view/template/footer.jsp" %>