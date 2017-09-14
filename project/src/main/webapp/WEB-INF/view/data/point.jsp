<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<form name="pay" action="${pageContext.request.contextPath}/data/point" method="post">
보유 포인트 <input type="number" readonly>
충전하실 포인트를 선택해 주세요.<select name="point">
<option value="1">1000p(10,000\)</option>
<option value="2">3000p(30,000\)</option>
<option value="3">5000p(50,000\)</option>
<option value="4">7000p(70,000\)</option>
<option value="5">10000p(100,000\)</option>
</select>
<hr>
정말로 충전하시겠습니까? 아래의 버튼을 누르시면 결제 페이지로 넘어갑니다.
<hr>
<input type="submit" value="결제하기"> 
</form>
</body>
</html>