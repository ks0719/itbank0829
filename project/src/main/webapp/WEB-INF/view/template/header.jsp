<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "java.net.URLDecoder" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="http://code.jquery.com/jquery-3.2.1.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
function wrapWindowByMask(){
    // 화면의 높이와 너비를 변수로 만듭니다.
    var maskHeight = $(document).height();
    var maskWidth = $(window).width();

    // 마스크의 높이와 너비를 화면의 높이와 너비 변수로 설정합니다.
    $('.mask').css({'width':maskWidth,'height':maskHeight});

    // fade 애니메이션 : 1초 동안 검게 됐다가 80%의 불투명으로 변합니다.
    $('.mask').fadeIn(1000);
    $('.mask').fadeTo("slow",0.8);

    // 레이어 팝업을 가운데로 띄우기 위해 화면의 높이와 너비의 가운데 값과 스크롤 값을 더하여 변수로 만듭니다.
    var left = ( $(window).scrollLeft() + ( $(window).width() - $('.window').width()) / 2 );
    var top = ( $(window).scrollTop() + ( $(window).height() - $('.window').height()) / 2 );

    // css 스타일을 변경합니다.
    $('.window').css({'left':left,'top':top, 'position':'absolute'});

    // 레이어 팝업을 띄웁니다.
    $('.window').show();
}

