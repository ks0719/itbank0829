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
           	<!-- 내가 눌렀을때는 답장하기 작동하면 안됨 -->
           	<!-- 신고, 차단 구현해야함 -->
             <button onclick="document.send.submit();" class="btn btn-default btn-sm" data-toggle="tooltip" title="Reply">답장하기</button>
             <button onclick="del(); document.delete.submit();" class="btn btn-default btn-sm" data-toggle="tooltip" title="Delete">삭제하기</button>
             <button onclick="location.href='${pageContext.request.contextPath}/data/mail?box=${box}&page=${param.page}'" class="btn btn-default btn-sm" data-toggle="tooltip" title="List">목록으로</button>
           </div>
         </div>

	</div>
</div>


<%@ include file="/WEB-INF/view/template/mailFooter.jsp" %>