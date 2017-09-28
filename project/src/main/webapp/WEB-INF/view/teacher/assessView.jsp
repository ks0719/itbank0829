<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%@ include file="/WEB-INF/view/template/teacherHeader.jsp" %>

<div class="row borN">
	<div class="col-xs-12">
		<div class="box box-primary">
			<div class="box-body no-padding">
			<h2>수강 평가 보기</h2>
			
			<div class="table-responsive">
				<table id="example2" class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>지식평점</th>
							<th>가격평점</th>
							<th>태도평점</th>
							<th>한마디</th>
		                </tr>
					</thead>
					<tbody id="videoList">
					<c:forEach var="assess" items="${list}">
						<tr>
							<td>${assess.kin_grade}</td>
							<td>${assess.price_grade}</td>
							<td>${assess.kind_grade}</td>
							<td>${assess.detail}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				<input type="button" class="btn btn-default pull-right" onclick="location.href='myLecture${url}';" value="돌아가기">
			</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/view/template/teacherFooter.jsp" %>