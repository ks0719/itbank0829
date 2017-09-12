<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp" %>    
<html>
<head>
<title>회원탈퇴</title>
</head>
<body>	
<form action="logout" id="checkPw"method="post"  onsubmit="return pwCheck();">
	<h2>비밀번호 확인</h2>
	<input type="password" name="pw"  id="pwcheck" placeholder="PW입력">
	<input type="submit" value="확인">
</form>
</body>
</html>
<%@ include file="/WEB-INF/view/template/footer.jsp" %>    