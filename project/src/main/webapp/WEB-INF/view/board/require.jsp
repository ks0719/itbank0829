<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%@ include file="/WEB-INF/view/template/boardHeader.jsp" %> 

<h1>요청게시판</h1>

<div class="page-wrap">
	<div class="table-wrap">
		<table border="1" class="tableUnit" rules=rows>
			<tr>
				<th>
					번호
				</th>
				<th class="title">
					[말머리] 제목
				</th>
				<th>
					작성자
				</th>
				<th>
					등록일
				</th>
				<th>
					조회수
				</th>
				<th>
					추천수
				</th>
			</tr>
			<c:forEach var="info" items="${list}">
			<tr>
				<td>
					${info.no}
				</td>
				<td class="title">
					[${info.head}] <a href="${path}/detail?no=${info.no}">${info.title}</a>
				</td>
				<td>
					${info.writer}
				</td>
				<td>
					${info.auto}
				</td>
				<td>
					${info.read}
				</td>
				<td>
					${info.best}
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div class="align-right">
		<input type="button" value="글쓰기" class="input-btn" onclick="location.href='${path}/write';">
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
	<form action="${path}" class="wrap">
		<input type="hidden" name="page" value="1">
		<select name="type" class="user-input">
			<option value="">분류 선택</option>
			<option value="head">말머리</option>
			<option value="title">제목</option>
			<option value="writer">작성자</option>
		</select>
		<input type="search" name="key" class="user-input" placeholder="검색 내용" value="${key}" required>
		<input type="submit" value="검색" class="input-btn">
	</form>
</div>
	
<%@ include file="/WEB-INF/view/template/boardFooter.jsp" %>