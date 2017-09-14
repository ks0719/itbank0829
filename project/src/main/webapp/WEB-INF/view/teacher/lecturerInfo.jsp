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
                    <td><input type="text" class="row" value="[${info.sort}] ${info.name}" readonly></td> 
				</tr>
				
				<tr>
                     <td><input type="text" class="row" value="${info.career}" readonly></td>
				</tr>
 
                <tr>
                    <td colspan="2"><textarea class="textarea" readonly>${info.intro}</textarea></td>
                </tr>
			</tbody>
		</table>
        <div class="left" align="left">
			평점 : ${info.grade} 강의횟수 : ${info.count} <a href="" class="now-lecture" data-key="${info.name}">현재 진행중인 강의</a>
        </div>
        <div class="right" align="right">
	        <a href="" onclick="window.open('${pageContext.request.contextPath}/data/mail/send?nick=${info.name}', '쪽지보내기', 'width=800, height=500'); return false;">쪽지보내기</a> 
        	<a href="lecturer${url}">목록보기</a>
        </div>
	</div>

<%@ include file="/WEB-INF/view/template/footer.jsp" %>