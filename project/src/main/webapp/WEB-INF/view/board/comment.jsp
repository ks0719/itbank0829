<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<c:if test="${comment.context eq context}">
		<tr>
			<td>
				${comment.detail}-${comment.writer} ${comment.reg}
				<a href="#">ÃßÃµ</a> ${comment.best}
				<br>
			</td>
		</tr>
	</c:if>