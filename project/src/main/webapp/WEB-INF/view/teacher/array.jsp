<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tr>
	<th class="lecturer-array" value="no">
		번호
	</th>
	<th class="lecturer-array" value="sort">
		분류
	</th>
	<th>
		강사명
	</th>
	<th class="lecturer-array" value="count">
		강의횟수
	</th>
	<th class="lecturer-array" value="grade">
		평점
	</th>
	<th>
		등록일
	</th>
</tr>
<c:forEach var="teacher" items="${list}">
<tr data-no="${teacher.no}" data-page="${page}" data-type="${type}" data-key="${key}" data-url="lecturerInfo" class="clickToinfo" style="cursor: pointer">
	<td>
		[${teacher.no}]
	</td>
	<td>
		${teacher.sort}
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
		${teacher.auto}
	</td>
</tr>
</c:forEach>