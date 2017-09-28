<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tr>
	<td>${count}</td>
	<td class="title" id="title${count}">${title}</td>
	<td><a href="" onclick="window.open('${pageContext.request.contextPath}/lecture/listening?video=${filename}', '동영상 보기', 'width=800, height=500'); return false;">동영상 보기</a></td>
	<td>
		<Button class="btn btn-warning video-edit" data-filename="${info.filename}">수정</Button>
		<Button class="btn btn-danger video-delete" data-count="${count}" data-filename="${filename}">삭제</Button>
	</td>
</tr>