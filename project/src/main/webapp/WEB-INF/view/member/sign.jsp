<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp" %>    
<html>
<head>
<title>회원가입</title>
 <script src="http://code.jquery.com/jquery-3.2.1.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
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
$(document).ready(function() {
     var input = $("#ids");
     $("#idcheck").on("click",function () {
    	 
    	 if($("#idcheck").val()=="중복확인"){
    		 if($("#ids").val()==""){
    			 alert("아이디를 입력하세요");
    		 }
    		 else{
		        $.ajax({
		           url:"idcheck",
		           type:"post",
		           data:{id:input.val()},
		           dataType:"text",
		           success:function(){
		              alert("사용 가능한 아이디 입니다.");
		              $("#sub").removeAttr("disabled");
		              $("#ids").attr("disabled","disabled");
		              $("#idcheck").val("취소");
		           },
		           error:function(){
		              alert("중복된 아이디가 있습니다.");
		           }
		        });
    		 }
    	 }else{
    		 $("#sub").attr("disabled","disabled");
             $("#idcheck").val("중복확인");
             $("#ids").removeAttr("disabled");
    	 }
     });
  });
  
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
               var regex = /^[가-힣]{1,6}$/;
               var target = document.querySelector("input[name=nickname]");
               if(!regex.test(target.value)){
                    target.style.border = "1px solid blue";
               }else{
                   target.style.border = "1px solid red";
               }
           }
  	
  	//핸드폰 번호 체크
  	function phoneCheck(){
  		var regex=/^[010]{3}[0-9]{4}[0-9]{4}$/; 
  		var target=document.querySelector("input[name=phone]");
  		if(regex.test(target.value)){
  			target.style.border="2px solid blue";
  		}else{
	  		target.style.border="2px solid red";
  		}
  	}
  	
  	
// 	//완료버튼 이벤트
	
// 	 $("#sub").on("click",function () {
// 		 var idregex=/^[\w]{8,20}$/;
// 		 var pwregex=/^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*=+]).{8,20}$/;
// 		 var nickregex = /^[가-힣]{1,6}$/;
// 		 var phoneregex=/^[010]{3}[0-9]{4}[0-9]{4}$/; 
// 		 var idtarget=("input[name=ids]");
// 		 var pwtarget=("input[name=pw]");
// 		 var nicktarget=("input[name=nick]");
// 		 var phonetarget=("input[name=phone]");
		 
// 		 if(idregex.test(idtarget.value))
// 	 });
</script>

</head>
<body>
	<h1>회원 가입</h1>
	<form action="" method="post" >
		<input type="text" name="id" placeholder="ID입력"  id="ids"  onkeyup="idCheck();" required>
		<input type="button"  id="idcheck" value="중복확인"  >
		<br><br>
		<input type="password" name="pw" placeholder="PW입력" onkeyup="pwCheck();" required>
		<br><br>
		<input type="password" name="pw2" id="pw2"placeholder="PW재입력" onkeyup="pw2Check();"required>
		<br><br>
		<input type="text" name="name" placeholder="이름입력" required>
		<br><br>
		<input type="text" name="nickname" placeholder="닉네임입력" onkeyup="nickCheck();" required>
		<br><br>
		<input type="tel" name="phone" id="phone"placeholder="번호입력(-없이)" onkeyup="phoneCheck();"required>
		<br><br>
		<input type="text" name="post" placeholder="우편번호" readonly>
		<input type="button" value="우편번호찾기" onclick="daumAddressSearch();">
		<br><br>
		<input type="text" name="addr1" placeholder="기본주소" readonly>
		<br><br>
		<input type="text" name="addr2" placeholder="상세주소" required>
		<br><br>
		<input type="text" name="sort" placeholder="사용가능한 언어입력" required>
		<br><br>
		<input id="sub"  disabled type="submit" value="완료" >
	</form>
</body>
</html>
<%@ include file="/WEB-INF/view/template/footer.jsp" %>