
const prices = {
    adult: 10000,
    student: 8000,
    child: 5000
};

let selectedSeats = []; //
let passengers = {
    adults: 0,
    students: 0,
    children: 0
};

let reservedSeats = [];
const memberId = window.location.pathname.split('/')[4]
console.log(memberId)
// fetch
fetch(`/reservation/busNumber/`+memberId)
    .then(response => response.json())
    .then(seatNumbers => {
        console.log(seatNumbers)
        reservedSeats = seatNumbers.map(item => parseInt(item.seatNumber));
        updateSeatAvailabilityOnInit();
        setupSeatClickHandlers();
        // 출발지와 도착지를 동적으로 설정
        const startTerminalName = seatNumbers[0].startTerminalName;
        const endTerminalName = seatNumbers[0].endTerminalName;

        const startTime = seatNumbers[0].startTime
        const endTime  = seatNumbers[0].endTime

        // HTML 요소를 업데이트


        document.getElementById('reservation_course_start').innerHTML = `<p>${startTerminalName}</p>`;
        document.getElementById('reservation_course_end').innerHTML = `<p>${endTerminalName}</p>`;
        document.getElementById('reservation_course_time').innerHTML = `<p>${calculateDuration(startTime, endTime)}</p>`
        document.querySelector('#reservation_seat_info h2').innerHTML = `${endTime.split('T')[0]}`


    })
    .catch(error => {
        console.error('Error fetching seat data:', error);
    });

function calculateDuration(startTime, endTime) {
    const start = new Date(startTime);
    const end = new Date(endTime);

    const durationMs = end - start;
    if (isNaN(durationMs)) {
        console.error('Invalid date(s) for duration calculation');
        return 'Invalid Duration';
    }

    const durationMinutes = Math.floor(durationMs / 60000); // 밀리초를 분으로 변환
    const hours = Math.floor(durationMinutes / 60);
    const minutes = durationMinutes % 60;

    return `${hours}시간 ${minutes}분`;
}

function updateSeatAvailabilityOnInit() {
    const seatElements = document.querySelectorAll('.seat');
    seatElements.forEach(seat => {
        const seatIndex = parseInt(seat.id.split('-')[1]);
        if (reservedSeats.includes(seatIndex)) {
            seat.classList.add('disabled');
        }
    });
}

function setupSeatClickHandlers() {
    document.querySelectorAll('.seat').forEach(seat => {
        seat.addEventListener('click', function () {
            const seatId = this.id;
            const seatIndex = parseInt(seatId.split('-')[1]);

            if (reservedSeats.includes(seatIndex) || totalPassengers() === 0) {
                return; // 예약된 좌석이거나 승객 수가 0이면 선택 불가
            }

            if (selectedSeats.includes(seatId)) {
                selectedSeats = selectedSeats.filter(id => id !== seatId);
                this.classList.remove('selected');
            } else if (selectedSeats.length < totalPassengers()) {
                selectedSeats.push(seatId);
                this.classList.add('selected');
            }

            updateTotalAmount();
            updateSeatAvailability();
        });
    });
}


// 인원수 조정 및 총 금액 업데이트
document.getElementById('adult-plus').addEventListener('click', () => {updatePassengerCount('adults', 1); document.getElementById('adults_right').textContent = passengers.adults;});
document.getElementById('adult-minus').addEventListener('click', () => {updatePassengerCount('adults', -1); document.getElementById('adults_right').textContent = passengers.adults;});
document.getElementById('student-plus').addEventListener('click', () => {updatePassengerCount('students', 1); document.getElementById('students_right').textContent = passengers.students;});
document.getElementById('student-minus').addEventListener('click', () => {updatePassengerCount('students', -1); document.getElementById('students_right').textContent = passengers.students;});
document.getElementById('child-plus').addEventListener('click', () => {updatePassengerCount('children', 1); document.getElementById('children_right').textContent = passengers.children;});
document.getElementById('child-minus').addEventListener('click', () => {updatePassengerCount('children', -1); document.getElementById('children_right').textContent = passengers.children;});


function totalPassengers() {
    return Object.values(passengers).reduce((total, num) => total + num, 0);
}


function updatePassengerCount(type, delta) {
    // 승객 감소 시도 시, 선택된 좌석 수가 인원 수보다 많아야 감소 가능
    const currentTotal = totalPassengers();

    // console.log(selectedSeats.length)
    // console.log(currentTotal)
    if (delta < 0 && selectedSeats.length >= currentTotal) {
        alert("선택한 좌석 수보다 예매하려는 매수가 적습니다 ");
        passengers[type] = Math.max(0, passengers[type] + 1);
        document.getElementById(type).textContent = passengers[type];
    }

    // 승객 수 업데이트
    passengers[type] = Math.max(0, passengers[type] + delta);
    document.getElementById(type).textContent = passengers[type];

    updateTotalAmount();
    updateSeatAvailability();
}


function updateTotalAmount() {
    const totalAmount =
        (passengers.adults * prices.adult) +
        (passengers.students * prices.student) +
        (passengers.children * prices.child);

    document.getElementById('total_amount').textContent = totalAmount;
}

function totalPassengers() {
    return passengers.adults + passengers.students + passengers.children;
}

function updateSeatAvailability() {
    const seatElements = document.querySelectorAll('.seat');
    seatElements.forEach(seat => {
        const seatIndex = parseInt(seat.id.split('-')[1]);

        // 예약된 좌석을 항상 비활성화
        if (reservedSeats.includes(seatIndex)) {
            seat.classList.add('disabled');
        } else {
            // 선택되지 않은 좌석과 예약되지 않은 좌석에 대해서만 활성화 상태 변경 이거하면 처음 로드될때  disable 처리가 안됨
            // if (!seat.classList.contains('selected')) {
            //     seat.classList.toggle('disabled', selectedSeats.length >= totalPassengers());
            // }
        }
    });
}
document.querySelector('.button2').addEventListener('click', function() {
    // 선택된 좌석 수와 승객 수를 가져옵니다.
    const selectedSeatCount = selectedSeats.length;
    const totalPassengerCount = totalPassengers();

    // 좌석 수와 승객 수 비교
    if (totalPassengerCount === 0) {
        alert("승객 수를 선택해주세요.");
    } else if (selectedSeatCount !== totalPassengerCount) {
        alert("선택한 좌석 수와 승객 수가 일치하지 않습니다.");
    } else {
        // 조건이 충족되면 URL로 리다이렉트
        window.location.href = "/reservation/payment-accept";

    }
});


