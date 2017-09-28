<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/view/template/header.jsp" %> 
<html>
<head>
<title>아이디찾기 결과</title>
</head>
<body>
	<h1>아이디 결과</h1>
	<div class="page-wrap">
	<div class="table-wrap">
		<table border="1" class="tableUnit" rules=rows>
			<tr>
				<th>
					아이디
				</th>
			</tr>
			<tr>
				<td>
					${findidcheck }
				</td>
		</table>
	</div>
</body>
</html>
<%@ include file="/WEB-INF/view/template/footer.jsp" %> 