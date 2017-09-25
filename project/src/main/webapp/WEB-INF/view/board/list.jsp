<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%@ include file="/WEB-INF/view/template/header.jsp" %>


<div class="row">
	<div class="col-xs-12">
	
		<div class="box">
			<div class="box-body">
				<table id="example2" class="table table-bordered table-hover">
					<thead>
						<tr>
		                  <th>번호</th>
		                  <th class="title">[말머리] 제목</th>
		                  <th>작성자</th>
		                  <th>등록일</th>
		                  <th>조회수</th>
		                  <th>추천수</th>
		                </tr>
					</thead>
					<tbody>
						<c:forEach var="info" items="${list}">
							<tr>
								<td>${info.no}</td>
								<td class="title">
									[${info.head}] <a href="${path}/detail?no=${info.no}">${info.title}</a>
								</td>
								<td>${info.writer}</td>
								<td>${info.auto}</td>
								<td>${info.read}</td>
								<td>${info.best}</td>
		                    </tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="7" style="text-align:right;">
								<c:choose>
									<c:when test="${empty cookie.mynick.value}">
										<input type="button" value="글쓰기" class="btn btn-primary" onclick="alert('로그인이 필요한 서비스 입니다'); return false;">
							       	</c:when>
							       	<c:otherwise>
										<input type="button" value="글쓰기" class="btn btn-primary" onclick="location.href='${path}/write?seq=1&context=';">
							       	</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</tfoot>
				</table>
				
				
				<div class="paging-wrap">
					<c:if test="${startBlock > 1}">
				        <button class="btn btn-default btn-sm" onclick="location.href='${url}page=${startBlock - 1}'"><i class="fa fa-chevron-left"></i></button>
					</c:if>
			
					<c:forEach var="i" begin="${startBlock}" end="${endBlock}" step="1">
						<c:choose>
							<c:when test="${i eq page}">
								<button class="btn btn-flat btn-sm">${i}</button>
							</c:when>
							<c:otherwise>
								<button class="btn btn-default btn-sm" onclick="location.href='${url}page=${i}'">${i}</button>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					
					<c:if test="${endBlock < blockTotal}">
			        	<button class="btn btn-default btn-sm" onclick="location.href='${url}page=${endBlock + 1}'"><i class="fa fa-chevron-right"></i></button>
					</c:if>
			    </div>
			</div>
		</div>
		
		<form action="${path}" class="wrap">
			<input type="hidden" name="page" value="1">
			<select name="type" title="분류선택" class="user-input">
				<option value="head">말머리</option>
				<option value="title">제목</option>
				<option value="writer">작성자</option>
			</select>
			<input type="search" name="key" class="user-input" placeholder="검색 내용" value="${key}" required>
			<input type="submit" value="검색" class="input-btn">
		</form>
		
	</div>
</div>

<%@ include file="/WEB-INF/view/template/footer.jsp" %>