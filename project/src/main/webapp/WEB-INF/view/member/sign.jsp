<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp" %>    
<h1>회원 가입</h1>
<form action="" id="sign" method="post" onkeydown="if(event.keyCode=13) return false;" onsubmit="return submitOK();">
	<input type="text" name="id" id="id" placeholder="ID입력"  required>
	<input type="button"  id="idcheck" value="중복확인" onclick="idCheck();">
	<br><br>
	<input type="password" name="pw" placeholder="pw입력" required>
	<br><br>
	<input type="password" name="pw2" placeholder="PW재입력"  required>
	<br><br>
	<input type="text" name="name" placeholder="이름입력" required>
	<br><br>
	<input type="text" id="nick" name="nick" placeholder="닉네임입력" required>
	<input type="button"  id="nickcheck" value="중복확인" onclick="nickCheck();">
	<br><br>
	<input type="number" id="phone"name="phone" placeholder="번호입력(-없이)" required>
	<input type="button"  id="pcheck" value="중복확인" onclick="phoneCheck();">
	<br><br>
	<input type="text" name="post" placeholder="우편번호"  readonly>
	<input type="button" value="우편번호찾기" onclick="daumAddressSearch();">
	<br><br>
	<input type="text" name="addr1" placeholder="기본주소"  readonly>
	<br><br>
	<input type="text" name="addr2" placeholder="상세주소" required>
	<br><br>
	<input type="text" name="sort" placeholder="사용가능한 언어입력" required>
	<br><br>
	<input id="sub" type="submit" value="완료">
</form>

<%@ include file="/WEB-INF/view/template/footer.jsp" %>