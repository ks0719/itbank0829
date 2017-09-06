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
	
	function isExist(nick){
		console.log(nick);
	}
	
	function mailDetail(no){
		location.href = "${pageContext.request.contextPath}/data/mailDetail?box=${param.box}&no="+no;
	}
	
	function del(){
		if(${box=='garbage'}) alert("메일이 삭제되었습니다");
		else alert("메일이 휴지통으로 이동되었습니다");
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
