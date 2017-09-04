<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<script src="http://code.jquery.com/jquery-latest.js"></script>

<script>
	$(document).ready(function() {
		$(".mail").on("click", function() {
			window.open('${pageContext.request.contextPath}/data/mail/mailDetail', '', 'width=500, height=500, menubar=no');
		});
	});
	
	$(document).ready(function(){
		$("#checkAll").click(function(){
			if($("#checkAll").prop("checked")){
				$("input[name=chk]").prop("checked",true);
			}else{
				$("input[name=chk]").prop("checked",false);
			}
		});
	});
	
// 	$(document).ready(function(){
// 		$("#remove").click(function(){
// 			if($("input[name=chk]").prop("checked")){

// 			}
// 		});
// 	});


// 	function del() {
// 		$("input:checked").each(function() {
// 			var checked = $(this).attr("checked"); // 체크된 값만을 불러 들인다.
// 			if (checked == true) {
// 				$(this).next().remove(); //span내용지우기
// 				$(this).remove(); //checkbox 지우기
// 			}
// 		});
// 	}

</script>

<head>
<title>쪽지함</title>
</head>
<body>
	<table>
		<tr>
			<th rowspan="5">
                <h3><a href="${pageContext.request.contextPath}/data/mail?box=index">받은 쪽지함</a></h3>
                <h3><a href="${pageContext.request.contextPath}/data/mail?box=sent">보낸 쪽지함</a></h3>
                <h3><a href="${pageContext.request.contextPath}/data/mail?box=spam">스팸 쪽지함</a></h3>
                <h3><a href="${pageContext.request.contextPath}/data/mail?box=protect">보관함</a></h3>
                <h3><a href="${pageContext.request.contextPath}/data/mail?box=garbage">휴지통</a></h3>
			</th>
			
            <td style="vertical-align: top">
                <table>
                   
                    
                    <thead>
                   		 <tr>
                        	<td colspan="7">
<!--                         		<form action="#" method="post"> -->
	                            	<button id="remove">삭제하기</button>
<!-- 	                            </form> -->
	                            	<button>쪽지쓰기</button>
	                            	<button>보관하기</button>
                        	</td>
                    	</tr> 
                    
	                    <tr>
	                        <th><input id="checkAll" type="checkbox" ></th>
	                        <th>아이디</th>
	                        <th>분류</th>
	                        <th>제목</th>
	                        <th>내용</th>
	                        <th>보낸날짜</th>
	                        <th>읽기여부</th>
	                    </tr>
                    </thead>
                    
                    <tbody>
                    	<c:forEach var="list" items="${list }">
                    		<tr>
	                    		<td><input type="checkbox" name="chk" ></td>
		                        <td>${list.mail_writer }</td>
		                        <td>${list.mail_tag}</td>
		                        <td>${list.mail_title }</td>
		                        <td>${list.mail_content }</td>
		                        <td>${list.mail_reg }</td>
		                        <td>${list.mail_read }</td>
                    		</tr>
                    	</c:forEach>
                    </tbody>
               
                </table>
            </td>
		</tr>
	</table>
	
	<h1>${param.box}</h1>
</body>
</html>