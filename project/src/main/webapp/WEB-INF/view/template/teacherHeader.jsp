<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/template/header.jsp" %>
<table>
	<thead>
		<nav id="nav">
			<ul>
				<li><a href="${pageContext.request.contextPath}/teacher/profile" class="no-uline">프로필 관리</a></li>     
		        <li><a href="${pageContext.request.contextPath}/teacher/resister" class="no-uline">강의 등록</a></li>
				<li><a href="${pageContext.request.contextPath}/teacher/myLectures" class="no-uline">진행 중인 강의</a></li>
				<li><a href="${pageContext.request.contextPath}/teacher/assessView" class="no-uline">수강평가 보기</a></li>
				<li><a href="${pageContext.request.contextPath}/teacher/withdrow" class="no-uline">마일리지 출금</a></li>
			</ul>
		</nav>
	</thead>
	<tbody>
		<tr>
			<td>