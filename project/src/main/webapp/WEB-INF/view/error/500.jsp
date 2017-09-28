<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp" %>   

<section class="content">
	<div class="error-page">
	  <h2 class="headline text-red">500</h2>
	  <div class="error-content">
	    <h3><i class="fa fa-warning text-red"></i>서버 오류가 발생했습니다</h3>
		    <p>계속 같은현상이 발생하면 고객센터로 문의주세요</p>
		    <p>이전 페이지로 돌아가시려면 <a href='javascript:history.back()'>뒤로가기</a>를 눌러주세요</p>
	  </div>
	</div><!-- /.error-page -->
</section>
<%@ include file="/WEB-INF/view/template/footer.jsp" %>