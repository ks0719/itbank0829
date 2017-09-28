<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/WEB-INF/view/template/header.jsp" %>
 
<div class="example-modal">
	<div class="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">강사 상세보기</h4>
				</div>
				<div class="modal-body"> 
					<table width="600" class="table table-bordered table-hover">
						<tbody>
							<tr>
								<td rowspan="2" width="20%">
									<img src="<c:url value="/img/bono.png"/>" width="180" height="180">
								</td>
								<td><input class="form-control" type="text" value="[${info.sort}] ${info.name}" readonly></td> 
							</tr>
							<tr>
								<td><input class="form-control" type="text" value="${info.career}" readonly></td>
							</tr>
							<tr>
								<td colspan="2"><textarea class="textarea" readonly>${info.intro}</textarea></td>
							</tr>
						</tbody>
					</table>
					<div class="pull-left">
						평점 : ${info.grade} 강의횟수 : ${info.count}
					</div>
					<c:if test="${info.name ne mynick}">
						<a href="" onclick="window.open('${pageContext.request.contextPath}/data/mail/send?nick=${info.name}', '쪽지보내기', 'width=800, height=500'); return false;" class="btn btn-primary pull-right">쪽지보내기</a> 
					</c:if>
					<a href="${pageContext.request.contextPath}/lecture/study?page=1&type=teacher&key=${info.name}" class="btn btn-primary pull-right">현재 진행중인 강의</a>
					<a href="lecturer${url}"class="btn btn-primary pull-right">목록보기</a>
					<br>
				</div>
				<div class="modal-footer">
					<br>
				</div>
			</div>
		</div>
	</div>
</div>