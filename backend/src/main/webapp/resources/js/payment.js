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
                 $('#schedule_id').val(data.scheduleId);

        }
    })
});

$(function () {

    /** 결제할 때 필요한 요소들 */
    let IMP = window.IMP;

    /** 카카오 페이 결제 */
    IMP.init("imp16376821");

    let today = new Date();
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
            name: 'PAYMENT UNIBUS',
            amount: parseInt($('#price').text().replace(/,/g,'') ,10),
            buyer_email: 'dnjstmddjs12@naver.com',
            buyer_tel: '010-2572-4233',
            buyer_addr: '경기도 시흥시',
            buyer_name: "원승언"
        }, function (rsp) { // callback
            if (rsp.success) {
                let msg = "결제가 완료되었습니다.";
                alert(msg);

                let count = parseInt($('.count').text());
                let payment = {
                    paymentImpUid: makeMerchantUid,
                    paymentProvider: rsp.pg_provider,
                    paymentMethod: "card"
                };
                console.log(payment);

                let countTicket = $('#seat_number').text().split(",").length;

                let reservation = {
                    price: rsp.paid_amount / countTicket,
                    seatNumber : $('#seat_number').text(),
                    scheduleId : parseInt($('#schedule_id').val())
                }
                console.log(reservation);

                let memberId = $('#memberId').val();

                let nonMember = {
                        nonUserTel : '010-2572-4233'
                }
                console.log(nonMember);

                let result = {
                    payment: payment,
                    reservation: reservation,
                    nonMember: nonMember
                };
                console.log(result);
                console.log('result:', JSON.stringify(result));


                $.ajax({
                    url: '/check/payment/' + memberId,
                    method: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(result),
                    success: function (ticket) {
                            console.log(ticket);

                            location.href = `/check/reservation/finish/` + ticket.paymentImpUid;



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

    /** 결제할 때 필요한 요소들 */
    let IMP = window.IMP;
    IMP.init("imp16376821");

    let today = new Date();
    var hours = today.getHours(); // 시
    var minutes = today.getMinutes();  // 분
    var seconds = today.getSeconds();  // 초
    var milliseconds = today.getMilliseconds();
    var makeMerchantUid = hours + minutes + seconds + milliseconds;
    /** 일반 결제 */
    function nicePay() {
        IMP.request_pay({
            pg: 'nice',
            pay_method: 'card',
            merchant_uid: makeMerchantUid,
            name: 'PAYMENT UNIBUS',
            amount: parseInt($('#price').text().replace(/,/g,'') ,10),
            buyer_email: 'dnjstmddjs12@naver.com',
            buyer_tel: '010-2572-4233',
            buyer_addr: '경기도 시흥시',
            buyer_name: "원승언"
        }, function (rsp) { // callback
            if (rsp.success) {
                let msg = "결제가 완료되었습니다.";
                alert(msg);

                let count = parseInt($('.count').text());
                let payment = {
                    paymentImpUid: makeMerchantUid,
                    paymentProvider: rsp.pg_provider,
                    paymentMethod: "card"
                };
                console.log(payment);

                let countTicket = $('#seat_number').text().split(",").length;

                let reservation = {
                    price: rsp.paid_amount / countTicket,
                    seatNumber : $('#seat_number').text(),
                    scheduleId : parseInt($('#schedule_id').val())
                }
                console.log(reservation);

                let memberId = $('#memberId').val();

                let nonMember = {
                    nonUserTel : '010-2572-4233'
                }
                console.log(nonMember);

                let result = {
                    payment: payment,
                    reservation: reservation,
                    nonMember: nonMember
                };
                console.log(result);
                console.log('result:', JSON.stringify(result));


                $.ajax({
                    url: '/check/payment/' + memberId,
                    method: 'POST',
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
    $('#nicePayment').on('click', nicePay);

})


