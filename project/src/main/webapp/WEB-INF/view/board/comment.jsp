<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	${comment.detail}-${comment.writer} ${comment.reg}
	<a href="#" class="comment-best" value="${comment.no}">추천</a> <span id="best${comment.no}">${comment.best}</span>
	<a href="#" class="comment-delete" value="${comment.no}">삭제</a>
	<br>
