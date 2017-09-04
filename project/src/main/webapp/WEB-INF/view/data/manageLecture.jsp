<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>수강 관리</title>
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
                <table>
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
	                        <th></th>
	                    </tr>
                	</thead>
                    
                    <tbody>
	                    <c:forEach var="mylecture" items="${list}">
		                    <tr>
		                        <td>${mylecture.tag}</td>
		                        <td>${mylecture.teacher}</td>
		                        <td>${mylecture.title}</td>
		                        <td>${mylecture.time}</td>
		                        <td>${mylecture.type}</td>
		                        <td>${mylecture.state}</td>
		                        <td>${mylecture.reg}</td>
		                        <td>${mylecture.price}</td>
		                        <td>자세히 보기</td>
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