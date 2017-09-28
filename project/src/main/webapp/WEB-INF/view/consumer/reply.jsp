<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp"%>
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
			
			<div class="box-footer">
				<div class="pull-right">
				</div>
			</div>
			
			<div class="box-footer" style="display: inline-block">
				<div class="pull-right">
					<button class="btn btn-default" onclick="location.href='${pageContext.request.contextPath }/consumer/b2clist'">목록으로</button>
				</div>
				<br>
			</div>
			
		</div>
	</div>
	
	
	
	<div class="col-md-12">
		<div class="box box-primary">
			<div class="box-header">
				<h1 class="box-title">답변 달기</h1>
			</div>
			
			<form action="${pageContext.request.contextPath}/consumer/reply" method="post" id="insertBoardFrm">
				<input type="hidden" value="${param.no}" name="no">
				<div class="box-body">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">닉네임</div>
							<input style="width:30%; min-width:130px" type="text" name="id" class="form-control" value="${mynick}" readonly>
						</div>
					</div>
					<div id="b2cBox" class="form-group">
						<textarea class="b2cTextarea" rows="20" name="detail" required id="editor"></textarea>
					</div>
				</div>
				<div class="box-footer">
					<div class="pull-right">
						<button id="submit" type="submit" class="btn btn-primary">등록하기</button>
					</div>
					<br>
				</div>
			</form>
		</div>
	</div>
	
</section>
			
<%@ include file="/WEB-INF/view/template/footer.jsp"%>