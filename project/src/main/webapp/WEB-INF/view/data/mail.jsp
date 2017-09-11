<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/view/template/mailHeader.jsp" %>

<table>
	<thead>
		<tr>
			<td colspan="6">
        		<form action="" method="post" name="garbage" id="garbage"></form>
        		<form action="" method="post" name="protect" id="protect"></form>
        		<c:if test="${param.box=='garbage'}">
             		<button onclick="moving('garbage'); document.garbage.submit();">영구삭제</button>
            	</c:if>
            	<c:if test="${param.box!='garbage'}">
	            	<button onclick="moving('garbage'); document.garbage.submit();">삭제하기</button>
            	</c:if>
            	<c:if test="${param.box!='protect'}">
             		<button onclick="moving('protect'); document.protect.submit();">보관하기</button>
            	</c:if>
            	<button onclick="location.href='mail/send?box=${param.box}'">쪽지쓰기</button>
        	</td>
    	</tr> 
    
	<tr>
		<th><input id="checkAll" type="checkbox" ></th>
		<th>보낸사람</th>
		<th>제목</th>
		<th>내용</th>
		<th>보낸날짜</th>
		<th>읽기여부</th>
	</tr>
    </thead>
    <tbody>
		<c:forEach var="list" items="${list}">
			<tr>
				<td><input type="checkbox" name="chk" value="${list.no}"></td>
				<td onclick="mailDetail(${list.no});">${list.mail_writer }</td>
				<td onclick="mailDetail(${list.no});">${list.mail_title }</td>
				<td onclick="mailDetail(${list.no});">${list.mail_content }</td>
				<td onclick="mailDetail(${list.no});">${list.mail_reg }</td>
				<td onclick="mailDetail(${list.no});">${list.mail_read }</td>
    		</tr>
    	</c:forEach>
    </tbody>
</table>

<%@ include file="/WEB-INF/view/template/mailFooter.jsp" %>