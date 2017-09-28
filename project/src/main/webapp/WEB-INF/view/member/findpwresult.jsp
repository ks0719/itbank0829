<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp" %>
<html>
	<script>
	
	</script>
<head>
<title>비밀번호 설정</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/member/findpwresult" method="post"  onsubmit="return findpwsubmit();">
 <input type="hidden" value="${id }" name="id" >
새 비밀번호 입력<input type="password"  name="findnewpw" id="findnewpw" required><br>
새 비밀번호 재입력<input type="password"  name="refindnewpw" id="refindnewpw" required><br>
<input type="submit"  value="변경하기" >
</form>
</body>
</html>
<%@ include file="/WEB-INF/view/template/footer.jsp"%>