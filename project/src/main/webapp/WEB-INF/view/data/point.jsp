<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<form name="pay" action="${pageContext.request.contextPath}/data/point" method="post">
<input type="text" name="title"><br>
<input type="number" name="money"><br>
<input type="email" name="email"><br>
<input type="text" name="name"><br>
<input type="tel" name="phone"><br>
<input type="text" name="addr"><br>
<input type="text" name="post"><br>
<input type="submit" onclick="IMP.request_pay()"> 
</form>
</body>
</html>