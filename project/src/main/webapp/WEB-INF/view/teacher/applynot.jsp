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
	
	
	
	
	
	$("#apply").on("click", function(){
		var result=false;
		$("input[name=checkB]:checked").each(function(){
			var teacherid=$(this).val();	
	        $.ajax({
	        	async: false,
	           url : "checkapply",
	           type : "post",
	           data : {teacherid:teacherid},
	           dataType:"text",
	           success : function() {
	        	   result=true;
	           }
	        })
	        
        });
			if(result){
				alert("강사신청 승인 완료!");
			}
	});
	
	
	
	$("#deleteteacher").on("click", function(){
		var result=false;
		$("input[name=checkB]:checked").each(function(){
			var teacherid=$(this).val();	
	        $.ajax({
	        	async: false,
	           url : "checkdelete",
	           type : "post",
	           data : {teacherid:teacherid},
	           dataType:"text",
	           success : function() {
	        	   result=true;
	           }
	        })
	        
        });
			if(result){
				alert("강사신청 거절 완료!");
			}
	});
	
	
	
	
	
	
});
</script>

<title>미승인 강사</title>
</head>
<body>
<h1>미승인 리스트</h1>
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
					이름
				</th>
				<th>
					주언어
				</th>
				<th>
					신청일
				</th>
			</tr>
			<c:forEach var="teacher" items="${list}">
			<tr>
				<td>
					<input type="checkbox" name="checkB" value="${teacher.name }">
				</td>
				<td>
					${teacher.teacherno }
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/teacher/applynotdetail?teacherno=${teacher.teacherno}">${teacher.name }</a>
				</td>
				<td>
					${teacher.sort }
				</td>
				<td>
					${teacher.reg }
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	
	<%--한번에 승인//거절 --%>
	<div class="align-right">
		<input type="button" value="거절"  id="deleteteacher" class="input-btn" >
		<input type="button" value="승인" id="apply" class="input-btn">
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
	
	<form action="applynot" class="wrap">
		<input type="hidden" name="page" value="1">
		<select name="type" title="분류선택" class="user-input">
			<option value="sort">주 언어</option>
			<option value="name">이름검색</option>
		</select>
		<input type="search" name="key" class="user-input" placeholder="검색 내용" value="${key}" required>
		<input type="submit" value="검색" class="input-btn">
	</form>
</div>
</body>
</html>

<%@ include file="/WEB-INF/view/template/footer.jsp" %> 