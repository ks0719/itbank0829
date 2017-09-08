<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp" %>
<form action="${pageContext.request.contextPath }/data/edit" method="post">
ID<input type="text" value="${dto.id }"  readonly><br>
닉네임<input type="text" value="${dto.nick }" name="nick" readonly><br>
이름<input type="text" value="${dto.name }"  readonly><br>
주소<input type="text" value="${dto.post }" name="post" readonly><br>
상세주소<input type="text" value="${dto.addr1 }" name="addr1" readonly size="50"><br><input type="text" value="${dto.addr2 }"  name="addr2" readonly><br>
휴대전화<input type="text" value="${dto.phone }" name="phone"  readonly><br>
<input type="submit" value="수정하기">
<button onclick="javscript:history.back();">이전 페이지로 돌아가기</button>

</form>
<%@ include file="/WEB-INF/view/template/footer.jsp"%>