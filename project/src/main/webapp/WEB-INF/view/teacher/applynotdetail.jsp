<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/view/template/header.jsp" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>




$(document).ready(function(){
	$("#apply").click(function(){
		var result=false;
		
		$.ajax({
			async:false,
			url:"accept",
			type:"post",
			data:{acceptteacher:${teacherno}},
			dataType:"text",
			success:function(){
				result=true;
			}
		})
		if(result){
			alert("강사신청 승인 성공!");
		}
		
	});
	
	
	$("#Napply").click(function(){
		console.log("거절실행");
		var result=false;
		
		$.ajax({
			async:false,
			url:"acceptnot",
			type:"post",
			data:{notaccept:${teacherno}},
			dataType:"text",
			success:function(){
				result=true;
			}
		})
		if(result){
			alert("강사신청 거절 완료!");
		}
	});
	
	
	
});
</script>
    <div class="example-modal">
	<div class="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">미승인 강사 상세보기</h4>
				</div>
				<div class="modal-body"> 

<c:set var="teacherno" value="${teacher.teacherno }"/>


<form action="" method="post" enctype="multipart/form-data">
<input type="hidden" id="NNO" value="${teacher.teacherno }">
<c:forEach var="teacher" items="${teacherList}">
		<table width="600" class="table table-bordered table-hover">
			<tbody>
				<tr>
					<td rowspan="2" width="20%">
						<img  src="${teacher.getPicture_realname() }" width="180" height="180">
					</td>
                    <td><input class="form-control" type="text" class="row" id="test" value="${teacher.sort }"readonly></td> 
				</tr>
				
				<tr>
                     <td><input class="form-control" type="text" class="row" value="${teacher.name}" readonly></td>
				</tr>
 
                <tr>
                    <td colspan="2"><textarea class="textarea" readonly>${teacher.career}</textarea></td>
                </tr>
                
                <tr>
                    <td colspan="2"><textarea class="textarea" readonly>${teacher.intro}</textarea></td>
                </tr>
			</tbody>
		</table>
		
</c:forEach>
		<br>
		<button class="btn btn-primary pull-right" onclick="javscript:history.back();">이전 페이지로 돌아가기</button>
		<input class="btn btn-primary pull-right" type="button" id="Napply" value="거절">
		<input class="btn btn-primary pull-right" type="button" id="apply" value="승인" >
</form>

</div>
				<br>
				<div class="modal-footer">
				</div>
			</div>
		</div>
	</div>
</div>


<%@ include file="/WEB-INF/view/template/footer.jsp" %> 