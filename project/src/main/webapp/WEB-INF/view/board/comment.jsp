<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${comment.detail ne null}">
		<div id="comment${comment.no}">
			${comment.detail}-${comment.writer} ${comment.reg}
			<c:choose>
				<c:when test="${not empty cookie.mynick.value}">
					<a href="" class="comment-best" value="${comment.no}">��õ</a> <span id="best${comment.no}">${comment.best}</span>
				</c:when>
				<c:otherwise>
					<a href="" onclick="alert('�α����� �ʿ��� ���� �Դϴ�'); return false;">��õ</a> <span>${comment.best}</span>
				</c:otherwise>
			</c:choose>
				<a href="" class="comment-delete" value="${comment.no}">����</a>
			<br>
		</div>
	</c:when>
</c:choose>