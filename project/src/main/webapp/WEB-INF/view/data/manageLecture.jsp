<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script>

function change(no){
	$("#changeable").load("${pageContext.request.contextPath}/lecture/class?no="+no+" .borN");
}

function change2(no){
	$("#changeable").load("${pageContext.request.contextPath}/lecture/assess?no="+no+" .borN");
}

function change3(no){
	$("#changeable").load("${pageContext.request.contextPath}/lecture/lectureList?no="+no+" .borN");
}

$(document).ready(function(){
	var box = '${param.box}';
	if(box == '') box='index';
	$("#"+box).addClass("active");
});

</script>


<meta charset="UTF-8">
    <title>수강 관리</title>
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
		<a href="${pageContext.request.contextPath}/data/manageLecture?box=index" class="logo"><b>내 수강 정보</b></a>
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
                  <ul class="nav nav-pills nav-stacked">
                  <!-- fa icon 전부 다 바꿔야함 -->
                    <li id="all"><a href="${pageContext.request.contextPath}/data/manageLecture?box=all"><i class="fa fa-history"></i>전체 수강 내역</a></li>
                    <li id="index"><a href="${pageContext.request.contextPath}/data/manageLecture?box=index"><i class="fa fa-assistive-listening-systems"></i>진행중인 강의<span id="newMail" class="label label-primary pull-right"></span></a></li>
                    <li id="comp"><a href="${pageContext.request.contextPath}/data/manageLecture?box=comp"><i class="fa fa-check-circle-o"></i>수료한 강의</a></li>
                    <li id="eval"><a href="${pageContext.request.contextPath}/data/manageLecture?box=eval"><i class="fa fa-calendar-check-o"></i>미평가 강의<span id="spamMail" class="label label-waring pull-right"></span></a></li>
                    <li id="wish"><a href="${pageContext.request.contextPath}/data/manageLecture?box=wish"><i class="fa fa-thumbs-o-up"></i>찜한 강의</a></li>
                  </ul>
                </div><!-- /.box-body -->
              </div><!-- /. box -->
		</section>
	</aside>
	<!-- 사이드바 끝 -->

	<div class="content-wrapper">
	
		<div class="box box-primary">
			<div class="box-body no-padding">
				<div class="mailbox-controls">
				
					<!-- 페이징 관련 부분 -->
					<div class="pull-right">
						<c:if test="${page>=1 && page<=maxPage}">
				  			${start}-${end}/${maxLength}
						</c:if>
						<div class="btn-group">
							<c:if test="${maxPage>1}">
								<c:choose>
									<c:when test="${empty page||page==1}">
										<button onclick="location.href='${pageContext.request.contextPath}/data/manageLecture?box=${box}&page=${page+1}'" class="btn btn-default btn-sm"><i class="fa fa-chevron-right"></i></button>
									</c:when>
									<c:when test="${not empty page && 1<page && page<maxPage}">
										<button onclick="location.href='${pageContext.request.contextPath}/data/manageLecture?box=${box}&page=${page-1}'" class="btn btn-default btn-sm"><i class="fa fa-chevron-left"></i></button>
										<button onclick="location.href='${pageContext.request.contextPath}/data/manageLecture?box=${box}&page=${page+1}'" class="btn btn-default btn-sm"><i class="fa fa-chevron-right"></i></button>
									</c:when>
									<c:when test="${not empty page && page==maxPage}">
										<button onclick="location.href='${pageContext.request.contextPath}/data/manageLecture?box=${box}&page=${page-1}'" class="btn btn-default btn-sm"><i class="fa fa-chevron-left"></i></button>
									</c:when>
								</c:choose>
							</c:if>
						</div>
					</div>
				</div>
				<!-- 페이징 관련 부분 끝 -->
	
				<div class="mailbox">
					<div class="table-responsive">
						<table id="changeable" class="table table-mailbox">
							<tr>
								<td>분류</td>
		                        <td class="name">강사</td>
		                        <td class="subject">과목명</td>
		                        <td>강의시간</td>
		                        <td>수강형태</td>
		                        <td>수강상태</td>
		                        <td class="time">수업날짜</td>
		                        <td>결제금액</td>
		                        <td>상세보기</td>
		                        <c:choose>
		                        	<c:when test="${param.box eq 'eval'}">
		                        		<td>평가</td>
		                        	</c:when>
		                        	<c:when test="${param.box eq 'index'}">
		                        		<td>강의</td>
		                        	</c:when>
		                        </c:choose>
							</tr>
							<!-- 리스트 반복문으로 찍을 공간 -->
							<c:forEach var="list" items="${list}">
								<tr>
									<td>${list.tag}</td>
			                        <td class="name">${list.teacher}</td>
			                        <td class="subject">${list.title}</td>
			                        <td>${list.time}</td>
			                        <td>${list.type}</td>
			                        <td>${list.state}</td>
			                        <td class="time">${list.period}</td>
			                        <td>${list.price}</td>
			                        <td><a href="" class="detail" onclick="change(${list.no}); return false;">상세보기</a></td>
			                        <c:choose>
			                        	<c:when test="${param.box eq 'eval'}">
			                        		<td><a href="" class="detail" onclick="change2(${list.no}); return false;">평가하기</a></td>
			                        	</c:when>
			                        	<c:when test="${param.box eq 'index'}">
			                        		<td><a href="" class="detail" onclick="change3(${list.no}); return false;">강의듣기</a></td>
			                        	</c:when>
			                        </c:choose>
								</tr>
					    	</c:forEach>
						</table>
					</div>
				</div>
					
			</div>
		</div>
	</div>
	<footer class="main-footer"></footer>
</div>	
    <!-- Bootstrap 3.3.2 JS -->
    <script src="${pageContext.request.contextPath}/css/bootstrap/js/bootstrap.js" type="text/javascript"></script>    
    <!-- Morris.js charts -->
    <script src="http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
    <!-- AdminLTE App -->
    <script src="${pageContext.request.contextPath}/css/dist/js/app.js" type="text/javascript"></script>
	</body>
</html>
