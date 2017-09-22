<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ include file="/WEB-INF/view/template/header.jsp" %>     
<html>
<head>
<title>회원정보</title>
</head>
<body>
	<h1>회원 정보</h1>

	<table border="1" class="tableUnit" rules=rows>
	<c:forEach var="member" items="${memberList}">
		<tbody>
			<tr>
				<th>번호</th>
				<td>${member.no }</td>				
			</tr>
			<tr>
				<th>회원ID</th>
				<td>${member.id }</td>				
			</tr>
			<tr>
				<th>회원이름</th>
				<td>${member.name }</td>
			</tr>
			<tr>
				<th>회원닉네임</th>
				<td>${member.nick }</td>
			</tr>
			<tr>
				<th>회원가입일</th>
				<td>${member.reg }</td>
			</tr>
			<tr>
				<th>회원전화번호</th>
				<td>${member.phone }</td>
			</tr>
		
			<tr>
				<th>회원주소</th>
				<td>${member.addr1}</td>
			</tr>
			<tr>
				<th>회원가입일</th>
				<td>${member.reg }</td>
			</tr>
			<tr>
				<th>회원마일리지</th>
				<td>${member.mileage }</td>
			</tr>
			<tr>
				<th>회원등급</th>
				<td>${member.power }</td>
			</tr>
			<tr>
				<th>회원레벨</th>
				<td>${member.lev }</td>
			</tr>
		</tbody>
		</c:forEach>
	</table>
</body>
<div class="align-right">
		<button class="input-btn"  onclick="javscript:history.back();">이전 페이지로 돌아가기</button>
</div>
</html>
<%@ include file="/WEB-INF/view/template/footer.jsp" %> 