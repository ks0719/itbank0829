<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/view/template/boardHeader.jsp" %>

<h1>수정</h1>
<div class="page-wrap">
	<div class="table-wrap">
		<form action="edit" method="post" enctype="multipart/form-data">
			<input type="hidden" name="no" value="${no}">
			<table border="1" class="study" rules=rows>
				<tr>
					<td class="head">
						말머리
					</td>
					<td>
						<select name="head" class="user-input">
							<option value="" >말머리</option>
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
						<input type="text" name="title" value="${unit.title}" class="user-input" required>
					</td>
				</tr>
				<tr>
					<td class="head">
						작성자
					</td>
					<td>
						<input type="text" name="writer" value="${unit.writer}" class="user-input" readonly>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<textarea rows="20" cols="80" name="detail" class="user-input" required>${unit.detail}</textarea>
					</td>
				</tr>
				<tr>
					<td class="head">
						첨부파일
					</td>
					<td>
						${unit.originfile}
						<input type="file" name="file" >
					</td>
				</tr>
			</table>
			<div class="align-right">
				<input type="submit" value="수정" class="input-btn">
				<input type="button" value="목록으로" class="input-btn" onclick="location.href='${pageContext.request.contextPath}/board/${path}';">
			</div>
		</form>
	</div>
</div>
	
<%@ include file="/WEB-INF/view/template/boardFooter.jsp" %>