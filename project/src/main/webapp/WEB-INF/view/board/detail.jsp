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
						<div>
							<c:choose>
								<c:when test="${not empty cookie.mynick.value}">
									<c:choose>
										<c:when test="${memberNo eq board.memberNo}">
											<input type="button" value="추천하기" class="btn btn-success pull-left" onclick="alert('자신의 글은 추천할 수 없습니다'); return false;">
										</c:when>
										<c:otherwise>
											<input type="button" value="추천하기" class="btn btn-success pull-left board-best" data-no="${board.no}">
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${board.no eq no}">
											<input type="button" value="답글쓰기" class="btn btn-primary pull-left" onclick="location.href='reply?no=${board.no}&context=${no}';">
										</c:when>
									</c:choose>
								</c:when>
								<c:otherwise>
									<input type="button" value="추천하기" class="btn btn-success pull-left" onclick="alert('로그인이 필요한 서비스 입니다'); return false;">
									<input type="button" value="답글쓰기" class="btn btn-primary pull-left" onclick="alert('로그인이 필요한 서비스 입니다'); return false;">
								</c:otherwise>
							</c:choose>
	
					
							<input type="button" value="목록으로" class="btn btn-default pull-right" onclick="location.href='${pageContext.request.contextPath}/board/${url}';">
							
							<c:choose>
						       	<c:when test="${empty cookie.mynick.value}">
									<input type="button" value="글쓰기" class="btn btn-default pull-right" onclick="alert('로그인이 필요한 서비스 입니다'); return false;">
						       	</c:when>
						       	<c:otherwise>
									<input type="button" value="글쓰기" class="btn btn-default pull-right" onclick="location.href='write?seq=1';">
						       	</c:otherwise>
							</c:choose>
							
							<c:choose>
								<c:when test="${memberNo eq board.memberNo}">
									<input type="button" value="수정하기" class="btn btn-warning pull-right" onclick="location.href='edit?no=${board.no}&context=${no}';">
									<input type="button" value="삭제하기" class="btn btn-danger pull-right board-delete" data-no="${board.no}" data-context="${no}">
								</c:when>
							</c:choose>
							<br>
						</div>
					</div>
					<!-- 버튼 위치 공간 끝 -->
					
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
					
					<!-- 댓글 작성 공간 시작 -->
					
					<div class="example-modal">
						<div class="modal modal-primary">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="input-group">
										<div class="modal-body">
											<textarea placeholder="댓글 입력" id="user-input${board.no}" name="detail" style="color: black; width:570px; height: 150px;" required></textarea>
										</div>
										
										<div class="modal-footer">
											<span class="input-group-btn">
												<c:choose>
													<c:when test="${not empty cookie.mynick.value}">
														<Button class="btn btn-info btn-flat board-comment" data-no="${board.no}" data-context="${no}">등록</Button>
													</c:when>
													<c:otherwise>
														<Button class="btn btn-info btn-flat" onclick="alert('로그인이 필요한 서비스 입니다'); return false;">등록</Button>
													</c:otherwise>
												</c:choose>
											</span>
										</div>
									</div>
								</div>
							</div>					
						</div>
					</div>
					
					<!-- 댓글 작성 공간 끝 -->
					
					
					
					
					<!-- 댓글 출력 공간 시작 -->
					
					<div class="example-modal">
						<div class="modal modal">
							<div class="modal-dialog">
								<div class="modal-content">
									<div id="comments${board.no}">
										<c:forEach var="comment" items="${list}">
											<c:if test="${comment.context eq board.no}">
												<div id="comment${comment.no}">
													<div class="modal-header"></div>
													<div class="modal-body">
															<div class="col-md-12">
																<ul class="timeline">
																	<li>
																		<div class="timeline-item">
																			<span class="time"><i class="fa fa-clock-o"></i>${comment.reg}</span>
														                    <h3 class="timeline-header"><a href="#">${comment.writer}(누르면 쪽지로 가게끔 하기)</a></h3>
														                    <div class="timeline-body">
														                    	${comment.detail}
														                    </div>
														                    <div class='timeline-footer'></div>
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
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</div>
		
					<!-- 댓글 출력 공간 끝 -->
		
		
				</div>
			</div>
		
		</c:forEach>
	</div>
</section>
	
<%@ include file="/WEB-INF/view/template/footer.jsp" %>