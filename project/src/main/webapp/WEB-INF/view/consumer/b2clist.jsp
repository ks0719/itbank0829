<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/view/template/header.jsp"%>
<div class="page-wrap">
	<div class="table-wrap">
		<table border="1" class="study" rules=rows>
		<tr>
				<th >
					[번호]
				</th>
				<th >
					[문의유형]
				</th>
				<th >
					[제목]
				</th>
				<th >
					[작성시간]
				</th>
				<th>
					[진행상태]
				</th>
			</tr>
		<c:forEach var="list" items="${list }">
		
			<tr>
			<a href="#">
				<th>
					[${list.rn }]
				</th>
				<th >
					[${list.type }]
				</th>
				<th>
					[${list.title }]
				</th>
				<th >
					[${list.reg }]
				</th>
				<th >
					[${list.state }]
				</th>
				</a>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div class="empty-row"></div>
<%@ include file="/WEB-INF/view/template/footer.jsp"%>