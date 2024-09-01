document.addEventListener('DOMContentLoaded', function() {
  // 모달 엘리먼트와 닫기 버튼을 가져옵니다.
  const modal = document.getElementById('modal1');
  const closeButton = document.querySelector('.close-btn');

  // 출발지와 도착지 입력 부분을 가져옵니다.
  const departureLi = document.querySelectorAll('.main_reservation_start');

  // li 요소를 클릭했을 때 모달을 엽니다.
  departureLi.forEach(li => {
    li.addEventListener('click', () => {
      modal.style.display = 'flex'; // 모달을 화면에 보이도록 설정
    });
  });

  // 닫기 버튼을 클릭했을 때 모달을 닫습니다.
  closeButton.addEventListener('click', () => {
    modal.style.display = 'none';
  });

  // 모달 외부를 클릭했을 때 모달을 닫습니다.
  window.addEventListener('click', (event) => {
    if (event.target == modal) {
      modal.style.display = 'none';
    }
  });
});
