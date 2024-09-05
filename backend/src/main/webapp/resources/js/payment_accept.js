$(function () {
    // 페이지 로드 시 URL에서 쿼리 매개변수 파싱
    function parseQueryParams() {
        const queryString = window.location.search;
        const urlParams = new URLSearchParams(queryString);

        const seatIds = urlParams.get('seatIds');
        const passengerCount = urlParams.get('passengerCount');
        const totalAmount = urlParams.get('totalAmount');
        const busGrade = urlParams.get('busGrade');
        const startTerminal = urlParams.get('startTerminal');
        const endTerminal = urlParams.get('endTerminal');
        const busCompany = urlParams.get("busCompany")
        const adults = urlParams.get("adults");
        const students = urlParams.get("students");
        const children = urlParams.get("children");
        const scheduleId = urlParams.get("scheduleId")

        return { seatIds, passengerCount, totalAmount, busGrade, startTerminal, endTerminal, busCompany, adults, students, children, scheduleId };
    }

    // 파싱한 데이터를 저장
    const queryParams = parseQueryParams();
    console.log(queryParams);  // 콘솔에 파싱 결과를 출력

    // 버튼 클릭 시 지정된 페이지로 파싱한 쿼리 매개변수와 함께 이동
    $(".button2").on('click', function () {
        // URL을 생성하면서 모든 쿼리 매개변수를 포함
        const newUrl = `/check/payment?seatIds=${encodeURIComponent(queryParams.seatIds)}
        &passengerCount=${queryParams.passengerCount}&totalAmount=${queryParams.totalAmount}&busGrade=${encodeURIComponent(queryParams.busGrade)}&startTerminal=${encodeURIComponent(queryParams.startTerminal)}
        &endTerminal=${encodeURIComponent(queryParams.endTerminal)}&busCompany=${queryParams.busCompany}&adults=${queryParams.adults}&students=${queryParams.students}&children=${queryParams.children}&scheduleId=${queryParams.scheduleId}`;
        window.location.href = newUrl;
    });
});
