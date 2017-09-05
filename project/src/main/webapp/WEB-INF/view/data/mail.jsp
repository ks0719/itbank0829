<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	$(document).ready(function(){
		$("#checkAll").click(function(){
			if($("#checkAll").prop("checked")){
				$("input[name=chk]").prop("checked",true);
			}else{
				$("input[name=chk]").prop("checked",false);
			}
		});
		
		$("input[name=chk]").click(function(){
			var chk = $("input[name=chk]");
			var tnf = true;
			
			for(var i=0; i<chk.length; i++){
				if(!$(chk[i]).prop("checked")){
					tnf=false;
				}
			}
			$("#checkAll").prop("checked", tnf);
		});
	});
	
	function mailDetail(no){
		//페이지 이동해야함
		location.href = "${pageContext.request.contextPath}/data/mailDetail?no="+no;
	}
	
	function moving(location) {
		var chk = $("input[name=chk]");
		var tnf;
		
		if(location=='garbage'){
			for(var i=0; i<chk.length; i++){
				if($(chk[i]).prop("checked")){
					$("#garbage").append("<input type='hidden' name='garbage' value='"+$(chk[i]).val()+"'>");
					tnf='del';
				}
			}
		}else if(location=='protect'){
			for(var i=0; i<chk.length; i++){
				if($(chk[i]).prop("checked")){
					$("#protect").append("<input type='hidden' name='protect' value='"+$(chk[i]).val()+"'>");
					tnf='protect';
				}
			}
		}
		
		if(tnf=='del') {
			if(${param.box=='garbage'}) alert("메일이 삭제되었습니다");
			else alert("메일이 휴지통으로 이동되었습니다");
		}else if(tnf=='protect'){
			alert("메일이 보관함으로 이동되었습니다");
		}
		else alert("체크된 메일이 없습니다");
    }﻿ 
</script>

<head>
<title>쪽지함</title>
</head>
<body>
	<table>
		<tr>
			<th rowspan="5">
                <h3><a href="${pageContext.request.contextPath}/data/mail?box=index">받은 쪽지함</a></h3>
                <h3><a href="${pageContext.request.contextPath}/data/mail?box=sent">보낸 쪽지함</a></h3>
                <h3><a href="${pageContext.request.contextPath}/data/mail?box=spam">스팸 쪽지함</a></h3>
                <h3><a href="${pageContext.request.contextPath}/data/mail?box=protect">보관함</a></h3>
                <h3><a href="${pageContext.request.contextPath}/data/mail?box=garbage">휴지통</a></h3>
			</th>
			
            <td style="vertical-align: top">
                <table>
                    <thead>
                   		 <tr>
                        	<td colspan="7">
                        		<form action="" method="post" name="garbage" id="garbage"></form>
                        		<form action="" method="post" name="protect" id="protect"></form>
                            	<button onclick="moving('garbage'); document.garbage.submit();">삭제하기</button>
                            	<c:if test="${param.box!='protect'}">
	                            	<button onclick="moving('protect'); document.protect.submit();">보관하기</button>
                            	</c:if>
                            	<button>쪽지쓰기</button>
                        	</td>
                    	</tr> 
                    
	                    <tr>
	                        <th><input id="checkAll" type="checkbox" ></th>
	                        <th>아이디</th>
	                        <th>분류</th>
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
		                        <td onclick="mailDetail(${list.no});">${list.mail_tag}</td>
		                        <td onclick="mailDetail(${list.no});">${list.mail_title }</td>
		                        <td onclick="mailDetail(${list.no});">${list.mail_content }</td>
		                        <td onclick="mailDetail(${list.no});">${list.mail_reg }</td>
		                        <td onclick="mailDetail(${list.no});">${list.mail_read }</td>
                    		</tr>
                    	</c:forEach>
                    </tbody>
                </table>
            </td>
		</tr>
	</table>
	
	<h1>${param.box}</h1>
</body>
</html>