<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="http://code.jquery.com/jquery-3.2.1.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
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
				if($("#id").val()==""){
					alert("아이디를 입력하세요");
				}
				else{
					$.ajax({
						url:"idcheck",
						type:"post",
						data:{id:$("#id").val()},
						dataType:"text",
						success:function(){
							var idregex=/^[a-zA-Z0-9]{8,20}$/;
						 	var idtarget= document.querySelector("input[name=id]");
							if(!idregex.test(idtarget.value)){
						 		alert("ID는 영문,숫자 조합 8~20자");
						 	}else{
								alert("사용 가능한 아이디 입니다.");
								$("#id").attr("readonly","readonly");
								$("#idcheck").val("취소");
						 	}
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
  		
	}
  	
  	//핸드폰 번호 체크
  	function phoneCheck(){
  		if($("#pcheck").val()=="중복확인"){
  			
  			var phoneregex=/^[010]{3}[0-9]{3,4}[0-9]{4}$/; 
			var phonetarget=document.querySelector("input[name=phone]");
			 if(!phoneregex.test(phonetarget.value)){
		 		alert("올바른 전화번호를 입력해주세요.");
			 }else{
				 $.ajax({
						url:"pcheck",
						type:"post",
						data:{phone:$("#phone").val()},
						dataType:"text",
						success:function(){
							alert("사용 가능한 전화번호 입니다.");
							$("#phone").attr("readonly","readonly");
							$("#pcheck").val("취소");
						},
						error:function(){
							alert("이미 등록된 전화번호 입니다.");
						}
					});
  			}
		}else{
			$("#pcheck").val("중복확인");
			$("#phone").removeAttr("readonly");
		}
  	}

  //완료버튼 이벤트
	$(document).ready(function() {
		$("#sub").on("click",function () {
			var pwregex=/^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*=+]).{8,20}$/;
			var nickregex=/^[가-힣]{1,6}$/;
			
		 	var pwtarget=document.querySelector("input[name=pw]");
		 	var nicktarget=document.querySelector("input[name=nickname]");
		 	
		 	 if(!pwregex.test(pwtarget.value)){
		 		alert("비밀번호 조건이 맞지 않습니다.");
		 	}else if(!nickregex.test(nicktarget.value)){
		 		alert("닉네임 조건이 맞지 않습니다.");
		 	}else if(!phoneregex.test(phonetarget.value)){
		 		alert("핸드폰 번호 조건이 맞지 않습니다.");
		 	}else{
		 		
		 	}
		});
	});
	  
</script>

<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
	<meta charset="utf-8">
	<title>Welcome</title>
</head> 
<body>

	<table>
		<tr>
			<th rowspan="5">
                <div>
                    <h3>회원정보 넣을 곳</h3>
                    <h3><a href="#" id="openMask">로그인</a></h3>
                    <h3><a href="${pageContext.request.contextPath}/member/sign">회원가입</a></h3>
                    <h3><a href="${pageContext.request.contextPath}/data/maininfo">내 정보 보기(maininfo.jsp)</a></h3>
                    <h3><a href="" onclick="window.open('${pageContext.request.contextPath}/data/mail?box=index', '쪽지함', 'width=800, height=500'); return false;">쪽지함</a></h3>
					<h3><a href="" onclick="window.open('${pageContext.request.contextPath}/data/manageLecture?box=index', '수강관리', 'width=1000, height=500'); return false;">내 수강정보</a></h3>
					사진
					닉네임
					내 등급
                </div>
                <div>
                    <h3><a href="${pageContext.request.contextPath}/board/free">커뮤니티(자유게시판)</a></h3>
                    <h3><a href="${pageContext.request.contextPath}/lecture/study?page=1">수업정보</a></h3>
                    <h3><a href="${pageContext.request.contextPath}/lecture/teacher">강사정보</a></h3>
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