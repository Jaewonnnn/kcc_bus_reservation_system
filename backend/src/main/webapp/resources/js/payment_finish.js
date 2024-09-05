$(function () {
    $.ajax({
        url : '/check/payment/finish',
        method : "GET",
        data: {
            data: encodeURIComponent(JSON.stringify(data)) // URL 인코딩
        },
        success : function (data) {
            console.log(data);
        },
        error : function (xhr, status, error) {
            console.log(xhr);
            console.log(status);
            console.log(error);
        }

    })
})