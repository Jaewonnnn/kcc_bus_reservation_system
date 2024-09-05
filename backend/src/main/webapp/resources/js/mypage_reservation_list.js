$(function(){
    const memberId = window.location.pathname.split('/')[3]

    function myReservationList(memberId){
        $.ajax({
            url : `/check/reservation/data/${memberId}`,
            type: 'GET',
            success: function (data){

                console.log(data);
                if(data){
                    data.forEach(item=>{
                        let startTime = (item.startTime).substring(0,5);
                        let tableRow = `<tr >
                        <th scope="row" class="text-center">
                          <input
                            class="form-check-input"
                            type="checkbox"
                            value=""
                            id="flexCheckDefault"
                          />
                        </th>
                        
                        <td class="text-center">${item.scheduleDate}</td>
                        <td class="text-center">${item.startTerminalName}</td>
                        <td class="text-center">${item.endTerminalName}</td>
                        <td class="text-center">${startTime}</td>
                        <td class="text-center">${item.reservationCount}매</td>
                        <td class="text-center">${item.totalPrice.toLocaleString()}원</td>
                        <td class="text-center">
                          <button class="detail_btn" data-bs-toggle="modal" data-bs-target="#staticBackdrop" data-payment-id="${item.paymentImpUid}">상세보기</button>
                        </td>
                      </tr>`
                        $('#my-reservation-list').append(tableRow)


                    })
                }
            }, error: function (err) {
                console.log('쿼리문을 확인해보자' + err)
            }

        })
    }

    // 모달 구현 - 예메 상세보기
    $(document).on('click', 'button[data-bs-toggle="modal"]', function () {
        let paymentImpUid = $(this).data('payment-id');
        console.log(paymentImpUid);
        $.ajax({
            url: `/check/reservation/detail/${paymentImpUid}/${memberId}`,
            type: 'GET',
            success: function (data){


                $('#scheduleDate').text(data.scheduleDate)
                $('#startTerminalName').text(data.startTerminalName)
                $('#endTerminalName').text(data.endTerminalName)
                $('#startTime').text(data.startTime)
                $('#dataLength').text(data.seatNumber.length+'매')
                var seatNumbers = data.seatNumber ;
                $('#seatNumber').text(seatNumbers)
                $('#totalPrice').text(data.totalPrice.toLocaleString() + '원')


            }

        })
    })

    myReservationList(memberId)


})