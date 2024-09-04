/** 특정 년 월 일에서 요일을 구하는 함수 */
function getDayOfWeek(year, month, day) {
    let date = new Date(year, month-1, day);
    let option = {weekday : 'long'};
    return date.toLocaleDateString('ko-KR', option);
}


$(document).ready(function () {
    $.ajax({
        url : '/reservation/'+57,
        method : "GET",
        ContentType: "application/json",
        success: function(data) {
            console.log(data);
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
                let html = `<div>
                            <input type='hidden' data-bus-id='${data.busId}'>
                            <input type='hidden' data-route-id='${data.routeId}'>
                            </div>`;
                $('body').append(html);
        }
    })
});


/** 카카오 페이 결제 */
$(function () {
    /** 결제할 때 필요한 요소들 */
    var IMP = window.IMP;
    IMP.init("imp16376821");

    var today = new Date();

    var hours = today.getHours(); // 시

    var minutes = today.getMinutes();  // 분

    var seconds = today.getSeconds();  // 초
    var milliseconds = today.getMilliseconds();
    var makeMerchantUid = hours + minutes + seconds + milliseconds;


    /** 카카오페이 결제 */
    function kakaoPay() {
        IMP.request_pay({
            pg: 'kakaopay',
            pay_method: 'card',
            merchant_uid: makeMerchantUid,
            name: '안녕',
            amount: '20000',
            buyer_email: 'dnjstmddjs12@naver.com',
            buyer_tel: '010-2572-4233',
            buyer_addr: '경기도',
            buyer_name: "원승언"
        }, function (rsp) { // callback
            if (rsp.success) {
                let msg = "결제가 완료되었습니다.";
                let result = {
                    "pg": "rsp.pg",
                    "pay_method": "rsp.pay_method"
                }
                alert(msg);

                $.ajax({
                    url: 'payment.jsp',
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
