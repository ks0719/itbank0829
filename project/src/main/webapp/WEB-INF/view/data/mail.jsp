<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/view/template/mailHeader.jsp" %>

<div class="box box-primary">
	<div class="box-body no-padding">
		<div class="mailbox-controls">
			<form action="" method="post" name="garbageSubmit" id="garbageSubmit"></form>
        	<form action="" method="post" name="protectSubmit" id="protectSubmit"></form>
			<!-- 전부 체크 버튼 -->
			<button class="btn btn-default btn-sm checkbox-toggle"><i id="checkAll" class="fa fa-square-o"></i></button>
			<div class="btn-group">
				<!-- 삭제하기 버튼 -->
			  <button onclick="moving('garbage'); document.garbageSubmit.submit();" class="btn btn-default btn-sm"><i class="fa fa-trash-o"></i></button>
			  	<!-- 보호하기 버튼 -->
			  <button onclick="moving('protect'); document.protectSubmit.submit();" class="btn btn-default btn-sm"><i class="fa fa-briefcase"></i></button>
			</div><!-- /.btn-group -->
			<div class="pull-right">
				<c:if test="${page>=1 && page<=maxPage}">
		  			${start}-${end}/${maxLength}
				</c:if>
			  <div class="btn-group">
			  	<c:choose>
			  		<c:when test="${empty page||page==1}">
			  			<button onclick="location.href='${pageContext.request.contextPath}/data/mail?box=${box}&page=${page+1}'" class="btn btn-default btn-sm"><i class="fa fa-chevron-right"></i></button>
			  		</c:when>
			  		<c:when test="${not empty page && 1<page && page<maxPage}">
			  			<button onclick="location.href='${pageContext.request.contextPath}/data/mail?box=${box}&page=${page-1}'" class="btn btn-default btn-sm"><i class="fa fa-chevron-left"></i></button>
			  			<button onclick="location.href='${pageContext.request.contextPath}/data/mail?box=${box}&page=${page+1}'" class="btn btn-default btn-sm"><i class="fa fa-chevron-right"></i></button>
			  		</c:when>
			  		<c:when test="${not empty page && page==maxPage}">
			  			<button onclick="location.href='${pageContext.request.contextPath}/data/mail?box=${box}&page=${page-1}'" class="btn btn-default btn-sm"><i class="fa fa-chevron-left"></i></button>
			  		</c:when>
			  	</c:choose>
			  </div>
			</div>
		</div>
		
		<!-- 메일 리스트 찍을 공간 -->
		
		<div class="mailbox">
			<div class="table-responsive">
				<table class="table table-mailbox">
					<c:forEach var="list" items="${list}">
						<c:choose>
							<c:when test="${list.mail_read=='안읽음'}">
								<tr class="unread">
							</c:when>
							<c:otherwise>
								<tr>
							</c:otherwise>
						</c:choose>
							<td class="small-col"><input type="checkbox" name="chk" value="${list.no}"></td>
							<td class="name" onclick="mailDetail(${list.no});">${list.mail_writer}</td>
							<td class="subject" onclick="mailDetail(${list.no});">${list.mail_title}</td>
							<td class="time" onclick="mailDetail(${list.no});">${list.mail_reg}</td>
			    		</tr>
			    	</c:forEach>
				</table>
			</div>
		</div>

	</div>
</div>


<%@ include file="/WEB-INF/view/template/mailFooter.jsp" %>