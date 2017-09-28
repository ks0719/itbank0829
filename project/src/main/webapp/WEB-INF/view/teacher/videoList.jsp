<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="/WEB-INF/view/template/header.jsp" %>

<div class="row borN">
	<div class="col-xs-12">
		<div class="box box-primary">
			<div class="box-body no-padding">
			
			<div class="table-responsive">
				<table id="example2" class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>번호</th>
							<th class="title">제목</th>
							<th>동영상</th>
							<th>제목 수정 / 삭제</th>
		                </tr>
					</thead>
					<tbody id="videoList">
					<c:forEach var="info" items="${videoList}" varStatus="status">
						<tr>
							<td>${status.count}</td>
							<td class="title" id="title${status.count}">${info.title}</td>
							<td><a href="" onclick="window.open('${pageContext.request.contextPath}/lecture/listening?video=${info.filename}', '동영상 보기', 'width=800, height=500'); return false;">동영상 보기</a></td>
							<td>
								<Button class="btn btn-warning video-edit" data-filename="${info.filename}">수정</Button>
								<Button class="btn btn-danger video-delete" data-no="${no}" data-filename="${info.filename}">삭제</Button>
							</td>
						</tr>
					</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4" id="addForm">
								<Button class="btn btn-default pull-right" id="video-form" data-no="${no}" data-url="${url}">동영상 추가</Button>
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