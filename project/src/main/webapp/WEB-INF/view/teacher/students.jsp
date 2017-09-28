<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%@ include file="/WEB-INF/view/template/teacherHeader.jsp" %>

<div class="row">
	<div class="col-xs-12">
		<div class="box box-primary">
			<div class="box-body no-padding">
				<h2>수강생 정보</h2>
				<br>
			<div class="table-responsive">
			
			
				<table id="example2" class="table table-bordered table-hover">
					<thead>
						<tr>
                            <th>학생명</th>
							<th>주언어</th>
							<th>레벨</th>
							<th>등록일</th>
		                </tr>
					</thead>
					<tbody>
						<c:forEach var="student" items="${list}">
							<tr>
								<td>${student.nick}</td>
								<td>${student.sort}</td>
								<td>${student.lev}</td>
								<td>${student.reg}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			
			<div class="pull-right"> 
				<Button class="btn btn-primary " onclick="location.href='qnaView${url}';">학생 Q&A</Button>
				<Button class="btn btn-default" onclick="location.href='myLectures${url}';">목록으로</Button>
			</div>
			    
			</div>
			
			</div>
		</div>
		
	</div>
</div>

<%@ include file="/WEB-INF/view/template/teacherFooter.jsp" %>