$(document).ready(function(){
    // showMask를 클릭시 작동하며 검은 마스크 배경과 레이어 팝업을 띄웁니다.
    $('.showMask').click(function(e){
        // preventDefault는 href의 링크 기본 행동을 막는 기능입니다.
        e.preventDefault();
        wrapWindowByMask();
    });

    // 닫기(close)를 눌렀을 때 작동합니다.
    $('.window .close').click(function (e) {
        e.preventDefault();
        $('.mask, .window').hide();
    });

    // 뒤 검은 마스크를 클릭시에도 모두 제거하도록 처리합니다.
    $('.mask').click(function () {
        $(this).hide();
        $('.window').hide();
    });
});


	$(document).ready(function() {
		// 검색시 select option 유지
		$("select option").each(function(){
	    	if($(this).val()=="${type}"){
				$(this).attr("selected","selected");
	    	} else if ($(this).val()=="${profile.sort}") {
				$(this).attr("selected","selected");
	    	} else if ($(this).val()=="${mylecture.tag}") {
				$(this).attr("selected","selected");
	    	}
		});
		
		$(document).on("click", ".clickToinfo", function() {
			var no = $(this).data('no');
			var page = $(this).data('page');
			var type = $(this).data('type');
			var key = $(this).data('key');

			if (type != "" && key != "") {
				location.href = "class?no=" + no + "&page=" + page + "&type=" + type + "&key=" + key;
			} else {
				location.href = "class?no=" + no + "&page=" + page;
			}
		});
		
		$(document).on("click", ".toLecturerInfo", function() {
			var name = $(this).data('name');
			var page = $(this).data('page');
			var type = $(this).data('type');
			var key = $(this).data('key');

			if (type != "" && key != "") {
				location.href =  "lecturerInfo?name=" + name + "&page=" + page + "&type=" + type + "&key=" + key;
			} else {
				location.href = "lecturerInfo?name=" + name + "&page=" + page;
			}
		});
		
		$(".lectureWish").on("click", function() {
			var no = $(this).attr('value');
			
			$.ajax({
        		url: "wish",
        		data: {"no" : no},
        		success: function(res) {
        	    	window.alert(res);
        		}
        	});
		});
		
		$("#board-select option").each(function(){
			if($(this).val()=="${unit.head}"){
				$(this).attr("selected","selected");
			}
		});
		
		$(document).on("click", ".board-delete", function() {
			var no = $(this).data('no');
			var context = $(this).data('context');
			var result = confirm("정말 삭제하시겠습니까?");
			
			if (result) {
				location.href="delete?no=" + no + "&context=" + context;
			}
		});
		
		$(document).on("submit", ".board-comment", function() {
			var contextNo = $(this).attr('value');
			event.preventDefault();
        	
        	$.ajax({
        		url: "comment",
        		data: $(this).serialize(),
        		async : false,
        		success: function(res) {
        			$("#comments"+contextNo).append(res);
        			$(".user-input").val('');
        		}
        	});
		});
		$(document).on("click", ".comment-best", function() {
			var commentNo = $(this).attr('value');

			$.ajax({
				url: "commentBest",
				data: {"commentNo": commentNo},
        		async : false,
				success: function(res) {
					$("#best"+commentNo).text(res);
				}
			});
		});
		$(document).on("click", ".comment-delete", function() {
			var commentNo = $(this).attr('value');
			var result = confirm("정말 삭제하시겠습니까?");

			$.ajax({
				url: "commentDelete",
				data: {"commentNo": commentNo, "result" : result},
        		async : false,
				success: function(res) {
					if (result) $("#comment"+commentNo).remove();
				}
			});
		});
		
		$(document).on("click", ".lecturer-array", function() {
			var page = $(this).data('page');
			var standard = $(this).attr('value');
			var type = $(this).data('type');
			var key = $(this).data('key');
			
			if (type == "" || key == "") {
				location.href="lecturer?page=" + page + "&standard=" + standard;
			} else {
				location.href="lecturer?page=" + page + "&standard=" + standard + "&type=" + type + "&key=" + key;
			}
		});
		
		$("#lecturer-apply").on("click", function() {
			var nick = $(this).attr('value');
			console.log(nick);
			
			$.ajax({
				url: "${pageContext.request.contextPath}/teacher/applycheck",
				data: {"nick": nick},
				success: function(res) {
					console.log(res);
					if (res == "true") {
						alert("이미 신청하셨거나 강사입니다.");
					} else {
						location.href = "${pageContext.request.contextPath}/teacher/apply";
					}
				}
			});
		});
		
		$(document).on("click", ".toMyLecture", function() {
			var no = $(this).data('no');
			var page = $(this).data('page');
			var type = $(this).data('type');
			var key = $(this).data('key');

			if (type != "" && key != "") {
				location.href = "myLecture?no=" + no + "&page=" + page + "&type=" + type + "&key=" + key;
			} else {
				location.href = "myLecture?no=" + no + "&page=" + page;
			}
		});
	});
	
	// 이미지 업로드시 이미지 미리보기
	function previewImage(targetObj, previewId) {
	    var preview = document.getElementById(previewId);   
	    var ua = window.navigator.userAgent;

	    if(ua.indexOf("MSIE") > -1){ // ie일때
	        targetObj.select();

	        try {
	            var prevImg = document.getElementById("prev_" + previewId); 
	            // 미리보기태그삭제
	            if (prevImg) {
	                preview.removeChild(prevImg);
	            }
	         
	         var src = document.selection.createRange().text;  
	            var img = document.getElementById(previewId);  

	            img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + src + "', sizingMethod='scale')"; 
	            // 이미지 로딩, sizingMethod는 div에 맞춰서 사이즈를 자동조절 하는 역할
	        } catch (e) {
	        	var info = document.createElement("<p>");
	            info.innerHTML = "not supported preview";
	            preview.insertBefore(info, null);
	        }
	    } else { // ie가 아닐때
	        var files = targetObj.files;
	        for (var i = 0; i < files.length; i++){
	            var file = files[i];
	            var imageType = /image.*/;
	            if (!file.type.match(imageType)) continue;
	            
	            var prevImg = document.getElementById("prev_" + previewId); 
	           	// 미리보기태그삭제
	            if (prevImg) {
	                preview.removeChild(prevImg);
	            }
	            
	            var img = document.createElement("img");
	            // 크롬은 div에 이미지가 뿌려지지 않는다. 그래서 자식Element(IMG)를 만든다.
	            img.id = "prev_" + previewId;
	            img.classList.add("obj");
	            img.file = file;
	            img.style.width = '150px'; // div 사이즈와 맞게 IMG 태그 속성 변경
	            img.style.height = '150px';
	            preview.appendChild(img);
	            
	            if(window.FileReader){ // FireFox, Chrome, Opera 
	                var reader = new FileReader();
	                reader.onloadend = (function(aImg) {
	                    return function(e) {
	                        aImg.src = e.target.result;
	                    };
	                })(img);
	                reader.readAsDataURL(file);
	            } else { // safari
	            	var info = document.createElement("<p>");
	                info.innerHTML = "not supported preview";
	                preview.insertBefore(info, null);
	            }
	            
	        }
	    }
	}
	
	  $(function(){
	      //전역변수
	      var obj = [];              
	      //스마트에디터 프레임생성
	      nhn.husky.EZCreator.createInIFrame({
	          oAppRef: obj,
	          elPlaceHolder: "editor",
	          sSkinURI: "${pageContext.request.contextPath}/editor/SmartEditor2Skin.html",
	          htParams : {
	              // 툴바 사용 여부
	              bUseToolbar : true,            
	              // 입력창 크기 조절바 사용 여부
	              bUseVerticalResizer : true,    
	              // 모드 탭(Editor | HTML | TEXT) 사용 여부
	              bUseModeChanger : true,
	          }
	      });
	      //전송버튼
	      $("#submit").click(function(){
	          //id가 smarteditor인 textarea에 에디터에서 대입
	          obj.getById["editor"].exec("UPDATE_CONTENTS_FIELD", []);
	          //폼 submit
	          $("#insertBoardFrm").submit();
	      });
	  });
	  

	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  //회원가입 페이지 에서 사용하는 스크립트
	  function daumAddressSearch() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.querySelector("input[name=post]").value = data.zonecode; //5자리 새우편번호 사용
                document.querySelector("input[name=addr1]").value = fullAddr;

                // 커서를 상세주소 필드로 이동한다.
                document.querySelector("input[name=addr2]").focus();
            }
        }).open();
    }
	  
	//아이디 중복확인
	function idCheck() {
		if($("#idcheck").val()=="중복확인"){
			var idregex=/^[a-zA-Z0-9]{8,20}$/;
		 	var idtarget= document.querySelector("#id");
			
			if($("#id").val()==""){
				alert("아이디를 입력하세요");
			}else if(!idregex.test(idtarget.value)){
				alert("ID는 영문,숫자 조합 8~20자");
			}
			else{
				$.ajax({
					url:"idcheck",
					type:"post",
					data:{id:$("#id").val()},
					dataType:"text",
					success:function(){
						alert("사용 가능한 아이디 입니다.");
						$("#id").attr("readonly","readonly");
						$("#idcheck").val("취소");
					},
					error:function(){
						alert("중복된 아이디가 있습니다.");
					}
				});
			}
		}else{
			$("#idcheck").val("중복확인");
			$("#id").removeAttr("readonly");
		}
	}

  	
  	//닉네임 체크
	function nickCheck(){
		if($("#nickcheck").val()=="중복확인"){
  			var nickregex=/^[가-힣]{2,6}$/;
  			var nicktarget = document.querySelector("#nick");
  			
  			if($("#nick").val()==""){
				alert("닉네임을 입력하세요");
			}else if(!nickregex.test(nicktarget.value)){
		 		alert("닉네임은 완성된 한글 2~6글자");
		 	}else{
				 $.ajax({
						url:"nickcheck",
						type:"post",
						data:{nick:$("#nick").val()},
						dataType:"text",
						success:function(){
							alert("사용 가능한 닉네임 입니다.");
							$("#nick").attr("readonly","readonly");
							$("#nickcheck").val("취소");
						},
						error:function(){
							alert("이미 등록된 닉네임 입니다.");
						}
					});
  			}
		}else{
			$("#nickcheck").val("중복확인");
			$("#nick").removeAttr("readonly");
		}
	}
	
  	//핸드폰 번호 체크
  	function phoneCheck(){
  		if($("#pcheck").val()=="중복확인"){
  			
			var phoneregex=/^[010]{3}[0-9]{3,4}[0-9]{4}$/; 
			var phonetarget=document.querySelector("#phone");
			
			if($("#phone").val()==""){
				alert("핸드폰 번호를 입력하세요");
			}else if(!phoneregex.test(phonetarget.value)){
				alert("올바른 핸드폰 번호를 입력해주세요.");
			}else{
				$.ajax({
					url:"pcheck",
					type:"post",
					data:{phone:$("#phone").val()},
					dataType:"text",
					success:function(){
						alert("사용 가능한 번호 입니다.");
						$("#phone").attr("readonly","readonly");
						$("#pcheck").val("취소");
					},
					error:function(){
						alert("이미 등록된 번호 입니다.");
					}
				});
  			}
		}else{
			$("#pcheck").val("중복확인");
			$("#phone").removeAttr("readonly");
		}
  	}
  	
  	function submitOK() {
  		var pwregex=/^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*=+]).{8,20}$/;
	 	var pwtarget=document.querySelector("#pw");
	 	
	 	var target = document.querySelector("#pw2");
	 	
	 	console.log(pwtarget.value);
	 	console.log(target.value);
	 	
	 	if($("#id").attr("readonly")!='readonly'){
	 		alert("아이디 중복 확인을 해주세요");
	 	}else if(!pwregex.test(pwtarget.value)){
	 		alert("비밀번호는 영문,숫자,특수문자 8~20자");
	 	}else if(pwtarget.value!=target.value){
	 		alert("비밀번호가 틀렸습니다");
	 	}else if($("#nick").attr("readonly")!='readonly'){
	 		alert("닉네임 중복 확인을 해주세요");
	 	}else if($("#phone").attr("readonly")!='readonly'){
	 		alert("전화번호 중복 확인을 해주세요");
	 	}else if(document.querySelector("input[name=post]").value==''){
	 		alert("주소를 입력해주세요");
	 	}else{
	 		alert("회원가입이 완료되었습니다.");
	 		return true;
	 	}
	 		return false;
	}
  	
  	//회원탈퇴 비밀번호 체크
  	function pwCheck(){
  		
  		var result=false;
  		
  		if($("#pwcheck").val()==""){
  			alert("비밀번호를 입력해주세요!");
  			result=false;
  		}else{
  			$.ajax({
				url:"deletemember",
				async: false,
				type:"post",
				data:{pw:$("#pwcheck").val()},
				dataType:"text",
				success:function(){
					alert("회원탈퇴가 완료되었습니다.");
					result=true;
				},
				error:function(){
					alert("비밀번호가 일치하지 않습니다.");
					result=false;
				}
			});
  		}
  		return result;
  	}
	  
  	
  	function logincheck(){
  		var result=false;
  		var id=document.querySelector("#loginid");
  		var pw=document.querySelector("#loginpw");
  		if($("#loginid").val()==""){
  			alert("아이디를 입력하세요!");
  			result=false;
  		}else if($("#loginpw").val()==""){
  			alert("비밀번호를 입력하세요!");
  			result=false;
   		}
  		else{
  			$.ajax({
  				url:"/project/member/login",
  				type:"post",
  				async: false,
				data:({id:$("#loginid").val(), pw:$("#loginpw").val(),page:"${pageContext.request.requestURL}",param:"${param}"}),
				dataType:"text",
				success:function(){
					alert("로그인 성공했습니다.");
					result=true;
				},
				error:function(){
					alert("아이디 또는 비밀번호가 맞지 않습니다.");
					result=false;
				}
			});
  		}
  			return result;
  	}
  	
  	function changepw() {
  		var pw=null;
  		var pwregex=/^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*=+]).{8,20}$/;
  	  if(chpw.newpw.value != chpw.repw.value ) {
  		  if(!phoneregex.test(chpw.newpw.value)){
  	    alert("비밀번호가 조건에 맞지 않습니다.");
  	    return false;
  	   chpw.newpw.focus();
  		  }
  	    return false;
  	  }
  	  else return true;
  	}
  	
  	
  	
