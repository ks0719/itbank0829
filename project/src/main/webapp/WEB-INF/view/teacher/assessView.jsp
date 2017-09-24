<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%@ include file="/WEB-INF/view/template/teacherHeader.jsp" %>

<h1>수강 평가 보기</h1>

<table>
	<tbody>
		<tr>
			<th>
				지식평점
			</th>
			<th>
				가격평점
			</th>
			<th>
				태도평점
			</th>
			<th>
				한마디
			</th>
		</tr>
		<c:forEach var="assess" items="${list}">
		<tr>
			<td>
				${assess.kin_grade}
			</td>
			<td>
				${assess.price_grade}
			</td>
			<td>
				${assess.kind_grade}
			</td>
			<td>
				${assess.detail}
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

<%@ include file="/WEB-INF/view/template/teacherFooter.jsp" %>