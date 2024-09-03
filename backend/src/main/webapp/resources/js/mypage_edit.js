/** 유저 정보 변경시키기 */
$(function () {
    $('.change_member_btn').click(function () {
        let memberName = $('#memberName').val() || $('#memberName').attr('placeholder');
        let firstTel = $('#firstTel').val() || $('#firstTel').attr('placeholder');
        let middleTel = $('#middleTel').val() || $('#middleTel').attr('placeholder');
        let lastTel = $('#lastTel').val() || $('#lastTel').attr('placeholder');
        let memberTel = firstTel + '-' + middleTel + '-' + lastTel;

        let firstEmail = $('#firstEmail').val() || $('#firstEmail').attr('placeholder');
        let lastEmail = $('#lastEmail').val() || $('#lastEmail').attr('placeholder');
        let memberEmail = firstEmail + '@' + lastEmail;

        let memberId = $('#memberId').text();
        if(confirm("정말로 정보를 변경하시겠습니까?")) {
            $.ajax({
                url: "/user/"+memberId,
                method: "PATCH",
                contentType: "application/json",
                data : JSON.stringify({
                    memberName : memberName,
                    memberTel : memberTel,
                    memberEmail : memberEmail
                }),
                success : function () {
                    alert('회원 변경에 완료하였습니다.');
                    location.href='/user/mypage'
                }
            })
        } else {
            return false;
        }
    })
})


/** 비밀번호 수정 */
$(function () {
    $('.change_btn').click(function () {
        if(confirm('정말로 비밀번호를 변경하시겠습니까?')) {
            $('.change_btn').addClass('hide');
            $('.input_hide_area').addClass('show');

            $('.change_password_btn').click(function() {
                let memberId = $('#memberId').text();
                let newPass = $('#hide_password_input').val();
                if(newPass === '') {
                    alert('비밀번호를 입력해주세요!');
                } else {
                    $.ajax({
                        url : '/user/memberPass',
                        method: "PATCH",
                        contentType: "application/json",
                        data : JSON.stringify({
                            memberId : memberId,
                            memberPass : newPass
                        }),

                        success: function () {
                            alert('비밀번호 변경을 완료했습니다.');
                            location.href='/user/mypage';
                        },
                        error: function (xhr, status, error) {
                            console.log('Error:', xhr.responseText);
                            alert('실패: ' + xhr.responseText); // 실패 메시지
                        }
                    })
                }
            })
        }
    })

})

/** 회원 탈퇴 */
$(function () {
    $('.password_check_btn').on('click', function() {
        let memberId = $('#memberId').text();
        let pass = $('#password_check').val();
        if(confirm("정말로 탈퇴하시겠습니까? 탈퇴하시면 모든 정보는 다시 복구할 수 없습니다.")) {
            $.ajax({
                url : '/user/withdraw',
                method : 'POST',
                contentType: 'application/json', // JSON 형식으로 전송
                data : JSON.stringify({
                    memberId : memberId,
                    memberPass : pass
                }),
                success : function (data) {
                    alert('회원 탈퇴가 완료되었습니다.');
                    let form = $('<form action="/user/logout" method="post"></form>');
                    $('body').append(form);
                    form.submit();

                },
                error: function (xhr, status, error) {
                    console.log('Error:', xhr.responseText);
                    console.log(status);
                    console.log(error)
                    alert('실패: ' + xhr.responseText); // 실패 메시지
                }
            });
        } else {
            return;
        }
    });
});
