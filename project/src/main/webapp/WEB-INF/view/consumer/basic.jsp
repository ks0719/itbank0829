<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp"%>

<div style="height: 500px; widows: 100px; overflow: auto;">
<textarea rows="200" cols="150" readonly>
<%@include file="/WEB-INF/view/consumer/basicrule.jsp" %>
</textarea>
</div><br>

<button onclick="location='${pageContext.request.contextPath}/consumer/b2c'">1:1문의하러가기</button>
<button onclick="location='${pageContext.request.contextPath}/consumer/b2clist'">문의목록 확인</button>

<%@ include file="/WEB-INF/view/template/footer.jsp"%>