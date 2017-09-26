<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<c:if test="${empty rdto }">
			<button onclick="location.href='${pageContext.request.contextPath}/consumer/reply?no=${param.no }'">답변달기</button>
			</c:if>
			<c:if test="${!empty rdto }">
			<table border="1" class="tableUnit" rules=rows>
				<tr>
					<td class="head">
						작성자
					</td>
					<td class="text-left">
						${rdto.nick}
					</td>
				</tr>
				<tr style="text-align: left">
					<td colspan="2">
						${rdto.detail}
					</td>
				</tr>
			</table>
			</c:if>
			
	</div>
</div>
<%@ include file="/WEB-INF/view/template/footer.jsp"%>