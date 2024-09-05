document.addEventListener("DOMContentLoaded", function () {
  // 모달 엘리먼트와 닫기 버튼을 가져옵니다.
  const modal = document.getElementById("modal1");
  const modal2 = document.getElementById("modal2");
  const modal3 = document.getElementById("modal3");

  const closeButton = document.querySelector(".close-btn");
  var nextMonthButton = document.querySelector("#nextMonthButton");
  var prevMonthButton = document.querySelector("#prevMonthButton");

  // 출발지와 도착지 입력 부분을 가져옵니다. - 모달1선언
  const departureLi = document.querySelectorAll(".main_reservation_start");

  // 승우 - 달력추가
  const departCalendar = document.querySelectorAll(
    ".main_reservation_calendar"
  );

  // 승우 - 인원수 추가 모달2 선언
  const departPerson = document.querySelector(".main_reservation_person");

  // 토스트 메세지
  let tostMessage = document.getElementById("tost_message");

  // 모달3선언
  let passengers = {
    adults: 0,
    students: 0,
    children: 0,
  };

  document.getElementById("adult-plus").addEventListener("click", () => {
    updatePassengerCount("adults", 1);
    document.getElementById("adults_right").textContent = passengers.adults;
  });
  document.getElementById("adult-minus").addEventListener("click", () => {
    updatePassengerCount("adults", -1);
    document.getElementById("adults_right").textContent = passengers.adults;
  });
  document.getElementById("student-plus").addEventListener("click", () => {
    updatePassengerCount("students", 1);
    document.getElementById("students_right").textContent = passengers.students;
  });
  document.getElementById("student-minus").addEventListener("click", () => {
    updatePassengerCount("students", -1);
    document.getElementById("students_right").textContent = passengers.students;
  });
  document.getElementById("child-plus").addEventListener("click", () => {
    updatePassengerCount("children", 1);
    document.getElementById("children_right").textContent = passengers.children;
  });
  document.getElementById("child-minus").addEventListener("click", () => {
    updatePassengerCount("children", -1);
    document.getElementById("children_right").textContent = passengers.children;
  });

  // ########  선언부 끝 구현부 시작   ################

  // li 요소를 클릭했을 때 모달을 엽니다.
  //모달 1 기능 및 연결
  departureLi.forEach((li) => {
    li.addEventListener("click", () => {
      modal.style.display = "flex"; // 모달을 화면에 보이도록 설정
    });
  });

  //모달 2 기능 및 연결
  departCalendar.forEach((li) => {
    li.addEventListener("click", () => {
      modal2.style.display = "flex"; // 모달을 화면에 보이도록 설정
      generateCalendar(currentDate);
    });
  });

  departPerson.addEventListener("click", () => {
    modal3.style.display = "flex";
  });

  // 현재 날짜를 전역 변수로 정의하고 초기화
  var currentDate = new Date();

  // 모달 달력 생성
  function generateCalendar(date) {
    const wra = document.getElementById("wrapper");
    const currentDateDiv = document.getElementById("currentDate");

    wra.innerHTML =
      "<div>일</div><div>월</div><div>화</div><div>수</div><div>목</div><div>금</div><div>토</div>";
    currentDateDiv.textContent =
      date.getFullYear() + "-" + ("0" + (date.getMonth() + 1)).slice(-2);

    date.setDate(1);
    const firstDay = date.getDay();
    const lastDay = new Date(
      date.getFullYear(),
      date.getMonth() + 1,
      0
    ).getDate();

    for (let i = 0; i < firstDay; i++) {
      wra.innerHTML += "<div></div>";
    }
    for (let i = 1; i <= lastDay; i++) {
      const dayDiv = document.createElement("div");
      dayDiv.className = "calendar-day";
      dayDiv.textContent = i;

      const today = new Date(); // 오늘 날짜
      const isCurrentMonth =
        today.getFullYear() === date.getFullYear() &&
        today.getMonth() === date.getMonth();

      dayDiv.onclick = function () {
        // 여기서 값 저장 이벤트를 걸어야함 혹은 input 하나 숨겨놧다가 input에 값 전달하고 해도 됨
        // 선택하면 값 보여주게해도됨 (날짜 잘못눌러서 다시하려해도 li다시누름 값바껴서 괜찮)
        alert("You selected: " + i + " " + currentDateDiv.textContent);
      };

      if (isCurrentMonth && i === today.getDate()) {
        dayDiv.classList.add("today");
      }
      wra.appendChild(dayDiv);
    }
  }
  function changeMonth(direction) {
    // 오늘 날짜를 가져옵니다.
    const today = new Date();
    const currentMonth = currentDate.getMonth();
    const currentYear = currentDate.getFullYear();

    // 이전 달로 넘어가는 경우 현재 날짜가 오늘 날짜의 달 이후인지 확인
    if (direction === -1) {
      if (
        currentYear < today.getFullYear() ||
        (currentYear === today.getFullYear() &&
          currentMonth <= today.getMonth())
      ) {
        // 현재가 이미 오늘 날짜의 달 이하이면 더 이상 넘어가지 않음
        tostOn();
        return;
      }
    }

    // 날짜를 변경합니다.
    currentDate.setMonth(currentMonth + direction);
    generateCalendar(currentDate);
  }

  // 토스트 메시지 노출-사라짐 함수 작성
  function tostOn() {
    tostMessage.classList.add("active");
    setTimeout(function () {
      tostMessage.classList.remove("active");
    }, 2000);
  }

  // 다음 달, 이전 달 버튼에 이벤트 리스너 연결
  if (nextMonthButton) {
    nextMonthButton.addEventListener("click", function () {
      changeMonth(1);
    });
  }
  if (prevMonthButton) {
    prevMonthButton.addEventListener("click", function () {
      changeMonth(-1);
    });
  }

  //모달 3 기능 및 연결

  function updatePassengerCount(type, delta) {
    passengers[type] = Math.max(0, passengers[type] + delta);
    document.getElementById(type).textContent = passengers[type];
    updateTotalAmount();
    updateSeatAvailability();
  }

  function updateTotalAmount() {
    const totalAmount =
      passengers.adults * prices.adult +
      passengers.students * prices.student +
      passengers.children * prices.child;

    document.getElementById("total_amount").textContent = totalAmount;
  }

  function totalPassengers() {
    return passengers.adults + passengers.students + passengers.children;
  }

  function updateSeatAvailability() {
    const seatElements = document.querySelectorAll(".seat");

    seatElements.forEach((seat) => {
      if (!seat.classList.contains("selected")) {
        if (selectedSeats.length >= totalPassengers()) {
          seat.classList.add("disabled");
        } else {
          seat.classList.remove("disabled");
        }
      }
    });
  }

  // 모달 공통
  // 닫기 버튼을 클릭했을 때 모달을 닫습니다.
  closeButton.addEventListener("click", () => {
    modal.style.display = "none";
  });

  // 모달 외부를 클릭했을 때 모달을 닫습니다.
  window.addEventListener("click", (event) => {
    if (event.target == modal) {
      modal.style.display = "none";
    }
    if (event.target == modal2) {
      modal2.style.display = "none";
    }
    if (event.target == modal3) {
      modal3.style.display = "none";
    }
  });
});
