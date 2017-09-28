<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<section class="content">
	<div class="col-md-12">
		<div class="box box-primary">
		
			<div class="box-header with-border">
				<h3 class="box-title">문의 유형 : ${dto.type}</h3>
			</div>
			
			
			<div class="box-body no-padding">
			
				<div class="mailbox-read-info">
					<h3>${dto.title}</h3>
               		<h5><span class="mailbox-read-time pull-right">닉네임 : ${dto.id}</span></h5>
               		<br>
				</div>
				
				<div class="mailbox-read-message">
					${dto.detail}
				</div>
				
			</div>
			
			<div class="box-footer" style="display: inline-block">
				<div class="pull-right">
					<button class="btn btn-default" onclick="location.href='${pageContext.request.contextPath }/consumer/b2clist'">목록으로</button>
					<c:if test="${empty rdto }">
						<button class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/consumer/reply?no=${param.no }'">답변달기</button>
					</c:if>
				</div>
				<br>
			</div>
			
		</div>
	</div>
	
	
	<c:if test="${!empty rdto }">
		<!-- 답변 달았으면 여기다 찍어줘야함 -->
		
		<div class="col-md-12">
			<div class="box box-primary">
			
				<div class="box-header with-border">
					<h3 class="box-title">답변</h3>
				</div>
				
				<div class="box-body no-padding">
				
					<div class="mailbox-read-info">
						<h3>운영자 : ${rdto.nick}</h3>
					</div>
					
					<div class="mailbox-read-message">
						${rdto.detail}
					</div>
					
				</div>
				
			</div>
		</div>
		
	</c:if>
</section>

<%@ include file="/WEB-INF/view/template/footer.jsp"%>