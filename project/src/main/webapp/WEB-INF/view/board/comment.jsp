<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${comment.detail ne null}">
	<div id="comment${comment.no}">
		<div class="modal-header"></div>
		<div class="modal-body">
				<div class="col-md-12">
					<ul class="timeline">
						<li>
							<div class="timeline-item">
								<span class="time"><i class="fa fa-clock-o"></i>${comment.reg}</span>
			                    <h3 class="timeline-header"><a href="#">${comment.writer}(누르면 쪽지로 가게끔 하기)</a></h3>
			                    <div class='timeline-footer pull-left'>
			                    	<textarea class="needResize" style="font-size:15; outline: none; border:0 solid black; width:430px; min-height:90px; overflow:visible; resize:none;" readonly>${comment.detail}</textarea>
			                    </div>
							</div>
						</li>
					</ul>
				</div>
		</div>
		<div class="modal-footer">
			<c:choose>
				<c:when test="${comment.memberNo eq memberNo}">
				
				
				
					<a href="" onclick="alert('자신의 글은 추천할 수 없습니다'); return false;">추천</a> <span>${comment.best}</span>
					
					
					
				</c:when>
				<c:when test="${not empty cookie.mynick.value}">
				
				
					
					<a href="" class="comment-best" value="${comment.no}">추천</a> <span id="best${comment.no}">${comment.best}</span>
					
					
					
				</c:when>
				<c:otherwise>
				
				
				
					<a href="" onclick="alert('로그인이 필요한 서비스 입니다'); return false;">추천</a> <span>${comment.best}</span>
					
					
					
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${memberNo eq comment.memberNo}">
				
				
					<a href="" class="comment-delete" value="${comment.no}">삭제</a>
					
					
					
				</c:when>
			</c:choose>
		</div>
	</div>
</c:if>

