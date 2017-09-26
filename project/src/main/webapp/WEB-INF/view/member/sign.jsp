<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp" %>    


<div class="example-modal">
	<div class="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">회원 가입</h4>
				</div>
				<div class="modal-body">
				
					
					<form action="" id="sign" method="post" onkeydown="if(event.keyCode==13) return false;" onsubmit="return submitOK();">
						<div class="input-group">
							<input class="form-control" type="text" name="id" id="id" placeholder="ID입력"  required>
							<span class="input-group-btn">
								<input class="btn btn-info btn-flat" type="button" id="idcheck" value="중복확인" onclick="idCheck();">
							</span>
						</div>
						<br>
						<input class="form-control" type="password" name="pw" id="pw" placeholder="pw입력" required>
						<br>
						<input class="form-control" type="password" id="pw2" name="pw2" placeholder="PW재입력"  required>
						<br>
						<input class="form-control" type="text" name="name" placeholder="이름입력" required>
						<br>
						<div class="input-group">
							<input class="form-control" type="number" id="phone"name="phone" placeholder="번호입력(-없이)" required>
							<span class="input-group-btn">
								<input class="btn btn-info btn-flat" type="button"  id="pcheck" value="중복확인" onclick="phoneCheck();">
							</span>
						</div>
						<br>
						<div class="input-group">
							<input class="form-control" type="text" id="nick" name="nick" placeholder="닉네임입력" required>
							<span class="input-group-btn">
								<input class="btn btn-info btn-flat" type="button"  id="nickcheck" value="중복확인" onclick="nickCheck();">
							</span>
						</div>
						<br>
						<input class="form-control" type="text" name="sort" placeholder="사용가능한 언어입력" required>
						<br>
						<div class="input-group">
							<input class="form-control" type="text" name="post" placeholder="우편번호"  readonly>
							<span class="input-group-btn">
								<input class="btn btn-info btn-flat" type="button" value="우편번호찾기" onclick="daumAddressSearch();">
							</span>
						</div>
						<br>
						<input class="form-control" type="text" name="addr1" placeholder="기본주소"  readonly>
						<br>
						<input class="form-control" type="text" name="addr2" placeholder="상세주소" required>
						<br>
						
						<button class="btn btn-primary pull-right" id="sub">가입하기</button>
					</form>
				</div>
				
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/view/template/footer.jsp" %>