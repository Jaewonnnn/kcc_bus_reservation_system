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