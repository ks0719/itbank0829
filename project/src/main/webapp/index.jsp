<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head> 
	<body>
		<h1>게시판</h1>
		<h1><a href="board/free">자유게시판 이동</a></h1>
		<h1><a href="board/info">정보게시판 이동</a></h1>
		<h1><a href="board/qna">QnA게시판 이동</a></h1>
		<h1><a href="board/require">요청게시판 이동</a></h1>
		<h1><a href="board/store">판매게시판 이동</a></h1>
		<h1>고객센터</h1>
		<h1><a href="consumer/basic">고객센터 홈</a></h1>
		<h1><a href="consumer/rule">사이트 규정</a></h1>
		<h1><a href="consumer/b2c">1:1질문</a></h1>
		<h1>내정보</h1>
		<h1><a href="data/maininfo">메인정보</a></h1>
		<h1><a href="data/edit">수정하기</a></h1>
		<h1><a href="data/exit">회원탈퇴</a></h1>
		<h1><a href="data/note">쪽지함</a></h1>
		<h1><a href="data/pay">마일리지결제</a></h1>
		<h1><a href="data/point">포인트정보</a></h1>
		<h1>수업 정보</h1>
		<h1><a href="<c:url value="/study"/>">메인수업페이지</a></h1>
		<h1><a href="<c:url value="/teacher"/>">강사정보</a></h1>
		<h1><a href="<c:url value="/class"/>">수업정보</a></h1>
		<h1><a href="<c:url value="/req"/>">수강하기</a></h1>
		<h1><a href="<c:url value="/assess"/>">평가하기</a></h1>
	</body>
</html>
