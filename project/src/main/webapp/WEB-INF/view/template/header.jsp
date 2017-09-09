<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="http://code.jquery.com/jquery-3.2.1.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
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
		$(".clickToinfo").on("click", function() {
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
		$("#board-select option").each(function(){
			if($(this).val()=="${unit.head}"){
				$(this).attr("selected","selected");
			}
		});
		$(".board-comment").on("submit", function() {
			var commentNo = $(this).attr('value');
			event.preventDefault();
        	
        	$.ajax({
        		url: "comment",
        		data: $(this).serialize(),
        		async : false,
        		success: function(res, context) {
        			console.log("a comment : " + commentNo);
        			$("#comment"+commentNo).append(res);
        		}
        	});
		});
		$(document).on("click", ".comment-best", function() {
			var commentNo = $(this).attr('value');
			console.log("comment" + commentNo);
			$.ajax({
				url: "commentBest",
				data: {"commentNo": commentNo},
        		async : false,
				success: function(res) {
					console.log("commentNo: " + commentNo);
					$("#best"+commentNo).text(res);
				}
			});
		});
	});
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
			 	var idtarget= document.querySelector("input[name=id]");
				
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
				$("#ids").removeAttr("disabled");
				$("#id").removeAttr("readonly");
			}
			
  
	//아이디 체크
	function idCheck(){
	    var regex = /^[\w]{8,20}$/;
	    var target = document.querySelector("input[name=ids]");
	    if(regex.test(target.value)){
	        target.style.border = "1px solid blue";
	    }else{
	        target.style.border = "1px solid red";
	    }
	}
		}
	

  	//비밀번호 체크
	function pwCheck(){
	    var regex = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*=+]).{8,20}$/;
	    var target = document.querySelector("input[name=pw]");
	    if(regex.test(target.value)){
			target.style.border = "1px solid blue";
	    }else{
			target.style.border = "1px solid red";
	    }
	}
  	
  	//비밀번호 재확인
	function pw2Check(){
	    var pw = document.querySelector("input[name=pw]")
	    var target = document.querySelector("#pw2");
	    if(pw.value === target.value){
			target.style.border = "1px solid blue";
	    }else{
			target.style.border = "1px solid red";
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
			var phonetarget=document.querySelector("input[name=phone]");
			
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
	 	var pwtarget=document.querySelector("input[name=pw]");
	 	
	 	var target = document.querySelector("input[name=pw2]");
	 	
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
	 	}else return true;
	 	
	 	return false;
	}
	  
	  
</script>

<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
	<meta charset="utf-8">
	<title>Welcome</title>
</head> 
<body>
<div class="setDiv">
 
    <div class="mask"></div>
    <div class="window">
    <h5>더 많은 정보를 제공받고 싶으시다면 로그인해주세요</h5>
    <div id="null"></div>
    <form action="${pageContext.request.contextPath }/member/login" method="post">
    	아이디<input type="text" name="id" required><br>
    	비밀번호<input type="password" name="pw" required><br>
    	<input type="hidden" value="${pageContext.request.requestURL}" name="page">
    	<input type="hidden" value="${param}" name="param">
        <input type="submit" id="login_btn" value="로그인하기"/><br>
        <input type="button" href="#" value="회원가입하기">
       </form>
    </div>
</div>

	<table>
		<tr>
			<th rowspan="10">
                <div>
                    <h3>회원정보 넣을 곳</h3>
                    <h3><a href="${pageContext.request.contextPath}/">홈으로</a></h3>
                    <c:set value="${cookie.mynick.value}" var="mynick"/>
   					<c:if test="${empty mynick }">
	                    <h3><a href="#" class="showMask">로그인</a></h3>
	                    <h3><a href="${pageContext.request.contextPath}/member/sign">회원가입</a></h3>
                    </c:if>
                    <c:if test="${not empty mynick }">
	              		<h3><a href="${pageContext.request.contextPath}/member/logout">로그아웃</a></h3>
	                    <h3><a href="${pageContext.request.contextPath}/data/maininfo">내 정보 보기(maininfo.jsp)</a></h3>
	                    <h3><a href="" onclick="window.open('${pageContext.request.contextPath}/data/mail?box=index', '쪽지함', 'width=800, height=500'); return false;">쪽지함</a></h3>
						<h3><a href="" onclick="window.open('${pageContext.request.contextPath}/data/manageLecture?box=index', '수강관리', 'width=1000, height=500'); return false;">내 수강정보</a></h3>
						사진
						닉네임
						내 등급
                    </c:if>
                </div>
                <div>
                    <h3><a href="${pageContext.request.contextPath}/board/free">커뮤니티(자유게시판)</a></h3>
                    <h3><a href="${pageContext.request.contextPath}/lecture/study?page=1">수업정보</a></h3>
                    <h3><a href="${pageContext.request.contextPath}/teacher/lecturer?page=1">강사정보</a></h3>
                    <h3><a href="${pageContext.request.contextPath}/consumer/basic">고객센터(고객센터 홈)</a></h3>
                </div>
			</th>
            
            <td style="vertical-align: top">
                <table>
                    <tr>
                        <th>
                            공지/그림/광고
                        </th>
                    </tr>
                    <tr>
                        <td>
                            <div>