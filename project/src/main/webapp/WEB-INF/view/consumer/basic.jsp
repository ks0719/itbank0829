<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp"%>

<div class="row">
	<div class="col-md-12">
		<div class="nav-tabs-custom">
			<ul class="nav nav-tabs pull-right">
				<li class="pull-left header">
					<i class="fa fa-info-circle"></i>고객센터
				</li>
			</ul>
			
			
			<div class="tab-content">
				<div id="b2cBox" class="tab-pane active">
					<textarea class="b2cTextarea" rows="20" style="font-size:15px; outline: none; overflow-x:hidden; resize:none;" readonly>
					<%@include file="/WEB-INF/view/consumer/basicrule.jsp" %>
					</textarea>
				</div>
			</div>
			
			<ul class="nav nav-tabs pull-right">
				<c:choose>
					<c:when test="${empty cookie.mynick.value}">
						<li>
							<button class="btn btn-success" onclick="alert('로그인이 필요한 서비스 입니다'); return false;">문의목록 확인</button>
						</li>
						<li>
							<button class="btn btn-info" onclick="alert('로그인이 필요한 서비스 입니다'); return false;">1:1문의하러가기</button>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<button class="btn btn-success" onclick="location='${pageContext.request.contextPath}/consumer/b2clist'">문의목록 확인</button>
						</li>
						<li>
							<button class="btn btn-info" onclick="location='${pageContext.request.contextPath}/consumer/b2c'">1:1문의하러가기</button>
						</li>
					</c:otherwise>
				</c:choose>
			</ul>
			<br>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/view/template/footer.jsp"%>