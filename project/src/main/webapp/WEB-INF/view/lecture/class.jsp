<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/WEB-INF/view/template/header.jsp" %>

	<div class="wrap">
		<table width="600" class="borN">
			<tbody>
				<tr>
					<td rowspan="2" width="20%">
						<img src="<c:url value="/img/bono.png"/>" width="180" height="180">
					</td>
                    <td><input type="text" class="row" value="[${info.tag}] ${info.title}" readonly></td> 
				</tr>
				
				<tr>
                     <td><input type="text" class="row" value="${info.intro}" readonly></td>
				</tr>
 
                <tr>
                    <td colspan="2"><textarea class="textarea" readonly>${info.detail}</textarea></td>
                </tr>
			</tbody>
		</table>	
        
        <div class="left" align="left">
        <a href="#">강사 정보</a>
        </div>
        <div class="right" align="right">
<<<<<<< HEAD
        <a href="${pageContext.request.contextPath}/lecture/req?no=${no}">신청하기</a>
        <a href="study?page=1">목록보기</a>
=======
        <a href="#">찜하기</a>
        <a href="${pageContext.request.contextPath}/lecture/req?no=${no}">신청하기</a>
        <a href="study?page=${page}">목록보기</a>
>>>>>>> branch 'master' of https://github.com/ks0719/itbank0829.git
        </div>
	</div>

<%@ include file="/WEB-INF/view/template/footer.jsp" %>