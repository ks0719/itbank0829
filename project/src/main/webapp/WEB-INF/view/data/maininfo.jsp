<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/view/template/header.jsp" %>

<table>
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
  
    
</div>
</nav>
<nav class="navline">
<h1>현재 진행중인 수강정보</h1><hr>
    <div class="overflow">
    
	    <c:forEach var="lecture" items="${mlist}">
    		<div>[${lecture.tag}]　${lecture.teacher}　${lecture.title}　　강의시간 ${lecture.time}　강의형태 ${lecture.type}
    		<hr></div>
    	</c:forEach>
        
    </div>
</nav>
    <nav class="navline">
<h1>포인트 사용 내역</h1><hr>
        <div class="overflow">
<!--       <div><a href="#" class="text">테스트와 함께하는 즐거운 테스트 시간</a> -->
<!--     <hr></div> -->
<!--     <div><a href="#" class="text">테스트와 함께하는 즐거운 테스트 시간</a> -->
<!--     <hr></div> -->
<!--         <div><a href="#" class="text">테스트와 함께하는 즐거운 테스트 시간</a> -->
<!--     <hr></div> -->
<!--         <div><a href="#" class="text">테스트와 함께하는 즐거운 테스트 시간</a> -->
<!--     <hr></div>   -->
        </div>
</nav>
    <nav class="navline">
<h1>내가 쓴 글</h1><hr>
        
        <div class="overflow">
        
	    	<c:forEach var="board" items="${blist}">
	    		<c:choose>
	    			<c:when test="${board.seq > 1}">
	    				<div><a href="${pageContext.request.contextPath}/board/${board.path}/detail?no=${board.context}" class="text">[${board.head}]　${board.title}　　조회수 ${board.read}　추천수 ${board.best}</a>
	    			</c:when>
	    			<c:otherwise>
	    				<div><a href="${pageContext.request.contextPath}/board/${board.path}/detail?no=${board.no}" class="text">[${board.head}]　${board.title}　　조회수 ${board.read}　추천수 ${board.best}</a>
	    			</c:otherwise>
	    		</c:choose>
	    		<hr></div>
	    	</c:forEach>
      
        </div>
         
</nav>
</tbody>
</table>

<%@ include file="/WEB-INF/view/template/footer.jsp" %>
