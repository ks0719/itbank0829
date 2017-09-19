<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/view/template/teacherHeader.jsp" %>

<h1>진행 중인 강의</h1>

<div class="page-wrap">
	<div class="table-wrap">
		<table border="1" class="tableUnit" rules=rows>
			<tr>
				<th>
					태그
				</th>
				<th>
					강의 제목
				</th>
				<th>
					평점
				</th>
				<th>
					가격
				</th>
				<th>
					강의 시간
				</th>
				<th>
					강의 기간
				</th>
				<th>
					상태
				</th>
			</tr>
			<c:forEach var="info" items="${list}">
			<tr data-no="${info.no}" data-page="${page}" data-type="${type}" data-key="${key}" class="toMyLecture" style="cursor: pointer">
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
					${info.price}
				</td>
				<td>
					${info.time}
				</td>
				<td>
					${info.period}
				</td>
				<td>
					${info.state}
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
		</select>
		<input type="search" name="key" class="user-input" placeholder="검색 내용" value="${key}" required>
		<input type="submit" value="검색" class="input-btn">
	</form>
</div>

<%@ include file="/WEB-INF/view/template/teacherFooter.jsp" %>