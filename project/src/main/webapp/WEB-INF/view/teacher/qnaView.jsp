<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/view/template/teacherHeader.jsp" %>

<h1>학생 QnA</h1>

<table>
	<tbody>
		<tr>
			<th>
				질문자
			</th>
			<th>
				내용
			</th>
			<th>
				첨부파일
			</th>
			<th>
				등록일
			</th>
			<th>
				답변여부
			</th>
		</tr>
		<c:forEach var="qna" items="${list}">
		<tr>
			<td>
				${qna.student}
			</td>
			<td>
				${qna.detail}
			</td>
			<td>
				<a href="#">${qna.filename}</a>
			</td>
			<td>
				${qna.reg}
			</td>
			<td>
				<c:choose>
					<c:when test="${qna.state eq 'false'}">
						<a href="" onclick="window.open('${pageContext.request.contextPath}/data/mail/send?nick=${qna.student}', '쪽지보내기', 'width=800, height=500'); return false;">답변하기</a>
					</c:when>
					<c:otherwise>
						답변 완료
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

<div>
	<Button onclick="location.href='students${url}';">돌아가기</Button>
</div>

<%@ include file="/WEB-INF/view/template/teacherFooter.jsp" %>