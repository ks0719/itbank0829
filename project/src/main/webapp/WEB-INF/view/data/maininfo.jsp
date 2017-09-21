<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/template/header.jsp" %>

<table>
<thead>
<nav id="nav" >
	<ul>
		<li><a href="${pageContext.request.contextPath}/data/edit" class="no-uline">내 정보 변경</a></li>     
        <li><a href="${pageContext.request.contextPath }/data/changepw" class="no-uline">비밀번호 변경</a></li>
        <li><a href="" class="no-uline" onclick="window.open('${pageContext.request.contextPath}/data/manageLecture?box=index', '수강관리', 'width=1000, height=500'); return false;">내 수강정보</a></li>
		<li><a href="${pageContext.request.contextPath }/data/point" class="no-uline">포인트 샾</a></li>
		<li><a href="${pageContext.request.contextPath}/member/deletemember" class="no-uline">회원 탈퇴</a></li>
		<li><a href="#" id="lecturer-apply" value="${dto.nick}" class="no-uline">강사 신청</a></li>
		<li><a href="#" class="no-uline">내가 쓴글</a></li>
		<li><a href="${pageContext.request.contextPath}/teacher/teacherMain" class="no-uline">강사 전용</a></li>
	</ul>
</nav>
</thead>
<tbody>
<hr>
<nav class="navline">
<h1>내 프로필</h1><hr>
<div class="overflow">
    <div style="border: 1px solid black; display: inline-block; width: 60px; margin: 10px;margin-right: -10px; background-color: aqua;">
    아이디
    </div>
    <div style="display: inline-block;border-top: 1px solid; border-right: 1px solid; border-bottom:  1px solid;padding-left: 10px  ">
    ${dto.id }
    </div>
    <div style="border: 1px solid black; display: inline-block; width: 60px; margin: 10px;margin-right: -10px; background-color: aqua;">
     이름
    </div>
    <div style="display: inline-block;border-top: 1px solid; border-right: 1px solid; border-bottom:  1px solid;padding-left: 10px  ">
    ${dto.name }
    </div>
    <div style="border: 1px solid black; display: inline-block; width: 60px; margin: 10px;margin-right: -10px; background-color: aqua;">
    닉네임
    </div>
    <div style="display: inline-block;border-top: 1px solid; border-right: 1px solid; border-bottom:  1px solid;padding-left: 10px  ">
    ${dto.nick }
    </div><br>
    <div style="border: 1px solid black; display: inline-block; width: 80px; margin: 10px;margin-right: -10px; background-color: aqua;">
    전화번호
    </div>
    <div style="display: inline-block;border-top: 1px solid; border-right: 1px solid; border-bottom:  1px solid;padding-left: 10px  ">
    ${dto.phone }
    </div>
    <div style="border: 1px solid black; display: inline-block; width: 80px; margin: 10px;margin-right: -10px; background-color: aqua;">
    마일리지
    </div>
    <div style="display: inline-block;border-top: 1px solid; border-right: 1px solid; border-bottom:  1px solid;padding-left: 10px  ">
    ${dto.mileage } 포인트
    </div>
    <div style="border: 1px solid black; display: inline-block; width: 60px; margin: 10px;margin-right: -10px; background-color: aqua;">
    아이디
    </div>
    <div style="display: inline-block;border-top: 1px solid; border-right: 1px solid; border-bottom:  1px solid;padding-left: 10px  ">
    안녕하세요
    </div>
    
</div>
</nav>
<nav class="navline">
<h1>현재 진행중인 수강정보</h1><hr>
    <div class="overflow">
    <div><a href="#" class="text">테스트와 함께하는 즐거운 테스트 시간</a>
    <hr></div>
    <div><a href="#" class="text">테스트와 함께하는 즐거운 테스트 시간</a>
    <hr></div>
        <div><a href="#" class="text">테스트와 함께하는 즐거운 테스트 시간</a>
    <hr></div>
        <div><a href="#" class="text">테스트와 함께하는 즐거운 테스트 시간</a>
    <hr></div>
        
    </div>
</nav>
    <nav class="navline">
<h1>포인트 사용 내역</h1><hr>
        <div class="overflow">
      <div><a href="#" class="text">테스트와 함께하는 즐거운 테스트 시간</a>
    <hr></div>
    <div><a href="#" class="text">테스트와 함께하는 즐거운 테스트 시간</a>
    <hr></div>
        <div><a href="#" class="text">테스트와 함께하는 즐거운 테스트 시간</a>
    <hr></div>
        <div><a href="#" class="text">테스트와 함께하는 즐거운 테스트 시간</a>
    <hr></div>  
        </div>
</nav>
    <nav class="navline">
<h1>내가 쓴 글</h1><hr>
        
        <div class="overflow">
      <div><a href="#" class="text">테스트와 함께하는 즐거운 테스트 시간</a>
    <hr></div>
    <div><a href="#" class="text">테스트와 함께하는 즐거운 테스트 시간</a>
    <hr></div>
        <div><a href="#" class="text">테스트와 함께하는 즐거운 테스트 시간</a>
    <hr></div>
        <div><a href="#" class="text">테스트와 함께하는 즐거운 테스트 시간</a>
    <hr></div>  
        </div>
         
</nav>
</tbody>
</table>

<%@ include file="/WEB-INF/view/template/footer.jsp" %>
