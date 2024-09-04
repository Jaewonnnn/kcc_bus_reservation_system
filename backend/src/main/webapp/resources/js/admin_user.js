$(function () {

    function myMemberList() {
        $.ajax({
            url : '/admin/users',
            type : 'GET',
            success : function (data) {
                console.table(data)
                data.forEach(function(user) {
                    // 각 사용자에 대해 새로운 행을 생성
                    const tableRow = `
                        <tr>
                            <td id="user-id" data-user-id="${user.id}">${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.phoneNumber}</td>
                            <td>${user.email}</td>
                            <td>
                                <button
                                    type="button"
                                    class="btn btn-primary"
                                    data-bs-toggle="modal"
                                    data-bs-target="#staticBackdrop"
                                    style="
                                        background-color: #212954 !important;
                                        font: 400 20px Noto Sans KR;
                                        color: #f9fafc !important;
                                        width: 50px;
                                    ">
                                    수정
                                </button>
                            </td>
                        </tr>
                    `;

                    $("#table-body").append(tableRow)
                })
            }
        })
    }

    // 모달이 열릴 때, 클릭된 버튼의 해당 사용자 ID 가져오기
    $(document).on('click', 'button[data-bs-toggle="modal"]', function () {
        // 클릭된 버튼의 가장 가까운 tr 요소에서 user-id 값을 가져옵니다.
        let memberId = $(this).closest('tr').find('#user-id').text();

        // AJAX 요청으로 해당 사용자 정보를 가져옴
        $.ajax({
            url: `/admin/user/${memberId}`,
            type: 'GET',
            contentType: 'application/json',
            success: function (data) {
                //console.table(data);
                // 모달 내부의 input 필드에 값을 채움
                $('#staticBackdrop input[name="name"]').val(data.name);
                $('#staticBackdrop input[name="phoneNumber"]').val(data.phoneNumber);
                $('#staticBackdrop input[name="email"]').val(data.email);
                $('#saveChangesButton').data('user-id', memberId);
                $('#userDeleteButton').data('user-id', memberId);
            },
            error: function (error) {
                console.error('Error fetching user data:', error);
            }
        });
    });

    // 수정 버튼 클릭 시 사용자 정보 업데이트 (PATCH 요청)

    $('#saveChangesButton').on('click', function () {
        let memberId = $(this).data('user-id');
        const updatedUser = {
            name: $('#staticBackdrop input[name="name"]').val(),
            phoneNumber: $('#staticBackdrop input[name="phoneNumber"]').val(),
            email: $('#staticBackdrop input[name="email"]').val()
        };

        $.ajax({
            url: `/admin/user/${memberId}` ,
            type: 'PATCH',
            contentType: 'application/json',
            data: JSON.stringify(updatedUser),
            success: function (response) {
                // alert('사용자 정보가 성공적으로 업데이트되었습니다.');
                location.reload();
            },
            error: function (xhr, status, error) {
                // console.error('Error updating user:', error);
                // alert('사용자 정보 수정 중 오류가 발생했습니다.');
                location.reload();
            }
        });
    });

    $('#userDeleteButton').on('click',function () {
        let memberId = $(this).data('user-id');
        console.log(memberId)
        $.ajax({
            url: `/admin/user/delete/${memberId}` ,
            type: 'PATCH',
            contentType: 'application/json',
            success: function (response) {
                location.reload();
            },
            error: function (xhr, status, error) {
                 console.error('Error updating user:', error);
                location.reload();
            }
        });
    })


    myMemberList()

})
