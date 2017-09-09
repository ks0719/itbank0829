<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/view/template/boardHeader.jsp" %>

<h1>상세보기</h1>
<div class="page-wrap">
	<div class="table-wrap">
		<c:forEach var="board" items="${boardList}">
			<table border="1" class="tableUnit" rules=rows>
				<tr>
					<td class="head">
						말머리
					</td>
					<td class="text-left">
						${board.head}
					</td>
				</tr>
				<tr>
					<td class="head">
						제목
					</td>
					<td class="text-left">
						${board.title}
					</td>
				</tr>
				<tr>
					<td class="head">
						작성자
					</td>
					<td class="text-left">
						${board.writer}
					</td>
				</tr>
				<tr class="detail">
					<td colspan="2">
						${board.detail}
					</td>
				</tr>
				<c:if test="${board.originfile ne null}">
				<tr>
					<td class="head">
						첨부파일
					</td>
					<td class="text-left">
						${board.originfile}
							<a href="download/${board.no}">
								<img src="<c:url value="/img/download.png"/>" width="20" height="20">
							</a>
					</td>
				</tr>
				</c:if>
			</table>
			<div>
				<form action="#" class="board-comment" value="${board.no}">
					<input type="hidden" name="topcontext" value="${no}">
					<input type="hidden" name="context" value="${board.no}">
					<input type="hidden" name="writer" value="테스트유저1">
					<input type="text" name="detail" class="user-input" placeholder="댓글 입력">
					<input type="submit" class="input-btn" value="등록">
				</form>
				<div id="comment${board.no}">
				<c:forEach var="comment" items="${list}">
					<c:if test="${comment.context eq board.no}">
							${comment.detail}-${comment.writer} ${comment.reg}
							<a href="#" class="comment-best" value="${comment.no}">추천</a> <span id="best${comment.no}">${comment.best}</span>
							<br>
					</c:if>
				</c:forEach>
				</div>
			</div>
			<div class="align-right">
				<input type="button" value="답글쓰기" class="input-btn" onclick="location.href='reply?no=${board.no}&context=${no}';">
				<input type="button" value="추천하기" class="input-btn" onclick="location.href='best?no=${board.no}&context=${no}';">
				<input type="button" value="수정하기" class="input-btn" onclick="location.href='edit?no=${board.no}&context=${no}';">
				<input type="button" value="삭제하기" class="input-btn" onclick="location.href='delete?no=${board.no}&context=${no}';">
				<input type="button" value="목록으로" class="input-btn" onclick="location.href='${pageContext.request.contextPath}/board/${path}';">
			</div>
			<div class="empty-row"></div>
		</c:forEach>
	</div>
</div>
	
<%@ include file="/WEB-INF/view/template/boardFooter.jsp" %>