//움직이는 레이어 팝업
$(function() {
		$( "#draggable" ).draggable();
	});

</script>

<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
	<meta charset="utf-8">
	<title>Welcome</title>
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
<!--     iCheck -->
<%--     <link href="${pageContext.request.contextPath}/css/plugins/iCheck/flat/blue.css" rel="stylesheet" type="text/css" /> --%>
<!--     Morris chart -->
<%--     <link href="${pageContext.request.contextPath}/css/plugins/morris/morris.css" rel="stylesheet" type="text/css" /> --%>
<!--     jvectormap -->
<%--     <link href="${pageContext.request.contextPath}/css/plugins/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" /> --%>
<!--     Date Picker -->
<%--     <link href="${pageContext.request.contextPath}/css/plugins/datepicker/datepicker3.css" rel="stylesheet" type="text/css" /> --%>
<!--     Daterange picker -->
<%--     <link href="${pageContext.request.contextPath}/css/plugins/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" /> --%>
<!--     bootstrap wysihtml5 - text editor -->
<%--     <link href="${pageContext.request.contextPath}/css/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" /> --%>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
</head> 
<body class="skin-blue">

<div id="draggable" class="ui-widget-content" style="top:80%;
    left:80%;
    height: 130px;
    width: 300px;
    border: 0px;
    cursor: pointer;
    position: absolute;
    z-index: 2147483647;
    overflow: visible;
    
    background-color: transparent;
    visibility: visible;
    border: 1px solid;">
	  <p>마우스로 움직이는 팝업레이어</p>
	</div>
