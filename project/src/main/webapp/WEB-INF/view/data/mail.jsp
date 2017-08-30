<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<script src="http://code.jquery.com/jquery-latest.js"></script>

<script>
	$(document).ready(function() {
		$(".mail").on("click", function() {
			window.open('${pageContext.request.contextPath}/data/mail/mailDetail', '', 'width=500, height=500, menubar=no');
		});
	});
</script>

<head>
<title>쪽지함</title>
</head>
<body>
	<table>
		<tr>
			<th rowspan="5">
                <h3><a href="${pageContext.request.contextPath}/data/mail?box=index">받은 쪽지함</a></h3>
                <h3><a href="${pageContext.request.contextPath}/data/mail?box=sent">보낸 쪽지함</a></h3>
                <h3><a href="${pageContext.request.contextPath}/data/mail?box=spam">스팸 쪽지함</a></h3>
                <h3><a href="${pageContext.request.contextPath}/data/mail?box=protect">보관함</a></h3>
                <h3><a href="${pageContext.request.contextPath}/data/mail?box=garbage">휴지통</a></h3>
			</th>
			
			
			
            <td style="vertical-align: top">
                <table>
                    <tr>
                        <td colspan="8">
                            <button>삭제하기</button>
                            <button>쪽지쓰기</button>
                            <button>보관하기</button>
                        </td>
                    </tr>
                    <tr>
                        <th><input type="checkbox"></th>
                        <th>아이디</th>
                        <th>분류</th>
                        <th>제목</th>
                        <th>내용</th>
                        <th>보낸날짜</th>
                        <th>읽기여부</th>
                    </tr>
                    <tr class="mail">
                        <td>메일1</td>
                    </tr>
                    <tr class="mail">
                        <td>메일2</td>
                    </tr>
                    <tr class="mail">
                        <td>메일3</td>
                    </tr>
                </table>
            </td>
		</tr>
	</table>
	
	<h1>${param.box}</h1>
</body>
</html>