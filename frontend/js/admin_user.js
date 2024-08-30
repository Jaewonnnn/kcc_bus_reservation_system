// 모든 수정 버튼을 선택합니다.
const modalButtons = document.querySelectorAll(".open-modal");

// 각 버튼에 클릭 이벤트 리스너를 추가합니다.
modalButtons.forEach((button) => {
  button.addEventListener("click", () => {
    // Bootstrap 모달 객체를 생성하고, 보여줍니다.
    const modal = new bootstrap.Modal(
      document.getElementById("staticBackdrop")
    );
    modal.show();
  });
});

// 모달이 열렸을 때 특정 입력 필드에 포커스를 맞추는 코드 (옵션)
const staticBackdrop = document.getElementById("staticBackdrop");
const myInput = document.getElementById("myInput");

staticBackdrop.addEventListener("shown.bs.modal", () => {
  if (myInput) {
    myInput.focus();
  }
});
