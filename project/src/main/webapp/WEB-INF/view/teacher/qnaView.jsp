<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/view/template/teacherHeader.jsp" %>

<div class="row borN">
	<div class="col-xs-12">
		<div class="box box-primary">
			<div class="box-body no-padding">
			<h2>학생 Q&A</h2>
			
			<div class="table-responsive">
				<table id="example2" class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>질문자</th>
							<th>내용</th>
							<th>첨부파일</th>
							<th>등록일</th>
							<th>답변여부</th>
		                </tr>
					</thead>
					<tbody id="videoList">
						<c:forEach var="qna" items="${list}">
							<tr>
								<td>
									${qna.student}
								</td>
								<td>
									${qna.detail}
								</td>
								<td>
									<a href="#">${qna.filename}</a>
								</td>
								<td>
									${qna.reg}
								</td>
								<td>
									<c:choose>
										<c:when test="${qna.state eq 'false'}">
											<a href="" onclick="window.open('${pageContext.request.contextPath}/data/mail/send?nick=${qna.student}', '쪽지보내기', 'width=800, height=500'); return false;">답변하기</a>
										</c:when>
										<c:otherwise>
											답변 완료
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<Button class="btn btn-default pull-right" onclick="location.href='students${url}';">돌아가기</Button>
			</div>
			</div>
		</div>
	</div>
</div>

<br><br>
<div>
	<Button onclick="location.href='students${url}';">돌아가기</Button>
</div>

<%@ include file="/WEB-INF/view/template/teacherFooter.jsp" %>