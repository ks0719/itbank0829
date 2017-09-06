<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/view/template/mailHeader.jsp" %>

<table>
	<tbody>
	 	<tr>
	 		<td>보낸사람</td>
	 		<td>${mail.getMail_writer()}</td>
	 		<td>받는사람</td>
	 		<td>${mail.getMail_receiver()}</td>
	 	</tr>
	 	<tr>
	 		<td>제목</td>
	 		<td>${mail.getMail_title()}</td>
	 	</tr>
	 	<tr>
	 		<td>날짜</td>
	 		<td>${mail.getMail_reg()}</td>
	 	</tr>
	 	<tr>
	 		<td>내용</td>
	 		<td>${mail.getMail_content()}</td>
	 	</tr>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="2">
				<form action="" method="post" name="delete" id="garbage">
					<input type="hidden" name="no" value="${mail.no}">
					<input type="hidden" name="box" value="${box}">
				</form>
				<form action="mail/send?box=${param.box}" method="get" name="send" id="send">
					<input type="hidden" name="nick" value="${mail.getMail_writer()}">
				</form>
				<!-- 신고 구현해야함 -->
				신고
				<button type="button" onclick="document.send.submit();">답장</button>
				<button type="button" onclick="location.href='${pageContext.request.contextPath}/data/mail?box=${param.box}'">목록으로</button>
				<button type="button" onclick="del(); document.delete.submit();">삭제하기</button>
			</td>
		</tr>
	</tfoot>
</table>

<%@ include file="/WEB-INF/view/template/mailFooter.jsp" %>