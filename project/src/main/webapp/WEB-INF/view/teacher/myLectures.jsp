<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%@ include file="/WEB-INF/view/template/teacherHeader.jsp" %>

<div class="row">
	<div class="col-xs-12">
		<div class="box box-primary">
			<div class="box-body no-padding">
				<c:choose>
                    <c:when test="${where eq 'myLecture'}">
                        <h2>내 강의 관리</h2>
                    </c:when>
                    <c:otherwise>
                        <h2>학생 관리</h2>
                    </c:otherwise>
                </c:choose>
                <br>
			<div class="table-responsive">
			
			
				<table id="example2" class="table table-bordered table-hover">
					<thead>
						<tr>
                            <th>태그</th>
                            <th class="title">강의제목</th>
                            <th>가격</th>
                            <th>강의시간</th>
                            <th>강의기간</th>
                            <th>상태</th>
		                </tr>
					</thead>
					<tbody>
						<c:forEach var="info" items="${list}">
                        <tr data-no="${info.no}" data-page="${page}" data-search="${search}" data-key="${key}" data-where="${where}" class="toMyLecture" style="cursor: pointer">
                            <td>[${info.tag}]</td>
                            <td class="title">${info.title}</td>
                            <td>${info.price}</td>
                            <td>${info.time}</td>
                            <td>${info.period}</td>
                            <td>${info.accept}</td>
                        </tr>
                        </c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="7" style="text-align:center;">
								<c:if test="${startBlock > 1}">
	                                <button class="btn btn-default btn-sm" onclick="location.href='${url}&page=${startBlock - 1}'"><i class="fa fa-chevron-left"></i></button>
	                            </c:if>

	                            <c:forEach var="i" begin="${startBlock}" end="${endBlock}" step="1">
	                                <c:choose>
	                                    <c:when test="${i eq page}">
	                                        <button class="btn btn-flat btn-sm">${i}</button>
	                                    </c:when>
	                                    <c:otherwise>
	                                        <button class="btn btn-default btn-sm" onclick="location.href='${url}&page=${i}'">${i}</button>
	                                    </c:otherwise>
	                                </c:choose>
	                            </c:forEach>
	
	                            <c:if test="${endBlock < blockTotal}">
	                                <button class="btn btn-default btn-sm" onclick="location.href='${url}&page=${endBlock + 1}'"><i class="fa fa-chevron-right"></i></button>
	                            </c:if>
							</td>
						</tr>
						<tr>
							<td colspan="7" style="text-align:center;">
								<div class="form-group">
									<form action="myLectures" class="wrap">
                                        <input type="hidden" name="page" value="1">
                                        <select name="search" title="분류 선택" class="form-control" style="display: inline; width: 13%;">
                                            <option value="tag">태그</option>
                                            <option value="title">제목</option>
                                        </select>
                                        <input type="search" name="key" class="form-control" style="display: inline; width: 35%;" placeholder="검색 내용" value="${key}" required>
                                        <input type="submit" value="검색" class="btn btn-primary">
                                    </form>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			    
			  </div>  
			    
			</div>
		</div>
		
	</div>
</div>

<%@ include file="/WEB-INF/view/template/teacherFooter.jsp" %>