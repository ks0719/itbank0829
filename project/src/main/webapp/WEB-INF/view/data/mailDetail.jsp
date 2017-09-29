<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/view/template/mailHeader.jsp" %>

<div class="box box-primary">
	<div class="box-body no-padding">

		<div class="mailbox-read-info">
           <h3>${mail.getMail_title()}</h3>
           <h5>From: ${mail.getMail_writer()} <span class="mailbox-read-time pull-right">${mail.getMail_reg()}</span></h5>
         </div>
         
         <div class="mailbox-read-message">
           <p>${mail.getMail_content()}</p>
         </div>
         
         <div class="mailbox-controls with-border text-center">
           <div class="btn-group">
           	<!-- 신고, 차단 구현해야함 -->
       		<form action="" method="post" name="delete">
       			<input type="hidden" name="page" value="${page}">
       			<input type="hidden" name="box" value="${box}">
       		</form>
       		<form action="mail/send" method="get" name="send">
       			<input type="hidden" name="nick" value="${mail.getMail_writer()}">
       			<input type="hidden" name="page" value="${page}">
       			<input type="hidden" name="box" value="${box}">
       		</form>
           	
           	<div class="pull-right">
				<button onclick="location.href='${pageContext.request.contextPath}/data/mail?box=${box}&page=${param.page}'" class="btn btn-default btn-sm" data-toggle="tooltip" title="List">목록으로</button>
	             <c:if test="${mynick != mail.getMail_writer()}">
	             	<button onclick="document.send.submit();" class="btn btn-primary btn-sm" data-toggle="tooltip" title="Reply">답장하기</button>
	             </c:if>
           	</div>
           	
           	<div class="pull-left">
				<button onclick="del(); document.delete.submit();" class="btn btn-danger btn-sm" data-toggle="tooltip" title="Delete">삭제하기</button>
				<button onclick="spam('${mail.getMail_writer()}');" class="btn btn-warning btn-sm">차단하기</button>
           	</div>
           	
           </div>
         </div>

	</div>
</div>


<%@ include file="/WEB-INF/view/template/mailFooter.jsp" %>