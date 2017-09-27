<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/template/header.jsp" %>


<div class="row">
	<div class="col-xs-12">
		<div class="box box-primary">
			<div class="box-body no-padding">
			
			<div class="table-responsive">
			
				<table id="example2" class="table table-bordered table-hover">
					<thead>
					
						<tr>
							<th data-placement="up" class="lecturer-array" value="sort" data-page="${page}" data-type="${type}" data-key="${key}" style="cursor: pointer">
								분류로 정렬
							</th>
							<th>
								강사명
							</th>
							<th data-placement="up" class="lecturer-array" value="count" data-page="${page}" data-type="${type}" data-key="${key}" style="cursor: pointer">
								강의횟수순 정렬
							</th>
							<th data-placement="up" class="lecturer-array" value="grade" data-page="${page}" data-type="${type}" data-key="${key}" style="cursor: pointer">
								평점순 정렬
							</th>
							<th>
								등록일
							</th>
						</tr>
					
					</thead>
					<tbody>
					
						<c:forEach var="teacher" items="${list}">
							<tr data-name="${teacher.name}" data-page="${page}" data-type="${type}" data-key="${key}" class="toLecturerInfo" style="cursor: pointer">
								<td>${teacher.sort}</td>
								<td>${teacher.name}</td>
								<td>${teacher.count}</td>
								<td>${teacher.grade}</td>
								<td>${teacher.auto}</td>
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
									<form action="lecturer" class="wrap">
										<input type="hidden" name="page" value="1">
										<select name="type" title="검색 기준" class="form-control" style="display: inline; width: 15%;">
											<option value="sort">강사분류</option>
											<option value="name">강사명</option>
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