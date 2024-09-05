let selectedScheduleId;
function submitFormData() {
// 폼 데이터 수집
    const route = document.getElementById("inputGroupSelectRoute").value;
    const days = Array.from(document.querySelectorAll("#dayCheckBox input[type='checkbox']:checked"))
        .map(checkbox => checkbox.nextElementSibling.textContent.trim());
    const departureHour = document.querySelector("#inputGroupSelectDeparture").value;
    const departureMinute = document.querySelector("#floatingInputDeparture").value;
    const arrivalHour = document.querySelector("#inputGroupSelectArrival").value;
    const arrivalMinute = document.querySelector("#floatingInputArrival").value;
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
function getFormData(button) {
    selectedScheduleId = button.closest('tr').children[0].innerText;
    const row = button.closest('tr');
    const scheduleIdCell = row.children[0];
    const scheduleId = scheduleIdCell.innerText;

    fetch(`http://localhost:8081/admin/schedule/${scheduleId}`)
        .then(response => response.json())
        .then(data => {
            console.log(data);

            const startTimeSelect = document.getElementById("startTimeSelecter");
            const departureHour = data.departureTime.substring(11, 13);

            for (let option of startTimeSelect.options) {
                if (option.value === departureHour) {
                    option.selected = true;
                    break;
                }
            }
            // change 이벤트 수동으로 트리거
            startTimeSelect.dispatchEvent(new Event('change'));

            const endTimeSelecter = document.getElementById("endTimeSelecter");
            const arrivalHour = data.arrivalTime.substring(11, 13);

            for (let option of endTimeSelecter.options) {
                if (option.value === arrivalHour) {
                    option.selected = true;
                    break;
                }
            }
            // change 이벤트 수동으로 트리거
            endTimeSelecter.dispatchEvent(new Event('change'));

            const companySelect = document.getElementById("companySelect-1");

            for (let option of companySelect.options) {
                if (option.value === data.companyName) {
                    option.selected = true;
                    break;
                }
            }
            // change 이벤트 수동으로 트리거
            companySelect.dispatchEvent(new Event('change'));

            document.getElementById("updateGroupSelectRoute").value = data.startName + " -> " + data.endName;
            document.getElementById("floatingUpdateArrival").value = data.arrivalTime.substring(14);
            document.getElementById("floatingUpdateDepature").value = data.departureTime.substring(14);
            document.getElementById("floatingUpdatePrice").value = data.price;

            const busSelect = document.getElementById('busSelect-1'); // 수정된 부분


            for (let option of busSelect.options) {
                if (option.value === data.busNumber) {
                    option.selected = true;
                    break;
                }
            }
            // change 이벤트 수동으로 트리거
            busSelect.dispatchEvent(new Event('change'));
        })
        .catch(error => console.error('Error:', error));
}
function deleteSchedule() {
    fetch(`http://localhost:8081/admin/schedule/delete/${selectedScheduleId}`, {
        method: "PATCH"
    })
        .then(response => {
            if (response.ok) {
                alert("성공적으로 삭제되었습니다!");
            } else {
                alert("삭제에 실패했습니다.");
            }
        })
}

