<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp" %>

 <div class="example-modal">
	<div class="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">내정보  변경</h4>
				</div>
				<div class="modal-body"> 
<form action="${pageContext.request.contextPath }/data/edit" method="post">
<label>아이디</label>
<input class="form-control" type="text" value="${dto.id }"  disabled><br>
<label>닉네임</label>
<div class="input-group">
<input class="form-control" type="text" value="${dto.nick }" name="nick"  id="nick" readonly required >
		<span class="input-group-btn">
		<input class="btn btn-info btn-flat" type="button"  id="nickcheck" value="변경" onclick= "dataEdit();">
		</span>
		</div>
		<br>
<label>이름</label>
<input class="form-control" type="text" value="${dto.name }"  disabled><br>
<label>우편 번호</label>
<div class="input-group">
<input class="form-control" type="text" value="${dto.post }" name="post" readonly>
<span class="input-group-btn">
	<input class="btn btn-info btn-flat" type="button" value="우편번호찾기" onclick="daumAddressSearch();">
		</span>
		</div>
		<br>
		<label>상세 주소</label>
<input class="form-control" type="text" value="${dto.addr1 }" name="addr1" readonly size="50"><br>
<input class="form-control" type="text" value="${dto.addr2 }" name="addr2"  size="50"><br>
<label>전화 번호</label>
<div class="input-group">
<input class="form-control" type="text" value="${dto.phone }" id="phone" name="phone" readonly required>
<span class="input-group-btn">
			<input class="btn btn-info btn-flat" type="button"  id="phonecheck" value="변경" onclick= "dataEdit2();">
</span>
</div>
<br>
<input class="btn btn-primary pull-right" type="submit" value="수정하기"  onclick="return dataSubmit();">
</form>

</div>
				<br>
				<div class="modal-footer">
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/view/template/footer.jsp"%>