<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/view/template/header.jsp" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
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
<head>
<title>강사 상세보기</title>
</head>
<body>
<c:set var="teacherno" value="${teacher.teacherno }"/>


<form action="" method="post" enctype="multipart/form-data">
<input type="hidden" id="NNO" value="${teacher.teacherno }">
<c:forEach var="teacher" items="${teacherList}">
		<table width="600" class="borN">
			<tbody>
				<tr>
					<td rowspan="2" width="20%">
						<img src="${teacher.getPicture_realname() }" width="180" height="180">
					</td>
                    <td><input type="text" class="row" id="test" value="${teacher.sort }" readonly></td> 
				</tr>
				
				<tr>
                     <td><input type="text" class="row" value="${teacher.name}" readonly></td>
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
		<button class="input-btn"  onclick="javscript:history.back();">이전 페이지로 돌아가기</button>
		<input class="input-btn" type="button" id="apply" value="승인" >
		<input class="input-btn" type="button" id="Napply" value="거절">
</form>
</body>
</html>


<%@ include file="/WEB-INF/view/template/footer.jsp" %> 