$(document).ready(function () {
    $('#memberForm').on('submit', function (event) {
        event.preventDefault(); // 기본 폼 제출 동작 방지

        // 폼 데이터 수집
        var formData = {
            id: $('input[name="id"]').val(), // hidden input에서 ID 가져오기
            pass: $('#password_check').val() // 비밀번호 입력값 가져오기
        };

        // AJAX 요청
        $.ajax({
            url: '/user/memberPass', // 요청을 보낼 URL
            method: 'POST',
            contentType: 'application/json', // JSON 형식으로 전송
            data: JSON.stringify(formData), // 객체를 JSON 문자열로 변환
            success: function (response) {
                alert('비밀번호 검증 성공'); // 성공 메시지
                location.href="/user/mypage/edit";
            },
            error: function (xhr, status, error) {
                console.log('Error:', xhr.responseText);
                alert('비밀번호 검증 실패: ' + xhr.responseText); // 실패 메시지
            }
        });
    });
});
