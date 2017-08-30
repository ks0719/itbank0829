<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>강의 정보</title>
<style>
	div {
	    width: 700px;
	    margin:0px auto;
	}
	.table-wrap {
	    overflow: auto;
	}
	.study {
		border-collapse: collapse;
		width: 90%;
 		margin: 10px auto;
	}
	.title {
		width: 80%;
		padding: 10px;
	}
	.teacher {
		text-align: right;
		padding: 10px;
	}
	form {
		width: 90%;
		margin: 0px auto;
	}
	.empty-row{
	    display: block;
	    width:100%;
	    height:30px;
	}
	.user-input {
	    padding:10px;
	    font-size:15px;
	    outline:none;
	    border:1px solid black;
	}
	.input-btn {
	    padding:10px;
	    font-size:15px;
	    background-color: white;
	    color: black;
	}
</style>
</head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	$(document).ready(function() {
		$(".clickToinfo").on("click", function() {
			window.open('class', '수업정보', 'width=600, height=600, menubar=no');
		});
	});
</script>
<body>
	<div>
		<div class="table-wrap">
			<table border="1" class="study" rules=rows>
				<tr class="clickToinfo">
					<td class="title">
						[수업태그]
						[제목]
					</td>
					<td class="teacher">
						[강사정보]
					</td>
				</tr>
				<tr class="clickToinfo">
					<td class="title">
						[수업태그]
						[제목]
					</td>
					<td class="teacher">
						[강사정보]
					</td>
				</tr>
				<tr class="clickToinfo">
					<td class="title">
						[수업태그]
						[제목]
					</td>
					<td class="teacher">
						[강사정보]
					</td>
				</tr>
				<tr class="clickToinfo">
					<td class="title">
						[수업태그]
						[제목]
					</td>
					<td class="teacher">
						[강사정보]
					</td>
				</tr>
				<tr class="clickToinfo">
					<td class="title">
						[수업태그]
						[제목]
					</td>
					<td class="teacher">
						[강사정보]
					</td>
				</tr>
			</table>
		</div>
		<div class="empty-row"></div>
		<form action="study/study">
			<select name="type" class="user-input">
				<option value="">분류 선택</option>
				<option value="수업태그">수업태그</option>
				<option value="제목">제목</option>
				<option value="강사명">강사명</option>
			</select>
			<input type="search" name="key" class="user-input" placeholder="검색 내용" required>
			<input type="submit" value="검색" class="input-btn">
		</form>
	</div>
</body>
</html>