<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/view/template/header.jsp" %>

<section class="content">
	<div class="row">
		<c:forEach var="board" items="${boardList}">
	
	
			<div class="col-xs-12">
				<div class="box box-primary">
				
					<div class="box-header with-border">
						<h3 class="box-title pull-left">${board.head}</h3>
					</div>
					
					
					<div class="box-body no-padding">
					
						<div class="mailbox-read-info">
							<h3 class="pull-left"><c:if test="${board.seq > 1}">답글 : </c:if>${board.title}</h3>
                    		<br>
                    		<h5 class="pull-left">${board.writer}</h5>
                    		<h5><span class="mailbox-read-time pull-right">${board.auto}</span></h5>
                    		<br>
						</div>
						
						
						<div class="mailbox-read-message">
							${board.detail}
						</div>
						
					</div>
					
					<!-- 버튼 위치 공간 시작 -->
					<div class="box-footer">
						<div class="pull-right">
							<c:choose>
								<c:when test="${not empty cookie.mynick.value}">
									<c:choose>
										<c:when test="${board.no eq no}">
											<input type="button" value="답글쓰기" class="input-btn" onclick="location.href='reply?no=${board.no}&context=${no}';">
										</c:when>
									</c:choose>
									<c:choose>
										<c:when test="${memberNo eq board.memberNo}">
											<input type="button" value="추천하기" class="btn btn-info" onclick="alert('자신의 글은 추천할 수 없습니다'); return false;">
										</c:when>
										<c:otherwise>
											<input type="button" value="추천하기" class="btn btn-info" onclick="location.href='best?no=${board.no}&context=${no}';">
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<input type="button" value="답글쓰기" class="input-btn" onclick="alert('로그인이 필요한 서비스 입니다'); return false;">
									<input type="button" value="추천하기" class="btn btn-info" onclick="alert('로그인이 필요한 서비스 입니다'); return false;">
								</c:otherwise>
							</c:choose>
	
							<c:choose>
								<c:when test="${memberNo eq board.memberNo}">
									<input type="button" value="수정하기" class="input-btn" onclick="location.href='edit?no=${board.no}&context=${no}';">
									<input type="button" value="삭제하기" class="input-btn board-delete" data-no="${board.no}" data-context="${no}">
								</c:when>
							</c:choose>
					
							<c:choose>
						       	<c:when test="${empty cookie.mynick.value}">
									<input type="button" value="글쓰기" class="input-btn" onclick="alert('로그인이 필요한 서비스 입니다'); return false;">
						       	</c:when>
						       	<c:otherwise>
									<input type="button" value="글쓰기" class="input-btn" onclick="location.href='write?seq=1';">
						       	</c:otherwise>
							</c:choose>
							<input type="button" value="목록으로" class="input-btn" onclick="location.href='${pageContext.request.contextPath}/board/${url}';">
							<br>
						</div>
					</div>
					<!-- 버튼 위치 공간 끝 -->
					
					<br><br>
					
					<!-- 첨부파일 출력 공간 -->
					<c:if test="${board.originfile ne null}">
						<div class="box-footer">
							<ul class="mailbox-attachments clearfix">
								<li>
									<div class="mailbox-attachment-info">
										<a href="download/${board.no}" class="mailbox-attachment-name"><i class="fa fa-paperclip"></i>${board.originfile}</a>
											<span class="mailbox-attachment-size">
												${board.filesize} KB
											</span>
									</div>
								</li>
							</ul>
						</div>
					</c:if>
					
					<!-- 댓글 작성 공간 -->
					<div class="box-footer">
					
						<form action="#" class="board-comment" value="${board.no}">
							<input type="hidden" name="topcontext" value="${no}">
							<input type="hidden" name="context" value="${board.no}">
							<input type="text" name="detail" class="user-input" placeholder="댓글 입력">
							<c:choose>
								<c:when test="${not empty cookie.mynick.value}">
									<input type="submit" class="input-btn" value="등록">
								</c:when>
								<c:otherwise>
									<input type="submit" class="input-btn" onclick="alert('로그인이 필요한 서비스 입니다'); return false;" value="등록">
								</c:otherwise>
							</c:choose>
						</form>
					
					</div>
					
					<!-- 댓글 출력 공간 -->
					
					<div id="comments${board.no}">
						<c:forEach var="comment" items="${list}">
							<c:if test="${comment.context eq board.no}">
								<div id="comment${comment.no}">
									${comment.detail}-${comment.writer} ${comment.reg}
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
									<br>
								</div>
							</c:if>
						</c:forEach>
					</div>
		
				</div>
			</div>
		
		</c:forEach>
	</div>
</section>
	
<%@ include file="/WEB-INF/view/template/footer.jsp" %>