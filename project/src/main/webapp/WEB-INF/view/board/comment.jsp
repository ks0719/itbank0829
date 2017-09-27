<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${comment.detail ne null}">
		<div id="comment${comment.no}">
			${comment.detail}-${comment.writer} ${comment.reg}
			<c:choose>
				<c:when test="${not empty cookie.mynick.value}">
					<a href="" class="comment-best" value="${comment.no}">추천</a> <span id="best${comment.no}">${comment.best}</span>
				</c:when>
				<c:otherwise>
					<a href="" onclick="alert('로그인이 필요한 서비스 입니다'); return false;">추천</a> <span>${comment.best}</span>
				</c:otherwise>
			</c:choose>
				<a href="" class="comment-delete" value="${comment.no}">삭제</a>
			<br>
		</div>
	</c:when>
</c:choose>