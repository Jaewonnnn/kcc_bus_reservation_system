/** 특정 년 월 일에서 요일을 구하는 함수 */
function getDayOfWeek(year, month, day) {
    let date = new Date(year, month-1, day);
    let option = {weekday : 'long'};
    return date.toLocaleDateString('ko-KR', option);
}

/** 승차권 배정을 위한 조회와 결제를 위한 함수 */
$(document).ready(function () {

    $.ajax({
        url : '/check/'+57,
        method : "GET",
        ContentType: "application/json",
        success: function(data) {
               let price = parseInt(data.schedulePrice).toLocaleString();
               let year = data.scheduleStartDate.split("-").at(0);
               let month = data.scheduleStartDate.split("-").at(1);
               let day = data.scheduleStartDate.split("-").at(2);
               let weekText = data.scheduleStartDate + ' ' + getDayOfWeek(year,month,day);         // 해당 날짜의 요일 구한 값
                $('#startTerminal').text(data.startTerminal);
                $('#endTerminal').text(data.endTerminal);
                $('#companyName').text(data.companyName);
                $('#busGradeName').text(data.busGradeName);
                $('#schedulePrice').text(data.schedulePrice);
                $('#scheduleStartDate').text(weekText);
                $('#schedulePrice').text(price);
                $('#price').text(price);
        }
    })
});

$(function () {

    /** 결제할 때 필요한 요소들 */
    let IMP = window.IMP;

    /** 카카오 페이 결제 */
    IMP.init("imp16376821");

    let today = new Date();

    let hours = today.getHours(); // 시

    let minutes = today.getMinutes();  // 분
    let seconds = today.getSeconds();  // 초
    let milliseconds = today.getMilliseconds();



    let makeMerchantUid = hours + minutes + seconds + milliseconds;
    /** 카카오페이 결제 */
    function kakaoPay() {
        IMP.request_pay({
            pg: 'kakaopay',
            pay_method: 'card',
            merchant_uid: makeMerchantUid,
            name: 'UNIBUS TICKET PRICE',
            amount: price,
            buyer_email: '${memberId.memberEmail}',
            buyer_tel: '${delivery.receiverTel}',
            buyer_addr: '${delivery.deliveryAddress}',
            buyer_name : "${memberId.memberName}"
        }, function (rsp) { // callback
            if (rsp.success) {
                console.log(rsp);
                let msg = "결제가 완료되었습니다.";
                let result = {
                    "pg": "rsp.pg",
                    "pay_method": "rsp.pay_method"
                }
                alert(msg);

                $.ajax({
                    url: '/check/payment',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(result),
                    success: function (order) {
                        console.log(order);
                    },
                    error: function (err) {
                        console.log(err);
                    }
                })


            }
        });
    }
    $('#kakaoPayment').on('click', kakaoPay);
})


$(function () {
    /** 나이스 페이 결제할 때 */
    function nicePay() {
        IMP.request_pay({
            pg: 'nice',
            pay_method: 'card',
            merchant_uid: makeMerchantUid,
            name: 'PageFlow 결제',
            amount: '${cartList[cartList.size()- 1].totalAmount}',
            buyer_email: '${memberId.memberEmail}',
            buyer_tel: '${delivery.receiverTel}',
            buyer_addr: '${delivery.deliveryAddress}',
            buyer_name : "${memberId.memberName}"
        }, function(rsp) { // callback
            if (rsp.success) {

                alert(rsp);
                let msg = "결제가 완료되었습니다.";


                let pointUsed = parseInt($('#myNumberInput').val(), 10) || 0;  // 사용한 포인트 금액을 숫자로 변환하고, 값이 없으면 0을 설정합니다.
                let orderPrice = rsp.paid_amount - pointUsed;  // 주문 금액에서 사용한 포인트 금액을 뺍니다.
                let result = {
                    "memberId": "${memberId.memberId}",
                    "dno": dno,
                    "orderPrice": orderPrice,  // 최종 결제 금액을 수정합니다.
                    "pointEarn": 0,
                    "payment": rsp.pg_provider,
                    "ordersBuyer": rsp.buyer_name,
                    "pointUsed": $('#myNumberInput').val()  // 사용자가 입력한 포인트 사용량을 추가합니다.
                }
                alert(msg);


                $.ajax({
                    url: '/order',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        orderPrice: finalPrice,
                        pointUsed: $('#myNumberInput').val(),
                        pointEarn: $('#myNumberInput').val() > 0 ? 0 : '${cartList[cartList.size()- 1].totalPointEarnings}'
                    }),
                    success: function(order) {
                        location.replace('/order/success?ono=' + order.ono);
                    },
                    error: function(err){
                        console.log(err);
                    }
                })

            }
        });

    }
    $('#nicePayment').on('click', nicePay);
})


