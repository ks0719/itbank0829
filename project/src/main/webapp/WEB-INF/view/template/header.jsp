<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<html>

<script>
	$(document).ready(function() {
		$(".clickToinfo").on("click", function() {
			window.open('class', '', 'width=1000, height=700, menubar=no');
		});
	});
</script>

<head>
	<meta charset="utf-8">
	<title>Welcome</title>
</head> 
<body>
	
	<table>
		<tr>
			<th rowspan="5">
                <div>
                    <h3>회원정보 넣을 곳</h3>
                </div>
                <div>
                    <h3><a href="${pageContext.request.contextPath}/board/free">커뮤니티(자유게시판)</a></h3>
                    <h3><a href="${pageContext.request.contextPath}/lecture/study">수업정보</a></h3>
                    <h3><a href="${pageContext.request.contextPath}/lecture/teacher">강사정보</a></h3>
                    <h3><a href="${pageContext.request.contextPath}/consumer/basic">고객센터(고객센터 홈)</a></h3>
                </div>
			</th>
            
            
            <td style="vertical-align: top">
                <table>
                    <tr>
                        <th>
                            공지/그림/광고
                        </th>
                    </tr>
                    <tr>
                        <td>
                            <div>