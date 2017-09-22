<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%@ include file="/WEB-INF/view/template/teacherHeader.jsp" %>

<h1>수강생 정보</h1>
<br>
<div class="page-wrap">
	<div class="table-wrap">
		<table border="1" class="tableUnit" rules=rows>
			<tr>
				<th>
					학생명
				</th>
				<th>
					주언어
				</th>
				<th>
					레벨
				</th>
				<th>
					등록일
				</th>
			</tr>
			<c:forEach var="student" items="${list}">
				<tr>
					<td>
						${student.nick}
					</td>
					<td>
						${student.sort}
					</td>
					<td>
						${student.lev}
					</td>
					<td>
						${student.reg}
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
<br><br>
<Button onclick="location.href='qnaView${url}';">학생 Q&A</Button>
<Button onclick="location.href='myLectures${url}';">돌아가기</Button>

<%@ include file="/WEB-INF/view/template/teacherFooter.jsp" %>