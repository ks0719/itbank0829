<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<style>
    .overflow{
        width:100%;
        height:100px; 
        overflow:auto 
    }
    .text{
        text-decoration: none;
        text-align: center; 
        display: block
    }
#nav ul{
display:inline-block;
border: 1px solid black;
}
#nav ul li{
	display: block;
	float: left;

 }    
 a.no-uline { 
text-decoration:none; 
text-align:center;
         display: block; 
          width: 176px;
          
 }
    nav.navline{
        display: inline-block;
        width: 600px;
        margin: 15px;
        height: 400px;
        border: 1px solid black;
        
    }

    </style>
</head>
<body>
<table>
<thead>
<nav id="nav" >
	<ul>
		<li><a href="#" class="no-uline">내 정보 확인</a></li>     
        <li><a href="#" class="no-uline">비밀번호 변경</a></li>
		<li><a href="#" class="no-uline">수강 내역</a></li>
		<li><a href="#" class="no-uline">포인트 샾</a></li>
		<li><a href="#" class="no-uline">회원 탈퇴</a></li>
		<li><a href="#" class="no-uline">강사 신청</a></li>
		<li><a href="#" class="no-uline">내가 쓴글</a></li>
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
</body>
</html>

    