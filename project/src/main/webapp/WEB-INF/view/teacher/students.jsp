<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%@ include file="/WEB-INF/view/template/teacherHeader.jsp" %>

<h1>수강생 정보</h1>

<c:forEach var="student" items="${list}">
	${student.nick} / ${student.sort} / ${student.level} / ${student.reg}
</c:forEach>

<Button onclick="location.href='qnaView${url}';">학생 Q&A</Button>
<Button onclick="location.href='myLectures${url}';">돌아가기</Button>

<%@ include file="/WEB-INF/view/template/teacherFooter.jsp" %>