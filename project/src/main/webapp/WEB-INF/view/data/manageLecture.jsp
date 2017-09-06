<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>수강 관리</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script>
$(document).ready(function(){
    $(".detail").click(function(){
        $("#changeable").load("${pageContext.request.contextPath}/lecture/class?no=32 .borN");
    });
});
</script>
</head>
<body>
	<table>
		<tr>
			<th rowspan="5">
                <h3><a href="${pageContext.request.contextPath}/data/manageLecture?box=all">전체 수강 내역</a></h3>
                <h3><a href="${pageContext.request.contextPath}/data/manageLecture?box=index">진행중인 강의</a></h3>
                <h3><a href="${pageContext.request.contextPath}/data/manageLecture?box=comp">수료한 강의</a></h3>
                <h3><a href="${pageContext.request.contextPath}/data/manageLecture?box=eval">미평가 강의</a></h3>
                <h3><a href="${pageContext.request.contextPath}/data/manageLecture?box=wish">찜한 강의</a></h3>
			</th>
            <td style="vertical-align: top">
                <table id="changeable">
                	<thead>
	                	<tr>
	                        <th>분류</th>
	                        <th>강사</th>
	                        <th>과목명</th>
	                        <th>강의시간</th>
	                        <th>수강형태</th>
	                        <th>수강상태</th>
	                        <th>수강날짜</th>
	                        <th>결제금액</th>
	                        <th>상세보기</th>
	                    </tr>
                	</thead>
                    
                    <tbody>
	                    <c:forEach var="list" items="${list}">
		                    <tr>
		                        <td>${list.tag}</td>
		                        <td>${list.teacher}</td>
		                        <td>${list.title}</td>
		                        <td>${list.time}</td>
		                        <td>${list.type}</td>
		                        <td>${list.state}</td>
		                        <td>${list.reg}</td>
		                        <td>${list.price}</td>
		                        <td>
<%-- 		                        	<a href="${pageContext.request.contextPath}/lecture/class?no=${list.no}" class="detail" id="">상세보기</a> --%>
		                        	<a href="" class="detail" id="${list.no}" onclick="return false;">상세보기</a>
		                        </td>
		                    </tr>
	                    </c:forEach>
                    </tbody>
                    
                    <tfoot>
                    	<tr>
                    		<td colspan="9">
                    			<c:forEach begin="${start}" end="${end}" var="i">
                    				<c:choose>
                    					<c:when test="${param.page == i || page==i}">
                    						[${i}]
                    					</c:when>
                    					<c:otherwise>
                    						<a href="?box=${param.box}&page=${i}">[${i}]</a>
                    					</c:otherwise>
                    				</c:choose>
                    			</c:forEach>
                    		</td>
                    	</tr>
                    </tfoot>
                </table>
            </td>
		</tr>
	</table>
</body>
</html>