<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/WEB-INF/view/template/header.jsp" %>

<div class="example-modal borN">
	<div class="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">수업정보 상세보기</h4>
				</div>
				<div class="modal-body"> 
					<table width="600" class="table table-bordered table-hover">
						<tbody>
							<tr>
								<td rowspan="2" width="20%">
									<img src="<c:url value="/img/bono.png"/>" width="180" height="180">
								</td>
                    			<td><input type="text" class="form-control" value="[${info.tag}] ${info.title}" readonly></td> 
							</tr>
				
							<tr>
								<td><input type="text" class="form-control" value="${info.intro}" readonly></td>
							</tr>
 
                			<tr>
								<td colspan="2"><textarea class="textarea" readonly>${info.detail}</textarea></td>
                			</tr>
						</tbody>
					</table>
        
			        <div class="pull-left">
			        	<a href="${pageContext.request.contextPath}/teacher/lecturerInfo?name=${info.teacher}" class="btn btn-success">강사 정보</a>
			        </div>
	        		<c:choose>
			        	<c:when test="${empty cookie.mynick.value}">
			        		<a href="" onclick="alert('로그인이 필요한 서비스 입니다'); return false;" class="btn btn-primary pull-right">찜하기</a>
					        <a href="" onclick="alert('로그인이 필요한 서비스 입니다'); return false;" class="btn btn-primary pull-right">신청하기</a>
			        	</c:when>
			        	<c:otherwise>
					        <a href="#" value="${no}" class="btn btn-primary pull-right lectureWish">찜하기</a>
					        <c:choose>
					        	<c:when test="${!paid}">
					        		<a href="${pageContext.request.contextPath}/lecture/req?no=${no}&page=${param.page}" class="btn btn-primary pull-right">신청하기</a>
					        	</c:when>
					        	<c:when test="${mynick eq info.teacher}">
					        		신청불가
					        	</c:when>
					        	<c:otherwise>
					        		신청완료
					        	</c:otherwise>
					        </c:choose>
			        	</c:otherwise>
	        		</c:choose>
        			<a href="study${url}" class="btn btn-primary pull-right">목록보기</a>
					<br>
				</div>
				<div class="modal-footer">
					<br>
				</div>
			</div>
		</div>
	</div>
</div>
	
<%@ include file="/WEB-INF/view/template/footer.jsp" %>