<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/WEB-INF/view/template/header.jsp" %>


<div class="row">
	<div class="col-xs-12">
		<div class="box box-primary">
			<div class="box-body no-padding">
			
			<div class="table-responsive">
			
				<table id="example2" class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>수업태그</th>
							<th class="title">제목</th>
							<th>평점</th>
							<th>강의기간</th>
							<th>강사명</th>
		                </tr>
					</thead>
					<tbody>
						<c:forEach var="info" items="${list}">
							<tr data-no="${info.no}" data-page="${page}" data-type="${type}" data-key="${key}" data-url="class" class="clickToinfo" style="cursor: pointer">
								<td>[${info.tag}]</td>
								<td class="title">${info.title}</td>
								<td>${info.kin_grade}/${info.price_grade}/${info.kind_grade}</td>
								<td>${info.period}</td>
								<td class="teacher">${info.teacher}</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="7" style="text-align:center;">
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
							</td>
						</tr>
						<tr>
							<td colspan="7" style="text-align:center;">
								<div class="form-group">
									<form action="study" class="wrap">
										<input type="hidden" name="page" value="1">
										<select name="type" title="분류 선택" class="user-input">
											<option value="tag">수업태그</option>
											<option value="title">제목</option>
											<option value="teacher">강사명</option>
										</select>
										<input type="search" name="key" class="form-control" style="display: inline; width: 35%;" placeholder="검색 내용" value="${key}" required>
										<input type="submit" value="검색" class="btn btn-primary">
									</form>
								</div>
							</td>
						</tr>
					
					</tfoot>
				</table>
				
			</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/view/template/footer.jsp" %>