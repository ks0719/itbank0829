<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/view/template/header.jsp" %>

<div class="row" class="borN">
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
		                </tr>
					</thead>
					<tbody>
						<c:forEach var="info" items="${list}">
							<tr>
								<td></td>
								<td class="title">${info.title}</td>
								<td><a href="">강의 듣기</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
			</div>
			</div>
		</div>
	</div>
</div>
	
<%@ include file="/WEB-INF/view/template/footer.jsp" %>