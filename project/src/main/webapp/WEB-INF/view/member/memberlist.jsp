<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/view/template/header.jsp" %> 
<html>
<head>
<script>

$(document).ready(function(){
	$("#checkAll").click(function(){
		if($("#checkAll").prop("checked")){
			$("input[name=checkB]").prop("checked",true);
		}else{
			$("input[name=checkB]").prop("checked",false);
		}
	});
	
	
	$("input[name=checkB]").click(function(){
		var chk = $("input[name=checkB]");
		var tnf = true;
		
		for(var i=0; i<chk.length; i++){
			if(!$(chk[i]).prop("checked")){
				tnf=false;
			}
		}
		$("#checkAll").prop("checked", tnf);
	});
	
	$("#delete").on("click", function(){
		console.log("실행됨");
		jQuery.ajaxSettings.traditional = true;
		var unitval = [];
        
        $.ajax({
           url : "project/member/checkBox",
           type : "post",
           data : {
              "userid" : unitval
              },
           success : function() {
              alert("강제 탈퇴 완료");
              location.reload();
           }

        });
	});
});
</script>

<title>회원리스트</title>
</head>
<body>
<h1>회원리스트</h1>
<div class="page-wrap">
	<div class="table-wrap">
		<table border="1" class="tableUnit" rules=rows>
			<tr>
				<th>
					<input type="checkbox" id="checkAll">
				</th>
				<th>
					번호
				</th>
				<th>
					회원ID
				</th>
				<th>
					[회원권한]
				</th>
				<th>
					회원이름
				</th>
				<th>
					회원닉네임
				</th>
				<th>
					가입일
				</th>
				<th>
					마일리지
				</th>
			</tr>
			<c:forEach var="member" items="${list}">
			<tr>
				<td>
					<input type="checkbox" name="checkB" value="${member.id }">
				</td>
				<td>
					${member.no}
				</td>
<!-- 				<td class="title"> -->
<%-- 					[${info.head}] <a href="${path}/detail?no=${info.no}">${info.title}</a> --%>
<!-- 				</td> -->
				<td>
					<a href="${pageContext.request.contextPath}/member/memberdetail?no=${member.no}">${member.id }</a>
				</td>
				<td>
					[${member.power}]
				</td>
				<td>
					${member.name}
				</td>
				<td>
					${member.nick}
				</td>
				<td>
					${member.reg}
				</td>
				<td>
					${member.mileage}
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	
	<%-- 회원 삭제 --%>
	<div class="align-right">
		<input type="button" value="삭제"  id="delete" class="input-btn" >
	</div>
	
	<div class="row">
		<div class="paging-wrap">
			<c:if test="${startBlock > 1}">
		        <div class="paging-unit"><a href="${url}&page=${startBlock - 1}">&lt;</a></div>
			</c:if>
	
			<c:forEach var="i" begin="${startBlock}" end="${endBlock}" step="1">
				<c:choose>
					<c:when test="${i eq page}">
						<div class="paging-unit active">${i}</div>
					</c:when>
					<c:otherwise>
	        			<div class="paging-unit"><a href="${url}&page=${i}">${i}</a></div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<c:if test="${endBlock < blockTotal}">
	        	<div class="paging-unit"><a href="${url}&page=${endBlock + 1}">&gt;</a></div>
			</c:if>
	    </div>
	</div>
	
	<form action="memberList" class="wrap">
		<input type="hidden" name="page" value="1">
		<select name="type" title="분류선택" class="user-input">
			<option value="id">ID검색</option>
			<option value="name">이름검색</option>
			<option value="nick">닉네임검색</option>
		</select>
		<input type="search" name="key" class="user-input" placeholder="검색 내용" value="${key}" required>
		<input type="submit" value="검색" class="input-btn">
	</form>
</div>
</body>
</html>

<%@ include file="/WEB-INF/view/template/footer.jsp" %> 