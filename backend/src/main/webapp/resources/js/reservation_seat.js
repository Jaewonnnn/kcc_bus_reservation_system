// 요금 설정 (예시)
const prices = {
  adult: 10000,
  student: 8000,
  child: 5000
};

let selectedSeats = [];
let passengers = {
  adults: 0,
  students: 0,
  children: 0
};

// 좌석 선택 처리
document.querySelectorAll('.seat').forEach(seat => {
  seat.addEventListener('click', function() {
      const seatId = this.id;

      // 좌석이 이미 선택되어 있으면 선택 해제
      if (selectedSeats.includes(seatId)) {
          selectedSeats = selectedSeats.filter(id => id !== seatId);
          this.classList.remove('selected');
      } else if (selectedSeats.length < totalPassengers()) {
          // 좌석이 선택되어 있지 않고, 총 인원 수보다 적은 좌석이 선택되었을 때만 선택 가능
          selectedSeats.push(seatId);
          this.classList.add('selected');
      }

      updateTotalAmount();
      updateSeatAvailability();
  });
});

// 인원수 조정 및 총 금액 업데이트
document.getElementById('adult-plus').addEventListener('click', () => {updatePassengerCount('adults', 1); document.getElementById('adults_right').textContent = passengers.adults;});
document.getElementById('adult-minus').addEventListener('click', () => {updatePassengerCount('adults', -1); document.getElementById('adults_right').textContent = passengers.adults;});
document.getElementById('student-plus').addEventListener('click', () => {updatePassengerCount('students', 1); document.getElementById('students_right').textContent = passengers.students;});
document.getElementById('student-minus').addEventListener('click', () => {updatePassengerCount('students', -1); document.getElementById('students_right').textContent = passengers.students;});
document.getElementById('child-plus').addEventListener('click', () => {updatePassengerCount('children', 1); document.getElementById('children_right').textContent = passengers.children;});
document.getElementById('child-minus').addEventListener('click', () => {updatePassengerCount('children', -1); document.getElementById('children_right').textContent = passengers.children;});

function updatePassengerCount(type, delta) {
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
      if (!seat.classList.contains('selected')) {
          if (selectedSeats.length >= totalPassengers()) {
              seat.classList.add('disabled');
          } else {
              seat.classList.remove('disabled');
          }
      }
  });
}
