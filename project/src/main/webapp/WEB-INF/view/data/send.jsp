<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/template/mailHeader.jsp" %>


<div class="box box-primary">
	<form action="" method="post">
		<div class="box-body">
			<div class="form-group">
			<div class="input-group">
				<c:choose>
	 				<c:when test="${nick==''||nick==null}">
	 					<input class="form-control" type="text" id="mail_receiver" name="mail_receiver" placeholder="받는 사람 닉네임" required>
	 					<span class="input-group-btn"><input class="btn btn-info btn-flat" style="display: inline;" type="button" id="check" value="확인" onclick="isExist($('#mail_receiver').val());"></span>
	 				</c:when>
	 				<c:otherwise>
	 					<input class="form-control" type="text" id="mail_receiver" name="mail_receiver" value="${nick}" readonly placeholder="받는 사람 닉네임" required>
	 					<span class="input-group-btn"><input class="btn btn-info btn-flat" style="display: inline;" type="button" id="check" value="취소" onclick="isExist($('#mail_receiver').val());"></span>
	 				</c:otherwise>
	 			</c:choose>
	 		</div>
            </div>
			<div class="form-group">
				<input class="form-control" name="mail_title" placeholder="제목" required/>
			</div>
			<div class="form-group">
				<textarea name="mail_content" required id="compose-textarea" class="form-control" style="height: 300px"></textarea>
			</div>
		</div>
		<div class="box-footer">
			<button onclick="location.href='${pageContext.request.contextPath}/data/mail?box=${param.box}'" class="btn btn-default" data-toggle="tooltip" title="List"><i class="fa fa-list"></i> 목록으로</button>
			<c:choose>
				<c:when test="${nick==''||nick==null}">
					<button id="send" disabled type="submit" class="btn btn-primary" data-toggle="tooltip" title="Send"><i class="fa fa-envelope-o"></i> 보내기</button>
				</c:when>
				<c:otherwise>
					<button id="send" type="submit" class="btn btn-primary" data-toggle="tooltip" title="Send"><i class="fa fa-envelope-o"></i> 보내기</button>
				</c:otherwise>
			</c:choose>
		</div>
	</form>
</div>
    
<%@ include file="/WEB-INF/view/template/mailFooter.jsp" %>