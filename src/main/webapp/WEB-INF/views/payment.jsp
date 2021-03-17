<%--
  Created by IntelliJ IDEA.
  User: 82102
  Date: 2021-03-08
  Time: 오후 4:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>

<script type="javascript">
    var IMP = window.IMP; // 생략해도 괜찮습니다.
    IMP.init("imp18833325"); // "imp00000000" 대신 발급받은 "가맹점 식별코드"를 사용합니다.

    // IMP.request_pay(param, callback) 호출
    IMP.request_pay({ // param
        pg: "html5_inicis",
        pay_method: "card",
        merchant_uid: "ORD20180131-0000011",
        name: "노르웨이 회전 의자",
        amount: 64900,
        buyer_email: "gildong@gmail.com",
        buyer_name: "홍길동",
        buyer_tel: "010-4242-4242",
        buyer_addr: "서울특별시 강남구 신사동",
        buyer_postcode: "01181"
    }, function (rsp) { // callback
        if (rsp.success) {
            // todo 1. 결제 성공 시 로직
        } else {
            // todo 2. 결제 실패 시 로직
        }
    });
</script>