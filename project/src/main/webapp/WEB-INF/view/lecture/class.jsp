<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<style>
	        .wrap{
            width:850px;
            margin: auto;
            margin-top: 100px;
        }
        
           .left{
                display: inline-block;
                width:15%
            }
            .right{
                display: inline-block;
                width: 77%;
            }
            .row{
                display: block;
                width:100%;
                height: 100%;
                margin:20px 0px;
                padding:20px;
                font-size:20px;
            }
        .borN{
            border: none;
        }
        .textarea{
            width: 100%;
            height: 200;
            resize: none;
        }
</style>
<title>Insert title here</title>
</head>
<body>
	<title>상세보기</title>
</head>
<body>
	<div class="wrap">
		<table width="800" class="borN">
			<tbody>
				<tr>
					<td rowspan="2" width="20%">image</td>
                    <td><input type="text" class="row" placeholder="[수업태그]//[제목]"></td> 
				</tr>
				
				<tr>
                     <td><input type="text" class="row" placeholder="자기어필"></td>
				</tr>
 
                <tr>
                    <td colspan="2"><textarea class="textarea" placeholder="강의 내용"></textarea></td>
                </tr>
			</tbody>
		</table>	
        
        <div class="left" align="left">
        <a href="#">강사 정보</a>
        </div>
        <div class="right" align="right">
        <a href="#">신청하기</a>
        <a href="#">목록보기</a>
        </div>
	</div>

</body>
</html>
</body>
</html>