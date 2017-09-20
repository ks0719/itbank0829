<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/template/header.jsp" %>
<table>
	<thead>
		<nav id="nav">
			<ul>
				<li><a href="${pageContext.request.contextPath}/teacher/profile" class="no-uline">프로필 관리</a></li>     
		        <li><a href="${pageContext.request.contextPath}/teacher/resister" class="no-uline">강의 등록</a></li>
				<li><a href="${pageContext.request.contextPath}/teacher/myLectures?where=myLecture" class="no-uline">내 강의 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/teacher/myLectures?where=students" class="no-uline">학생 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/teacher/withdrow" class="no-uline">마일리지 출금</a></li>
			</ul>
		</nav>
	</thead>
	<tbody>
		<tr>
			<td>