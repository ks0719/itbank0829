<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/template/header.jsp" %>
<table>
	<thead>
		<nav id="nav">
			<ul>
				<li><a href="${pageContext.request.contextPath}/board/free" class="no-uline">자유게시판</a></li>     
		        <li><a href="${pageContext.request.contextPath}/board/info" class="no-uline">정보게시판</a></li>
				<li><a href="${pageContext.request.contextPath}/board/qna" class="no-uline">QnA게시판</a></li>
				<li><a href="${pageContext.request.contextPath}/board/require" class="no-uline">요청게시판</a></li>
				<li><a href="${pageContext.request.contextPath}/board/store" class="no-uline">판매게시판</a></li>
			</ul>
		</nav>
	</thead>
	<tbody>
		<tr>
			<td>