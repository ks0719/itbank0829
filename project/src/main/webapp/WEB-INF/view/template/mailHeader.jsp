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
				error:function(){
					alert("존재하지 않는 회원입니다");
				}
			});
		}else{
			$("#check").val("확인");
			$("#mail_receiver").removeAttr("readonly");
			$("#send").attr("disabled", "disabled");
		}
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

<div class="wrapper">
	<!-- 헤더 시작 -->
	<header class="main-header">
		<!-- 홈으로 버튼(로고로 사용해도됨) -->
		<a href="${pageContext.request.contextPath}/data/mail?box=index" class="logo"><b>쪽지함</b></a>
		<!-- 로고 옆 메뉴바 -->
		<nav class="navbar navbar-static-top" role="navigation">
			<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
	            <span class="sr-only">Toggle navigation</span>
	          </a>
			뭐 넣을지 생각 (없으면 그냥 버튼만 남기기)
		</nav>
	</header>
	<!-- 헤더 끝 -->
	
	<!-- 사이드바 시작 -->
	<aside class="main-sidebar">
		<section class="sidebar">
			
			<div class="box box-solid">
                <div class="box-body no-padding">
                  <ul class="nav nav-pills nav-stacked">
                  	<!--
                  		아이콘 수정 해야함
                  		여기도 홈처럼 그 페이지에 있으면 active되게 해줘야함
                  		새로운 메일이 있으면 계산해서 숫자로 표시 해줘야함(메인 헤더에 있는 코드 가져오면 됨)
                  	-->
                    <li class="active"><a href="${pageContext.request.contextPath}/data/mail?box=index"><i class="fa fa-inbox"></i> 받은 쪽지함 <span class="label label-primary pull-right">12</span></a></li>
                    <li><a href="${pageContext.request.contextPath}/data/mail?box=sent"><i class="fa fa-envelope-o"></i> 보낸 쪽지함</a></li>
                    <li><a href="${pageContext.request.contextPath}/data/mail?box=spam"><i class="fa fa-file-text-o"></i> 스팸 쪽지함</a></li>
                    <li><a href="${pageContext.request.contextPath}/data/mail?box=protect"><i class="fa fa-filter"></i> 보관함 <span class="label label-waring pull-right">65</span></a></li>
                    <li><a href="${pageContext.request.contextPath}/data/mail?box=garbage"><i class="fa fa-trash-o"></i> 휴지통</a></li>
                  </ul>
                </div><!-- /.box-body -->
              </div><!-- /. box -->
			
			
		</section>
	</aside>
	<!-- 사이드바 끝 -->
	
	
	<div class="content-wrapper">