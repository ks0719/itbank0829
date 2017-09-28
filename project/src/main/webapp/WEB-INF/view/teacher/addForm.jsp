<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="addVideo" method="post" enctype="multipart/form-data">
	<input type="hidden" name="no" value="${no}">
	<input type="hidden" name="url" value="${url}">
	<input type="text" class="form-control" name="title" placeholder="강의 제목" required>
	<br>
	<input type="file" name="video" required>
	<br>
	<input type="submit" class="btn btn-primary" value="추가">
</form>