<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/view/template/header.jsp"%>


<div class="row">
	<div class="col-xs-12">
		<div class="box box-primary">
			<div class="box-body no-padding">
			
			<div class="table-responsive">
		<table border="1" class="table table-bordered table-hover" style="width: 100%;"> 
		
		<thead>
		<tr>
				<th>
					[번호]
				</th>
				<th>
					[문의유형]
				</th>
				<th>
					[제목]
				</th>
				<th>
					[작성시간]
				</th>
				<th>
					[진행상태]
				</th>
			</tr>
			</thead>
			
			
			<tbody>
		<c:forEach var="list" items="${list }">
			<tr style="cursor: pointer" onclick="location.href='${pageContext.request.contextPath}/consumer/detail?no=${list.no }'">
				<th>
					${list.rn }
				</th>
				<th >
					${list.type }
				</th>
				<th>
					${list.title }
				</th>
				<th >
					${list.reg }
				</th>
				<th >
					${list.state }
				</th>
			</tr>
			</c:forEach>
			</tbody>
			
			
			
		</table>
		  </div>  
			    
			</div>
		</div>
		
	</div>
</div>
<%@ include file="/WEB-INF/view/template/footer.jsp"%>