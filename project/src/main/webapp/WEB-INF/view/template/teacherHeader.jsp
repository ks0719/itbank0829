<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav id="nav" style="margin-top: 15px;">
	<ul>
		<li><a href="${pageContext.request.contextPath}/teacher/profile" class="topmenu">프로필 관리</a></li>     
        <li><a href="${pageContext.request.contextPath}/teacher/resister" class="topmenu">강의 등록</a></li>
		<li><a href="${pageContext.request.contextPath}/teacher/myLectures?where=myLecture" class="topmenu">내 강의 관리</a></li>
		<li><a href="${pageContext.request.contextPath}/teacher/myLectures?where=students" class="topmenu">학생 관리</a></li>
		<li><a href="${pageContext.request.contextPath}/teacher/withdrow" class="topmenu">마일리지 출금</a></li>
	</ul>
</nav>