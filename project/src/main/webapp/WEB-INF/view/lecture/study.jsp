<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/template/header.jsp" %>

		<div class="table-wrap">
			<table border="1" class="study" rules=rows>
				<tr class="clickToinfo">
					<td class="title">
						[수업태그]
						[제목]
					</td>
					<td class="teacher">
						[강사정보]
					</td>
				</tr>
				<tr class="clickToinfo">
					<td class="title">
						[수업태그]
						[제목]
					</td>
					<td class="teacher">
						[강사정보]
					</td>
				</tr>
				<tr class="clickToinfo">
					<td class="title">
						[수업태그]
						[제목]
					</td>
					<td class="teacher">
						[강사정보]
					</td>
				</tr>
				<tr class="clickToinfo">
					<td class="title">
						[수업태그]
						[제목]
					</td>
					<td class="teacher">
						[강사정보]
					</td>
				</tr>
				<tr class="clickToinfo">
					<td class="title">
						[수업태그]
						[제목]
					</td>
					<td class="teacher">
						[강사정보]
					</td>
				</tr>
			</table>
		</div>
		<div class="empty-row"></div>
		<form action="study/study">
			<select name="type" class="user-input">
				<option value="">분류 선택</option>
				<option value="수업태그">수업태그</option>
				<option value="제목">제목</option>
				<option value="강사명">강사명</option>
			</select>
			<input type="search" name="key" class="user-input" placeholder="검색 내용" required>
			<input type="submit" value="검색" class="input-btn">
		</form>
	
<%@ include file="/WEB-INF/view/template/footer.jsp" %>