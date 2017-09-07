<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script src="http://code.jquery.com/jquery-latest.js"></script>
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
		$(".board-comment").on("submit", function() {
			event.preventDefault();
        	
        	$.ajax({
        		url: "comment",
        		data: $(this).serialize(),
        		success: function(res, context) {
        			$(".comment${context}").append(res);
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
                    <h3><a href="${pageContext.request.contextPath}/member/login">로그인</a></h3>
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