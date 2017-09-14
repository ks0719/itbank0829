<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/WEB-INF/view/template/header.jsp" %>
<div class="page-wrap">
	<div class="table-wrap">
		<table border="1" class="tableUnit" rules=rows>
			<tr>
				<th>
					수업태그
				</th>
				<th class="title">
					제목
				</th>
				<th>
					평점
				</th>
				<th>
					개강일
				</th>
				<th class="teacher">
					강사정보
				</th>
			</tr>
			<c:forEach var="info" items="${list}">
			<tr data-no="${info.no}" data-page="${page}" data-type="${type}" data-key="${key}" data-url="class" class="clickToinfo" style="cursor: pointer">
				<td>
					[${info.tag}]
				</td>
				<td class="title">
					${info.title}
				</td>
				<td>
					${info.kin_grade}/${info.price_grade}/${info.kind_grade}
				</td>
				<td>
					${info.period}
				</td>
				<td class="teacher">
					${info.teacher}
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div class="row">
		<div class="paging-wrap">
			<c:if test="${startBlock > 1}">
		        <div class="paging-unit"><a href="${url}&page=${startBlock - 1}">&lt;</a></div>
			</c:if>
			
			<c:forEach var="i" begin="${startBlock}" end="${endBlock}" step="1">
				<c:choose>
					<c:when test="${i eq page}">
						<div class="paging-unit active">${i}</div>
					</c:when>
					<c:otherwise>
		       			<div class="paging-unit"><a href="${url}&page=${i}">${i}</a></div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<c:if test="${endBlock < blockTotal}">
		       	<div class="paging-unit"><a href="${url}&page=${endBlock + 1}">&gt;</a></div>
			</c:if>
		</div>
	</div>
	<form action="study" class="wrap">
		<input type="hidden" name="page" value="1">
		<select name="type" title="분류 선택" class="user-input">
			<option value="tag">수업태그</option>
			<option value="title">제목</option>
			<option value="teacher">강사명</option>
		</select>
		<input type="search" name="key" class="user-input" placeholder="검색 내용" value="${key}" required>
		<input type="submit" value="검색" class="input-btn">
	</form>
</div>
	
<%@ include file="/WEB-INF/view/template/footer.jsp" %>