<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav id="nav" style="margin-top: 15px;'">
						<ul>
							<li><a href="${pageContext.request.contextPath}/data/edit"
								class="topmenu">내 정보 변경</a></li>
							<li><a
								href="${pageContext.request.contextPath }/data/changepw"
								class="topmenu">비밀번호 변경</a></li>
							<li><a href="" class="topmenu"
								onclick="window.open('${pageContext.request.contextPath}/data/manageLecture?box=index', '수강관리', 'width=1000, height=500'); return false;">내
									수강정보</a></li>
							<li><a href="${pageContext.request.contextPath }/data/point"
								class="topmenu">포인트 샾</a></li>
							<li><a
								href="${pageContext.request.contextPath}/member/deletemember"
								class="topmenu">회원 탈퇴</a></li>
<!-- 							<li><a href="#" class="topmenu">내가 쓴글</a></li> -->
							<c:choose>
								<c:when test="${isTeacher eq 'true'}">
									<li><a
										href="${pageContext.request.contextPath}/teacher/profile"
										class="topmenu">강사 전용</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="#" id="lecturer-apply" value="${dto.nick}"
										class="topmenu">강사 신청</a></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</nav>