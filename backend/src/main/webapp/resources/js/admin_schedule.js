function submitFormData() {
    // 폼 데이터 수집
    const route = document.getElementById("inputGroupSelectRoute").value;
    const days = Array.from(document.querySelectorAll("#dayCheckBox input[type='checkbox']:checked"))
        .map(checkbox => checkbox.nextElementSibling.textContent.trim());
    const departureHour = document.querySelector("#inputGroupSelectDeparture").value;
    const departureMinute = document.querySelector("#floatingInputDeparture").value;
    const arrivalHour = document.querySelector("#inputGroupSelectArrival").value; // 수정된 부분
    const arrivalMinute = document.querySelector("#floatingInputArrival").value; // 수정된 부분
    const company = document.getElementById("companySelect").value;
    const busNumber = document.getElementById("busSelect").value;
    const price = document.getElementById("floatingInputPrice").value;

    // POST 요청에 사용할 데이터
    const data = {
        route: route, // 수정된 부분: route를 routeId로 변경
        days: days,
        departureTime: `${departureHour}:${departureMinute}`,
        arrivalTime: `${arrivalHour}:${arrivalMinute}`,
        companyName: company,
        busNumber: busNumber,
        price: price
    };

    // 서버로 데이터를 전송하는 비동기 POST 요청
    fetch('http://localhost:8081/admin/schedule', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
        .then(response => {
            if (response.ok) {
                alert("성공적으로 저장되었습니다!");
            } else {
                alert("저장 중 오류가 발생했습니다.");
            }
        })
        .catch(error => {
            console.error("에러 발생:", error);
            alert("저장 중 에러가 발생했습니다.");
        });
}
