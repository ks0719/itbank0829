<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/template/mailHeader.jsp" %>
<form action="" method="post">
	<table>
		<tbody>
		 	<tr>
		 		<td>받는사람</td>
		 		<td>
		 			<c:choose>
		 				<c:when test="${nick==''||nick==null}">
		 					<input type="text" id="mail_receiver" name="mail_receiver" placeholder="받는사람" required>
		 					<input type="button" id="check" value="확인" onclick="isExist($('#mail_receiver').val());">
		 				</c:when>
		 				<c:otherwise>
		 					<input type="text" id="mail_receiver" name="mail_receiver" value="${nick}" disabled placeholder="받는사람" required>
		 					<input type="button" id="check" value="취소" onclick="isExist($('#mail_receiver').val());">
		 				</c:otherwise>
		 			</c:choose>
		 		</td>
		 	</tr>
		 	<tr>
		 		<td>제목</td>
		 		<td><input type="text" name="mail_title" placeholder="제목" required></td>
		 	</tr>
		 	<tr>
		 		<td>내용</td>
		 		<td><textarea name="mail_content" rows="50" cols="80" required></textarea></td>
		 	</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					<button type="button" onclick="location.href='${pageContext.request.contextPath}/data/mail?box=${param.box}'">목록으로</button>
					<c:choose>
		 				<c:when test="${nick==''||nick==null}">
		 					<input id="send" disabled type="submit" value="전송">
		 				</c:when>
		 				<c:otherwise>
		 					<input id="send" type="submit" value="전송">
		 				</c:otherwise>
		 			</c:choose>
				</td>
			</tr>
		</tfoot>
	</table>
</form> 
    
<%@ include file="/WEB-INF/view/template/mailFooter.jsp" %>