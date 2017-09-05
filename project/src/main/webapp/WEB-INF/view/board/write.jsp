<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/template/boardHeader.jsp" %>

<h1>글쓰기</h1>
<div class="page-wrap">
	<div class="table-wrap">
		<form action="write" method="post">
			<table border="1" class="study" rules=rows>
				<tr>
					<td class="head">
						말머리
					</td>
					<td>
						<select name="head" class="user-input">
							<option value="">말머리</option>
							<option value="Java">Java</option>
							<option value="JSP">JSP</option>
							<option value="C언어">C언어</option>
							<option value="C++">C++</option>
							<option value="JavaScript">JavaScript</option>
							<option value="Python">Python</option>
							<option value="기타">기타</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="head">
						제목
					</td>
					<td>
						<input type="text" class="user-input" name="title" required placeholder="제목">
					</td>
				</tr>
				<tr>
					<td class="head">
						작성자
					</td>
					<td>
						<input type="text" class="user-input" name="writer" value="${id}" required>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<textarea rows="20" cols="80" name="detail" required></textarea>
					</td>
				</tr>
			</table>
			<div class="align-right">
				<input type="submit" value="등록" class="input-btn">
				<input type="button" value="목록으로" class="input-btn" onclick="location.href='${pageContext.request.contextPath}/board/${path}';">
			</div>
		</form>
	</div>
</div>
	
<%@ include file="/WEB-INF/view/template/boardFooter.jsp" %>