<div class="setDiv">
    <div class="mask"></div>
    <div class="window">
    <h5>더 많은 정보를 제공받고 싶으시다면 로그인해주세요</h5>
    <div id="null"></div>
    <form action="${pageContext.request.contextPath }/member/login" method="post">
    	아이디<input type="text" name="id"  id="loginid" required><br>
    	비밀번호<input type="password" name="pw" id="loginpw" required><br>
    	<input type="hidden" value="${pageContext.request.requestURL}" name="page">
    	<input type="hidden" value="${param}" name="param">
        <input type="submit" id="login_btn" value="로그인하기" onclick="return logincheck();"/><br>
        <input type="button" href="#" value="회원가입하기">
    </form>
    </div>
</div>

<div class="wrapper">
	<!-- 헤더 시작 -->
	<header class="main-header">
		<!-- 홈으로 버튼(로고로 사용해도됨) -->
		<a href="${pageContext.request.contextPath}/" class="logo"><b>홈</b>으로</a>
		<!-- 로고 옆 메뉴바 -->
		<nav class="navbar navbar-static-top" role="navigation">
			<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
	            <span class="sr-only">Toggle navigation</span>
	          </a>
			광고 or 공지 넣을 자리(회원 정보 메뉴로 들어가면 여기다 회원정보 화면에 있는 a태그들 넣을 것)
		</nav>
	</header>
	<!-- 헤더 끝 -->
	
	<!-- 사이드바 시작 -->
	<aside class="main-sidebar">
		<section class="sidebar">
			<!-- 유저 정보 시작 -->
			<div class="user-panel">
				<c:set var="mynick" value="${cookie.mynick.value}"/>
				<c:choose>
					<c:when test="${empty mynick}">
					<div class="pull-right info">
						<a href="#" class="showMask"><i class="fa fa-sign-in"></i><span>로그인</span></a>
						<a href="${pageContext.request.contextPath}/member/sign"><i class="fa fa-user-plus"></i><span>회원가입</span></a>
					</div>
					</c:when>
					<c:otherwise>
						<div class="pull-left image">
							<!-- 테스트용 이미지 -->
							<img src="${pageContext.request.contextPath}/img/users--blue-flag-png-image-100720.png" class="img-circle" alt="User Image"/>
						</div>
						<div class="pull-left info">
							<a href="${pageContext.request.contextPath}/data/maininfo">
								<i class="fa fa-user-circle-o"></i>
								<span><%=URLDecoder.decode((String)pageContext.getAttribute("mynick"), "UTF-8")%></span>
							</a>
							<a href="" class="dropdown-toggle" data-toggle="dropdown" onclick="window.open('${pageContext.request.contextPath}/data/mail?box=index', '쪽지함', 'width=800, height=500'); return false;">
								<i class="fa fa-envelope"></i>
								<!-- 여기는 함수 만들어서 숫자 계산 해줘야함 -->
								<span class="label label-success">4</span>
							</a>
							
							<br>
							<a href="" onclick="window.open('${pageContext.request.contextPath}/data/manageLecture?box=index', '수강관리', 'width=1000, height=500'); return false;"><i class="fa fa-clipboard"></i><span>내 수강정보</span></a>
							<br>
							<a href="${pageContext.request.contextPath}/member/logout"><i class="fa fa-sign-out"></i><span>로그아웃</span></a>
						</div>
					</c:otherwise>
				</c:choose>
			
			</div>
			
			<!-- 유저 정보 끝 -->
			
			<!--
				클릭이 된 상태는 treeview 뒤에  active가 붙어야 한다
				클릭이 된 상태는 treeview-menu 뒤에 menu-open이 붙어야 한다
				따라서 나중에 그 페이지에 들어갔을 때 class가 변경되도록 설정 해주는 함수를 만들어 줘야 한다
			-->
			
			<!-- 사이드바 메뉴 시작 -->
			<ul class="sidebar-menu">
				<c:choose>
					<c:when test="${pageContext.request.requestURI.replace('/project/WEB-INF/view/','').substring(0,5) eq 'board'}">
						<li class="treeview active">
							<a href="#">
								<i class="fa fa-edit"></i>
								<span>커뮤니티</span> <i class="fa fa-angle-left pull-right"></i>
							</a>
							<ul class="treeview-menu menu-open">
					</c:when>
					<c:otherwise>
						<li class="treeview">
							<a href="#">
								<i class="fa fa-edit"></i>
								<span>커뮤니티</span> <i class="fa fa-angle-left pull-right"></i>
							</a>
							<ul class="treeview-menu">
					</c:otherwise>
				</c:choose>
						<li>
							<a href="${pageContext.request.contextPath}/board/free" class="active">
								<i class="fa fa-circle-o"></i>자유게시판
							</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/board/info">
								<i class="fa fa-circle-o"></i>정보게시판
							</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/board/qna">
								<i class="fa fa-circle-o"></i>Q&A게시판
							</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/board/require">
								<i class="fa fa-circle-o"></i>요청게시판
							</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/board/store">
								<i class="fa fa-circle-o"></i>판매게시판
							</a>
						</li>
						<li></li>
					</ul>
				</li>
				
				<li>
					<a href="${pageContext.request.contextPath}/lecture/study?page=1">
						<i class="fa fa-calendar-o"></i> <span>수업정보</span>
					</a>
				</li>
				
				<li>
					<a href="${pageContext.request.contextPath}/teacher/lecturer?page=1">
						<i class="fa fa-id-card"></i> <span>강사정보</span>
					</a>
				</li>
				
				<li>
					<a href="${pageContext.request.contextPath}/consumer/basic">
						<i class="fa fa-info-circle"></i> <span>고객센터</span>
					</a>
				</li>
			</ul>
			<!-- 사이드바 메뉴 끝 -->
			
			
		</section>
	</aside>
	<!-- 사이드바 끝 -->
	
	
	<div class="content-wrapper">
	
	
	
	
	





