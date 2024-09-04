$(function(){
    const memberId = window.location.pathname.split('/')[3]

    function myReservationList(memberId){
        $.ajax({
            url : `/check/reservation/data/${memberId}`,
            type: 'GET',
            success: function (data){

                //console.table(data)
                if(data){
                    data.forEach(item=>{
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
                        <td class="text-center">${item.startTime}</td>
                        <td class="text-center">${item.reservationCount}매</td>
                        <td class="text-center">${item.totalPrice.toLocaleString()}원</td>
                        <td class="text-center">
                          <button class="detail_btn" data-bs-toggle="modal" data-bs-target="#staticBackdrop">상세보기</button>
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
        $.ajax({
            url: `/check/reservation/detail/${memberId}`,
            type: 'GET',
            success: function (data){
                //console.table(data)
                $('#scheduleDate').text(data[0].scheduleDate)
                $('#startTerminalName').text(data[0].startTerminalName)
                $('#endTerminalName').text(data[0].endTerminalName)
                $('#startTime').text(data[0].startTime)
                $('#dataLength').text(data.length+'매')
                var seatNumbers = data.map(item => item.seatNumber + '석').join(', ');
                $('#seatNumber').text(seatNumbers)
                $('#totalPrice').text(data[0].totalPrice.toLocaleString()+'원')



            }

        })
    })

    myReservationList(memberId)


})