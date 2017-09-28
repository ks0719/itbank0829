<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class=row>
	<div class="col-md-12">
		<div class="box box-primary">
			<div class="box-header"></div>
			
				<div class="box-body">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">닉네임</div>
							<input style="width:30%; min-width:130px; outline:none;" type="text" class="form-control" value="${dto.id}" readonly>
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">문의 유형</div>
							<input style="width:80px; outline:none;" type="text" class="form-control" value="${dto.type}" readonly>
						</div>
					</div>
					<div class="form-group">
						<input style="outline:none;" type="text" value="${dto.title}" class="form-control" readonly>
					</div>
					<div id="b2cBox" class="form-group">
						<textarea style="outline:none;" class="b2cTextarea" rows="20" name="detail" readonly>${dto.detail}</textarea>
					</div>
				</div>
				<div class="box-footer">
					<div class="pull-right">
						<button id="submit" type="submit" class="btn btn-primary"><i class="fa fa-envelope-o"></i>문의하기</button>
					</div>
					<br>
				</div>
		</div>
	</div>
</div>











			<table border="1" class="tableUnit" rules=rows>
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
			
<%@ include file="/WEB-INF/view/template/footer.jsp"%>