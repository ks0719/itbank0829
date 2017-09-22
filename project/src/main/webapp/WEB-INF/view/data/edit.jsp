<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp" %>
<form action="${pageContext.request.contextPath }/data/edit" method="post">
ID<input type="text" value="${dto.id }"  disabled><br>
닉네임<input type="text" value="${dto.nick }" name="nick"  id="nick" readonly required >
		<input type="button"  id="nickcheck" value="변경" onclick= "dataEdit();"><br>
이름<input type="text" value="${dto.name }"  disabled><br>
주소<input type="text" value="${dto.post }" name="post" readonly>
	<input type="button" value="우편번호찾기" onclick="daumAddressSearch();"><br>
기본주소<input type="text" value="${dto.addr1 }" name="addr1" readonly size="50"><br>
상세주소<input type="text" value="${dto.addr2 }" name="addr2"  size="50"><br>
휴대전화<input type="text" value="${dto.phone }" id="phone" name="phone" readonly required>
			<input type="button"  id="phonecheck" value="변경" onclick= "dataEdit2();"><br>
<input type="submit" value="수정하기"  onclick="return dataSubmit();">
<button onclick="javscript:history.back();">이전 페이지로 돌아가기</button>

</form>
<%@ include file="/WEB-INF/view/template/footer.jsp"%>