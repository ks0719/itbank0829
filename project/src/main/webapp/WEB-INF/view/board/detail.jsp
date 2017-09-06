<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/view/template/boardHeader.jsp" %>

<h1>상세보기</h1>
<div class="page-wrap">
	<div class="table-wrap">
		<table border="1" class="study" rules=rows>
			<tr>
				<td class="head">
					말머리
				</td>
				<td>
					${unit.head}
				</td>
			</tr>
			<tr>
				<td class="head">
					제목
				</td>
				<td>
					${unit.title}
				</td>
			</tr>
			<tr>
				<td class="head">
					작성자
				</td>
				<td>
					${unit.writer}
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea rows="20" cols="80">${unit.detail}</textarea>
				</td>
			</tr>
			<tr>
				<td>
					첨부파일
				</td>
				<td>
					${unit.originfile}
					<a href="download/${unit.no}">
						<img src="<c:url value="/img/download.png"/>" width="20" height="20">
					</a>
				</td>
			</tr>
		</table>
		<div class="align-right">
			<input type="button" value="수정하기" class="input-btn" onclick="location.href='edit?no=${unit.no}';">
			<input type="button" value="삭제하기" class="input-btn" onclick="location.href='delete?no=${unit.no}';">
			<input type="button" value="목록으로" class="input-btn" onclick="location.href='${pageContext.request.contextPath}/board/${path}';">
		</div>
	</div>
</div>
	
<%@ include file="/WEB-INF/view/template/boardFooter.jsp" %>