<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "java.net.URLDecoder" %>
<html>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>

	$(document).ready(function(){
		$("#checkAll").click(function(){
			if($("#checkAll").hasClass("fa-square-o")){
				$("input[name=chk]").prop("checked",true);
				$("#checkAll").removeClass("fa-square-o");
				$("#checkAll").addClass("fa-check-square-o");
			}else{
				$("input[name=chk]").prop("checked",false);
				$("#checkAll").addClass("fa-square-o");
				$("#checkAll").removeClass("fa-check-square-o");
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
			
			if(tnf){
				$("#checkAll").removeClass("fa-square-o");
				$("#checkAll").addClass("fa-check-square-o");
				
			}else{
				$("#checkAll").addClass("fa-square-o");
				$("#checkAll").removeClass("fa-check-square-o");
			}
			
			
		});
		
		//새로운 메일 갯수
		var mynick = "${cookie.mynick.value}";
		
		var error1 = $.ajax({
			url:"/project/data/mail/newMail",
			async: false,
			type:"post",
			data:{nick:mynick, isSpam:"false"},
			dataType:"text"
		}).responseText;
		
		var newMail = error1.substring(error1.indexOf("/project/WEB-INF/view/")+"/project/WEB-INF/view/".length, error1.indexOf(".jsp"))
		
		if(newMail!=0){
			$('#newMail').text(newMail);
		}
		
		var error2 = $.ajax({
			url:"/project/data/mail/newMail",
			async: false,
			type:"post",
			data:{nick:mynick, isSpam:"true"},
			dataType:"text"
		}).responseText;
		
		var spamMail = error2.substring(error2.indexOf("/project/WEB-INF/view/")+"/project/WEB-INF/view/".length, error2.indexOf(".jsp"))
		
		if(spamMail!=0){
			$('#spamMail').text(spamMail);
		}
		
		var box = '${param.box}';
		if(box == '') box='index';
		$("#"+box).addClass("active");
	});
	
	function isExist(nick){
		if(nick=='') {
			alert("닉네임을 입력해주세요");
			return false;
		}
		
		if($("#check").val()=="확인"){
			$.ajax({
				url:"nickcheck",
				type:"post",
				data:{nick:nick},
				dataType:"text",
				success:function(){
					$("#check").val("취소");
					$("#mail_receiver").attr("readonly", "readonly");
					$("#send").removeAttr("disabled");
				},
				error:function(data){
					if(data.responseText.indexOf("error : my nickname")>=0){
						alert("나에겐 쪽지를 보낼 수 없습니다");
					}else{
						alert("존재하지 않는 회원입니다");
					}
				}
			});
		}else{
			$("#check").val("확인");
			$("#mail_receiver").removeAttr("readonly");
			$("#send").attr("disabled", "disabled");
		}
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
					$("#garbageSubmit").append("<input type='hidden' name='garbage' value='"+$(chk[i]).val()+"'>");
					tnf='del';
				}
			}
		}else if(location=='protect'){
			for(var i=0; i<chk.length; i++){
				if($(chk[i]).prop("checked")){
					$("#protectSubmit").append("<input type='hidden' name='protect' value='"+$(chk[i]).val()+"'>");
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
	<meta charset="UTF-8">
    <title>쪽지함</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<!-- Bootstrap 3.3.2 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />    
    <!-- FontAwesome 4.7.0 -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.css" rel="stylesheet" type="text/css" />
    <!-- Ionicons 2.0.0 -->
    <link href="http://code.ionicframework.com/ionicons/2.0.0/css/ionicons.css" rel="stylesheet" type="text/css" />    
    <!-- Theme style -->
    <link href="${pageContext.request.contextPath}/css/dist/css/AdminLTE.css" rel="stylesheet" type="text/css" />
    <!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
    <link href="${pageContext.request.contextPath}/css/dist/css/skins/_all-skins.css" rel="stylesheet" type="text/css" />

</head>





<body class="skin-mail">

<c:set var="incodedNick" value="${cookie.mynick.value}"/>
<c:if test="${!empty nick }">
	<%request.setAttribute("mynick", URLDecoder.decode((String)pageContext.getAttribute("incodedNick"), "UTF-8"));%>
</c:if>


<div class="wrapper">
	<!-- 헤더 시작 -->
	<header class="main-header">
		<a href="${pageContext.request.contextPath}/data/mail?box=index" class="logo"><b>쪽지함</b></a>
		<!-- 메뉴바 -->
		<nav class="navbar navbar-static-top" role="navigation">
			<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
	            <span class="sr-only">Toggle navigation</span>
	          </a>
		</nav>
	</header>
	<!-- 헤더 끝 -->
	
	<!-- 사이드바 시작 -->
	<aside class="main-sidebar">
		<section class="sidebar">
			<div class="box box-solid">
                <div class="box-body no-padding">
					<a href="${pageContext.request.contextPath}/data/mail/send?box=${param.box}" class="btn btn-primary btn-block margin-updown"><i class="fa fa-pencil"></i> 쪽지쓰기</a>
                  <ul class="nav nav-pills nav-stacked">
                    <li id="index"><a href="${pageContext.request.contextPath}/data/mail?box=index"><i class="fa fa-inbox"></i> 받은 쪽지함 <span id="newMail" class="label label-primary pull-right"></span></a></li>
                    <li id="sent"><a href="${pageContext.request.contextPath}/data/mail?box=sent"><i class="fa fa-envelope-o"></i> 보낸 쪽지함</a></li>
                    <li id="protect"><a href="${pageContext.request.contextPath}/data/mail?box=protect"><i class="fa fa-briefcase"></i> 보관함</a></li>
                    <li id="spam"><a href="${pageContext.request.contextPath}/data/mail?box=spam"><i class="fa fa-filter"></i> 스팸 쪽지함<span id="spamMail" class="label label-waring pull-right"></span></a></li>
                    <li id="garbage"><a href="${pageContext.request.contextPath}/data/mail?box=garbage"><i class="fa fa-trash-o"></i> 휴지통</a></li>
                  </ul>
                </div><!-- /.box-body -->
              </div><!-- /. box -->
			
			
		</section>
	</aside>
	<!-- 사이드바 끝 -->
	
	
	<div class="content-wrapper">