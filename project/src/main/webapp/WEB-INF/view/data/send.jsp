<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/template/mailHeader.jsp" %>
<form action="" method="post">
	<table>
		<tbody>
		 	<tr>
		 		<td>받는사람</td>
		 		<td>
		 			<input type="text" name="mail_receiver" placeholder="받는사람" required>
		 			<button>받는 사람이 존재하는 사람인지 확인하는 버튼</button>
		 		</td>
		 	</tr>
		 	<tr>
		 		<td>분류</td>
		 		<td>
		 			<select name="mail_tag">
		 				<option value="">없음</option>
		 				<option value="질문">질문</option>
		 			</select>
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
					취소
					<input type="submit" value="전송">
				</td>
			</tr>
		</tfoot>
	</table>
</form> 
    
<%@ include file="/WEB-INF/view/template/mailFooter.jsp" %>