<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/view/template/header.jsp" %>

<html>
<head>
<title>Insert title here</title>
</head>
<body>

<h1>강사신청</h1>

<div>
	<form action="apply" method="post">
		사진 : <input type="file" name="file">
		<br>
		닉네임 : ${nick}
		<br>
		<textarea placeholder="경력소개"></textarea>
		<textarea placeholder="자기소개"></textarea>
	</form>
</div>

</body>
</html>

<%@ include file="/WEB-INF/view/template/footer.jsp" %>