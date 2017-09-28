<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/template/header.jsp" %>


<div class=row>
	<div class="col-md-12">
		<div class="box box-primary">
			<div class="box-header">
				<h1 class="box-title">글쓰기</h1>
			</div>
			
			<form action="write" method="post" id="insertBoardFrm" enctype="multipart/form-data">
				<input type="hidden" value="${context}" name="context">
				<div class="box-body">
				
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">말머리</div>
							<select name="head" title="말머리" class="form-control" style="display: inline; width: 80px;" required>
								<option value="Java">Java</option>
								<option value="JSP">JSP</option>
								<option value="C언어">C언어</option>
								<option value="C++">C++</option>
								<option value="JavaScript">JavaScript</option>
								<option value="Python">Python</option>
								<option value="기타">기타</option>
							</select>
						</div>
					</div>
				
					<div class="form-group">
						<input placeholder="제목" type="text" name="title" required class="form-control">
					</div>
				
					<div id="b2cBox" class="form-group">
						<textarea class="b2cTextarea" rows="20" name="detail" required id="editor"></textarea>
					</div>
					
					<div class="form-group">
						<input class="btn btn-default btn-file" type="file" name="file">
					</div>
				</div>
				<div class="box-footer">
					<div class="pull-right">
						<button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/board/${path}';">목록으로</button>
						<button id="submit" type="submit" class="btn btn-primary">등록</button>
					</div>
					<br>
				</div>
			</form>
		</div>
	</div>
</div>
	
<%@ include file="/WEB-INF/view/template/footer.jsp" %>