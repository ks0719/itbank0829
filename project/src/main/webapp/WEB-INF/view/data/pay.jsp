<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
      <script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.2.js"></script>
     <script type="text/javascript">
     var IMP = window.IMP; // 생략가능
     var redirect=null;
     IMP.init('imp03522546'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
     IMP.request_pay({
         pg : 'inicis', // version 1.1.0부터 지원. 
         pay_method : 'card',
         merchant_uid : 'merchant_' + new Date().getTime(),
         name : '포인트 충전(${point}p)',
         amount : '${money}',
         buyer_email : 'iamport@siot.do',
         buyer_name : '${list.name}',
         buyer_tel : '${list.phone}',
         buyer_addr : '${list.addr1} ${list.addr2}',
         buyer_postcode : '${list.post}',
         m_redirect_url : '${pageContext.request.contextPath}/data/complate'
     }, function(rsp) {
         if ( rsp.success ) {
             var msg = '결제가 완료되었습니다.';
             msg += '고유ID : ' + rsp.imp_uid;
             msg += '상점 거래ID : ' + rsp.merchant_uid;
             msg += '결제 금액 : ' + rsp.paid_amount;
             msg += '카드 승인번호 : ' + rsp.apply_num;
            redirect='${pageContext.request.contextPath}/data/complate';
         } else {
             var msg = '결제에 실패하였습니다. 다시 시도해주세요';
             msg += '에러내용 : ' + rsp.error_msg;
             redirect='${pageContext.request.contextPath}/data/point';
         }
         alert(msg);
         document.location.href=redirect;
     });
</script>
<html>
<head>
<title>Insert title here</title>
</head>
<body>

</body>
</html>