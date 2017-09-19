<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/template/header.jsp" %>

<h1>글쓰기</h1>
<div class="page-wrap">
	<div class="table-wrap">
		<form action="write" method="post" id="insertBoardFrm" enctype="multipart/form-data">
			<input type="hidden" value="${context}" name="context">
			<table border="1" class="tableUnit" rules=rows>
				<tr>
					<td class="head">
						말머리
					</td>
					<td class="text-left">
						<select name="head" title="말머리" class="user-input">
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
					<td class="text-left">
						<input type="text" class="user-input" name="title" required placeholder="제목" required>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<textarea rows="30" cols="30" name="detail" id="editor" required></textarea>
					</td>
				</tr>
				<tr>
					<td class="head">
						첨부파일
					</td>
					<td class="text-left">
						<input type="file" name="file">
					</td>
				</tr>
			</table>
			<div class="align-right">
				<input type="submit" id="submit" value="등록" class="input-btn">
				<input type="button" value="목록으로" class="input-btn" onclick="location.href='${pageContext.request.contextPath}/board/${path}';">
			</div>
		</form>
	</div>
</div>
	
<%@ include file="/WEB-INF/view/template/footer.jsp" %>