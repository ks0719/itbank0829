<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp"%>
<div class=row>
	<div class="col-md-12">
		<div class="box box-primary">
			<div class="box-header">
				<h1 class="box-title">1:1 문의</h1>
			</div>
			
			<form action="${pageContext.request.contextPath}/consumer/b2c" method="post" id="insertBoardFrm" enctype="multipart/form-data">
				<div class="box-body">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">닉네임</div>
							<input style="width:30%; min-width:130px" type="text" name="id" class="form-control" value="${mynick}" readonly>
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">문의 유형</div>
							<select name="type" class="form-control" style="display: inline; width: 80px;" required>
								<option value="환불">환불</option>
								<option value="강의">강의</option>
								<option value="계정">계정</option>
								<option value="일반">일반</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<input placeholder="제목" type="text" name="title" required class="form-control">
					</div>
					<div id="b2cBox" class="form-group">
						<textarea class="b2cTextarea" rows="20" name="detail" required id="editor"></textarea>
					</div>
				</div>
				<div class="box-footer">
					<div class="pull-right">
						<button class="btn btn-default" onclick="location.href='${pageContext.request.contextPath }/consumer/b2clist'">목록으로</button>
						<button id="submit" type="submit" class="btn btn-primary">문의하기</button>
					</div>
					<br>
				</div>
			</form>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/view/template/footer.jsp"%>