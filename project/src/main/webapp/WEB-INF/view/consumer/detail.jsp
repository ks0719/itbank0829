<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp"%>
	<h1>1:1 문의</h1>
	<div class="page-wrap">
	<div class="table-wrap">
			<table border="1" class="tableUnit" rules=rows>
				<tr>
					<td class="head">
						분류
					</td>
					<td class="text-left">
						${dto.type}
					</td>
				</tr>
				<tr>
					<td class="head">
						제목
					</td>
					<td class="text-left">
						${dto.title}
					</td>
				</tr>
				<tr>
					<td class="head">
						작성자
					</td>
					<td class="text-left">
						${dto.id}
					</td>
				</tr>
				<tr class="detail">
					<td colspan="2">
						${dto.detail}
					</td>
				</tr>
			</table>
	</div>
</div>
<%@ include file="/WEB-INF/view/template/footer.jsp"%>