<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="" id="video-add" enctype="multipart/form-data">
	<input type="hidden" value="${no}">
	<input type="text" name="title" placeholder="강의 제목" required>
	<input type="file" name="video" required>
	<input type="submit" value="추가">
</form>