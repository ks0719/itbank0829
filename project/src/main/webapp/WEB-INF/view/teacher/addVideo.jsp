<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tr>
	<td>${count}</td>
	<td class="title" id="title${count}">${title}</td>
	<td><a href="" onclick="window.open('${pageContext.request.contextPath}/lecture/listening?video=${filename}', '동영상 보기', 'width=800, height=500'); return false;">동영상 보기</a></td>
	<td>
		<Button class="video-edit" data-count="${count}" data-origin="${title}">수정</Button>
	</td>
</tr>