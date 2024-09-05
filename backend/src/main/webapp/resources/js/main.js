document.addEventListener("DOMContentLoaded", function () {
  // 모달 엘리먼트와 닫기 버튼을 가져옵니다.
  const modal = document.getElementById("modal1");
  const modal2 = document.getElementById("modal2");
  const modal3 = document.getElementById("modal3");

  const closeButton = document.querySelector(".close-btn");
  var nextMonthButton = document.querySelector("#nextMonthButton");
  var prevMonthButton = document.querySelector("#prevMonthButton");

  const startLocationDisplay = document.querySelectorAll('.main_reservation_list_big')[0]; // 출발지 표시
  const destinationDisplay = document.querySelectorAll('.main_reservation_list_big')[1]; // 도착지 표시

  const startLocationDay = document.querySelectorAll('.main_reservation_list_big')[2]; // 출발지 날짜
  const destinationDay = document.querySelectorAll('.main_reservation_list_big')[3]; // 도착지 날짜

  //------modal3
  const reservationPersonText = document.querySelector('.main_reservation_person .main_reservation_list_big');
  const modal3_close =document.querySelector("#modal3-close")

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
      isStartLocationSelected = false;
      fetchTerminals();//===========
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
      date.getFullYear()+ "년" + " " + ("0" + (date.getMonth() + 1)).slice(-2) + "월";

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

      const isPastDate =
          isCurrentMonth && i < today.getDate(); // 이전 날짜 로직
      if (!isPastDate ) { // 이전 날짜 이전꺼는 이벤트를 안걸려고함

        dayDiv.onclick = function () {
          // 여기서 값 저장 이벤트를 걸어야함 혹은 input 하나 숨겨놧다가 input에 값 전달하고 해도 됨
          // 선택하면 값 보여주게해도됨 (날짜 잘못눌러서 다시하려해도 li다시누름 값바껴서 괜찮)
          alert(currentDateDiv.textContent + i + "일" + " 을 선택하셨습니다.");

          startLocationDay.textContent = currentDateDiv.textContent + i + "일";

          closeModal2();
        };
      }else{
        dayDiv.classList.add("disabled"); // 과거 날짜는 스타일로 구분 (선택 불가 표시
      }

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

    return total = passengers.adults + passengers.students + passengers.children;
    // return reservationPersonText.textContent = `성인 ${passengers.adults}명, 중고생 ${passengers.students}명, 아동 ${passengers.children}명`;
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

  function closeModal1() {
    const modal = document.getElementById('modal1');
    modal.style.display = 'none';
  }

  function closeModal2() {
    const modal = document.getElementById('modal2');
    modal.style.display = 'none';
  }

  modal3_close.addEventListener('click', () => {
    // "완료" 버튼 클릭 시 인원수 업데이트
    // 모달 닫기 (디스플레이 속성을 수정하여 모달을 숨김)
    document.getElementById('modal3').style.display = 'none';
    reservationPersonText.textContent = `총 ${passengers.adults + passengers.students + passengers.children}명`;
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



//------------------------------------------------------------------------------
  // 도시 및 터미널 버튼 컨테이너
  const terminalContainer = document.getElementById('terminal-buttons');
  const cityList = document.querySelectorAll('#modal-regions li'); // 도시 리스트

  const startLocationDisplayIn = document.querySelectorAll('#modal-header>div')[0]
  const destinationDisplayIn = document.querySelectorAll('#modal-header>div')[1]



  // 검색 입력 필드 및 버튼 가져오기
  const searchInput = document.querySelector('#modal-search input');
  const searchButton = document.querySelector('#modal-search button');

  let isStartLocationSelected = false; // 출발지 선택 여부를 추적

  // 도시 리스트를 DOM에서 읽어와 클릭 이벤트를 설정합니다.
  cityList.forEach(city => {
    city.addEventListener('click', () => {
      const cityId = city.getAttribute('data-region');
      fetchTerminals(cityId);
    });
  });

  function generateTerminalButtons(terminals) {
    if (!terminalContainer) {
      console.error('터미널 못찾음');
      return;
    }
    terminalContainer.innerHTML = ''; // 기존 버튼들 제거

    if (terminals.length === 0) {
      terminalContainer.innerHTML = '<p>해당 도시의 터미널이 없습니다.</p>';
      return;
    }


    terminals.forEach(terminal => {
      const button = document.createElement('button');
      button.className = 'terminal-button';
      button.textContent = terminal.terminalName;
      // button.onclick = () => handleTerminalButtonClick(terminal.id);
      button.onclick = () => handleTerminalButtonClick(terminal.terminalName);
      terminalContainer.appendChild(button);
    });
  }

  function handleTerminalButtonClick(terminalId) {


    if (!isStartLocationSelected) {
      // 첫 번째 클릭: 출발지 설정
      startLocationDisplayIn.textContent = terminalId
      startLocationDisplay.textContent = terminalId;
      isStartLocationSelected = true;
      alert(`출발지로 ${terminalId} 터미널이 선택되었습니다.`);
    } else {
      // 두 번째 클릭: 도착지 설정
      destinationDisplayIn.textContent = terminalId;
      destinationDisplay.textContent = terminalId;
      alert(`도착지로 ${terminalId} 터미널이 선택되었습니다.`);
      closeModal1(); // 선택 완료 후 모달 닫기
    }
  }


  async function fetchTerminals(cityId = null) { // cityId 매개변수를 선택적으로 처리
    try {
      const url = cityId
          ? `http://localhost:8081/reservation/terminal_list?cityId=${cityId}`
          : 'http://localhost:8081/reservation/terminal_list'; // 모든 데이터 가져오기

      const response = await fetch(url); // API 엔드포인트 URL
      if (!response.ok) throw new Error('네트워크 응답불가');
      const data = await response.json();
      console.table(data)
      let filteredTerminals = data;

      // cityId가 제공된 경우에만 필터링 수행
      if (cityId) {
        const cityIdNum = parseInt(cityId);
        filteredTerminals = data.filter(terminal => {
          const terminalCityId = terminal.city;
          if (cityIdNum < 31000) {
            return terminalCityId === cityIdNum;
          } else if (cityIdNum >= 31000 && cityIdNum < 32000) {
            return terminalCityId >= 31000 && terminalCityId < 32000; // 경기도
          } else if (cityIdNum >= 32000 && cityIdNum < 33000) {
            return terminalCityId >= 32000 && terminalCityId < 33000; // 강원도
          } else if (cityIdNum >= 33000 && cityIdNum < 34000) {
            return terminalCityId >= 33000 && terminalCityId < 34000; // 충청북도
          } else if (cityIdNum >= 34000 && cityIdNum < 35000) {
            return terminalCityId >= 34000 && terminalCityId < 35000; // 충청남도
          } else if (cityIdNum >= 35000 && cityIdNum < 36000) {
            return terminalCityId >= 35000 && terminalCityId < 36000; // 전라북도
          } else if (cityIdNum >= 36000 && cityIdNum < 37000) {
            return terminalCityId >= 36000 && terminalCityId < 37000; // 전라남도
          } else if (cityIdNum >= 37000 && cityIdNum < 38000) {
            return terminalCityId >= 37000 && terminalCityId < 38000; // 경상북도
          } else if (cityIdNum >= 38000 && cityIdNum < 39000) {
            return terminalCityId >= 38000 && terminalCityId < 39000; // 경상남도
          }
          return false;
        });
      }

      generateTerminalButtons(filteredTerminals); // 터미널 버튼 생성
    } catch (error) {
      console.error('터미널 데이터를 가져오는 중 오류 발생:', error);
    }
  }

  // 검색 기능
  function searchTerminals(terminals, query) {
    return terminals.filter(terminal => {
      const terminalName = terminal.terminalName ? terminal.terminalName.toLowerCase() : '';
      const regionName = terminal.region ? terminal.region.toLowerCase() : '';
      return terminalName.includes(query.toLowerCase()) || regionName.includes(query.toLowerCase());
    });
  }


  // 검색 버튼 클릭 시 터미널 검색
  searchButton.addEventListener('click', async () => {
    const query = searchInput.value.trim();
    if (query) {
      try {
        const url = 'http://localhost:8081/reservation/terminal_list'; // 모든 데이터 가져오기
        const response = await fetch(url);
        if (!response.ok) throw new Error('Network response was not ok.');
        const data = await response.json();

        const searchResults = searchTerminals(data, query);
        generateTerminalButtons(searchResults);
      } catch (error) {
        console.error('검색 중 오류 발생:', error);
      }
    }
  });


  //==================================================================편도 왕복 변경
  const onewayButton = document.getElementById('oneway');
  const roundtripButton = document.getElementById('roundtrip');
  const reservationList = document.getElementById('main_reservation_list');

  onewayButton.addEventListener('click', () => toggleReservationType('oneway'));
  roundtripButton.addEventListener('click', () => toggleReservationType('roundtrip'));

  function toggleReservationType(type) {
    const reservationList = document.getElementById('main_reservation_list');

    if (type === 'oneway') {
      // "왕복" 버튼 클릭 상태에서 "오는날" 항목이 추가된 경우
      const arrivalDateItem = document.getElementById('arrival-date');
      if (arrivalDateItem) {
        reservationList.removeChild(arrivalDateItem);
      }

      // "편도" 버튼 클릭 상태에서 "오는날" 항목이 추가된 경우
      const departureDateItem = document.getElementById('departure-date');
      if (!departureDateItem) {
        const newDepartureDateItem = document.createElement('li');
        newDepartureDateItem.className = 'main_reservation_calendar';
        newDepartureDateItem.id = 'departure-date';
        newDepartureDateItem.innerHTML = `
        <p class="main_reservation_list_small">
          <i class="fa-regular fa-calendar-days"></i>가는날
        </p>
        <p class="main_reservation_list_big">날짜 입력</p>
      `;
        reservationList.appendChild(newDepartureDateItem);
      }

      // 상태 변경
      document.getElementById('oneway').classList.add('selector');
      document.getElementById('roundtrip').classList.remove('selector');
    } else if (type === 'roundtrip') {
      // "편도" 버튼 클릭 상태에서 "가는날" 항목이 삭제된 경우
      const departureDateItem = document.getElementById('departure-date');
      if (departureDateItem) {
        reservationList.removeChild(departureDateItem);
      }

      // "왕복" 버튼 클릭 상태에서 "오는날" 항목이 추가된 경우
      const arrivalDateItem = document.getElementById('arrival-date');
      if (!arrivalDateItem) {
        const newArrivalDateItem = document.createElement('li');
        newArrivalDateItem.className = 'main_reservation_calendar';
        newArrivalDateItem.id = 'arrival-date';
        newArrivalDateItem.innerHTML = `
        <p class="main_reservation_list_small">
          <i class="fa-regular fa-calendar-days"></i>오는날
        </p>
        <p class="main_reservation_list_big">날짜 입력</p>
      `;
        reservationList.appendChild(newArrivalDateItem);
      }

      // 상태 변경
      document.getElementById('roundtrip').classList.add('selector');
      document.getElementById('oneway').classList.remove('selector');
    }
  }

  //---------------------------------------------------

  // 터미널 이름으로 터미널 ID를 조회하는 함수
  async function getTerminalIdByName(terminalName) {
    try {
      const response = await fetch(`/reservation/terminal_list`);
      if (!response.ok) throw new Error('Error fetching terminal list');

      const terminals = await response.json();
      // 터미널 이름과 일치하는 ID를 찾아서 반환합니다.
      const terminal = terminals.find(term => term.terminalName === terminalName);
      return terminal ? terminal.terminalId : null;
    } catch (error) {
      console.error('Error fetching terminal ID:', error);
      return null;
    }
  }

  document.getElementById("main_reservation_search").addEventListener("click", async function () {
    // 출발지, 도착지, 출발 시간 정보를 가져옵니다.
    const startLocation = document.querySelectorAll(".main_reservation_list_big")[0].textContent.trim();
    const destination = document.querySelectorAll(".main_reservation_list_big")[1].textContent.trim();
    const startTime = document.querySelectorAll(".main_reservation_list_big")[2].textContent.trim();

    // startTime을 "YYYY년 MM월 DD일" 형식에서 "YY/MM/DD" 형식으로 변환
    function formatDate(dateStr) {
      const match = dateStr.match(/(\d{4})년 (\d{2})월(\d{1,2})일/);
      if (!match) {
        alert('날짜를 입력해주세요 ')

        console.error('Invalid date format:', dateStr);
        return null;
      }

      const [, year, month, day] = match;
      const formattedDate = `${year}-${month.padStart(2, '0')}-${day.padStart(2, '0')}`;
      return formattedDate;
    }

    const formattedStartTime = formatDate(startTime);
    if (!formattedStartTime) {
      console.error('Error formatting startTime:', startTime);
      return;
    }

    console.log('Formatted startTime:', formattedStartTime);

    try {
      // 터미널 이름으로 ID를 가져옵니다.
      const startTerminalId = await getTerminalIdByName(startLocation);
      const destinationTerminalId = await getTerminalIdByName(destination);

      if (!startTerminalId || !destinationTerminalId) {
        console.error('Failed to get terminal IDs');
        return;
      }

      // 서버에 데이터 전송
      const response = await fetch('/reservation/schedule_list', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          startTerminalId: startTerminalId,
          destinationTerminalId: destinationTerminalId,
          startTime: formattedStartTime
        })
      });

      if (!response.ok) throw new Error('Error submitting schedule data');

      const result = await response.json();
      console.log('Server response:', result);

      // 쿼리 파라미터로 변환하여 리다이렉트
      const queryParams = new URLSearchParams({
        startTerminalId: startTerminalId,
        destinationTerminalId: destinationTerminalId,
        startTime: formattedStartTime
      }).toString();

      window.location.href = `/reservation/schedule_list?${queryParams}`;

    } catch (error) {
      console.error('Error:', error);
    }
  });



  //--------------------------------swiper
  const progressCircle = document.querySelector(".autoplay-progress svg");
  const progressContent = document.querySelector(".autoplay-progress span");
  var swiper = new Swiper(".mySwiper", {
    spaceBetween: 0,
    loop: true,
    centeredSlides: true,
    autoplay: {
      delay: 3500,
      disableOnInteraction: false
    },
    pagination: {
      el: ".swiper-pagination",
      clickable: true
    }
  });


});
