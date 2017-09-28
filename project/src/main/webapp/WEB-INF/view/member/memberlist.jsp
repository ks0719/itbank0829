<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/view/template/header.jsp" %> 
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
		var result=false;
		console.log("실행됨");
		$("input[name=checkB]:checked").each(function(){
			var userid=$(this).val();	
	     
	        $.ajax({
	        	async: false,
	           url : "checkBox",
	           type : "post",
	           data : {userid:userid},
	           dataType:"text",
	           success : function() {
	        	   result=true;
	           }
	        })
	        
        });
			if(result){
				alert("강제 회원 삭제 성공");
			}
	});
	
	
});
</script>

<h1>회원리스트</h1>
<div class="row">
	<div class="col-xs-12">
		<div class="box box-primary">
			<div class="box-body no-padding">
			
			<div class="table-responsive">
			
		<table border="1" class="table table-bordered table-hover" >
		<thead>
			<tr>
				<th>
					<input type="checkbox" id="checkAll">
				</th>
				<th>
					번호
				</th>
				<th class="title">
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
			</thead>
			
			
			<tbody>
			<c:forEach var="member" items="${list}">
			<tr>
				<td>
					<input type="checkbox" name="checkB" value="${member.id }">
				</td>
				<td>
					${member.no}
				</td>
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
			</tbody>
			
			
	<tfoot>
	<tr>
	<td colspan="8" style="text-align:right;">
	<%-- 회원 삭제 --%>
		<input type="button" value="삭제"  id="delete" class="btn btn-primary" >
	</td>
	</tr>
	
	<tr>
	<td colspan="8" style="text-align:center;">
			<c:if test="${startBlock > 1}">
		        <button class="btn btn-default btn-sm" onclick="location.href='${url}page=${startBlock - 1}'"><i class="fa fa-chevron-left"></i></button>
			</c:if>
	
			<c:forEach var="i" begin="${startBlock}" end="${endBlock}" step="1">
				<c:choose>
					<c:when test="${i eq page}">
						<button class="btn btn-flat btn-sm">${i}</button>
					</c:when>
					<c:otherwise>
	        			<button class="btn btn-default btn-sm" onclick="location.href='${url}page=${i}'">${i}</button>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<c:if test="${endBlock < blockTotal}">
	        	<button class="btn btn-default btn-sm" onclick="location.href='${url}page=${endBlock + 1}'"><i class="fa fa-chevron-right"></i></button>
			</c:if>
	</td>
	</tr>
			
	<tr>	
	<td colspan="8" style="text-align:center;">
	<div class="form-group">
	<form action="memberlist" class="wrap">
		<input type="hidden" name="page" value="1">
		<select name="type" title="분류선택" class="form-control" style="display: inline; width: 20%;">
			<option value="id">ID검색</option>
			<option value="name">이름검색</option>
			<option value="nick">닉네임검색</option>
		</select>
		<input type="search" name="key" class="form-control" style="display: inline; width: 35%;" placeholder="검색 내용" value="${key}" required>
		<input type="submit" value="검색" class="btn btn-primary">
	</form>
		  
	</div>
	</td>
	</tr>
		  </tfoot>
		  </table>
		  </div>  
			    
			</div>
		</div>
		
	</div>
</div>

<%@ include file="/WEB-INF/view/template/footer.jsp" %> 