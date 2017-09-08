<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/template/header.jsp" %>

<div class="page-wrap">
	<div class="table-wrap">
		<table border="1" class="tableUnit" rules=rows>
			<tr>
				<th>
					번호
				</th>
				<th>
					분류
				</th>
				<th>
					강사명
				</th>
				<th>
					강의횟수
				</th>
				<th>
					평점
				</th>
				<th>
					등록일
				</th>
			</tr>
			<c:forEach var="teacher" items="${list}">
			<tr>
				<td>
					[${teacher.no}]
				</td>
				<td>
					${teacher.tag}
				</td>
				<td>
					${teacher.name}
				</td>
				<td>
					${teacher.count}
				</td>
				<td>
					${teacher.grade}
				</td>
				<td>
					${teacher.reg}
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
			<h1>${startBlock}</h1>
			<h1>${page}</h1>
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
		<select name="type" title="검색 기준" class="user-input">
			<option value="tag">분류</option>
			<option value="name">강사명</option>
		</select>
		<input type="search" name="key" class="user-input" placeholder="검색 내용" value="${key}" required>
		<input type="submit" value="검색" class="input-btn">
	</form>
</div>

<%@ include file="/WEB-INF/view/template/footer.jsp" %>