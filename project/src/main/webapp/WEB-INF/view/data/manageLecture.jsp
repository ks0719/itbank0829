<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>수강 관리</title>
</head>
<body>

	<table>
		<tr>
			<th rowspan="5">
                <h3><a href="${pageContext.request.contextPath}/data/manageLecture?box=all">전체 수강 내역</a></h3>
                <h3><a href="${pageContext.request.contextPath}/data/manageLecture?box=index">진행중인 강의</a></h3>
                <h3><a href="${pageContext.request.contextPath}/data/manageLecture?box=comp">수료한 강의</a></h3>
                <h3><a href="${pageContext.request.contextPath}/data/manageLecture?box=eval">수강평가</a></h3>
                <h3><a href="${pageContext.request.contextPath}/data/manageLecture?box=wish">찜한 강의</a></h3>
			</th>
            <td style="vertical-align: top">
                <table>
                	<tr>
                        <th>분류</th>
                        <th>강사</th>
                        <th>과목명</th>
                        <th>수강시간</th>
                        <th>수강형태</th>
                        <th>수강상태</th>
                        <th>결제금액</th>
                        <th></th>
                    </tr>
                    <tr>
                        <td colspan="8">
                            	리스트 찍을 공간
                        </td>
                    </tr>
                </table>
            </td>
		</tr>
	</table>
	
	<h1>${param.box}</h1>
</body>
</html>