<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="teacher" items="${list}">
	<tr>
		<td>
			[${teacher.no}]
		</td>
		<td>
			${teacher.tag}
		</td>
		<td>
			${teacher.name}
		</td>
		<td>
			${teacher.count}
		</td>
		<td>
			${teacher.grade}
		</td>
		<td>
			${teacher.reg}
		</td>
	</tr>
</c:forEach>