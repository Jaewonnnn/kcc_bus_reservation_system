class Headers extends HTMLElement {
  constructor() {
    super();
    this.attachShadow({ mode: 'open' });
    this.shadowRoot.innerHTML = `
      <link rel="stylesheet" href="/resources/css/reset.css">
      <link rel="stylesheet" href="/resources/css/header.css">
      <header>
        <div id="header_wrap">
          <a href="/main"><h1>UNIBUS</h1></a>
          <ul id="header_menu">
            <li>승차권예매</li>
            <li>조회/취소</li>
            <li>터미널정보</li>
          </ul>         
          <div id="header_login_wrap">
            <button class="login_join_btn" onclick="location.href='/join'">회원가입</button>
            <button class="login_join_btn" onclick="location.href='/login'">로그인</button>
            <form action="/logout" method="post">
                <button class="login_join_btn" type="submit">로그아웃</button>    
            </form>
          </div>
        </div>
      </header>
    `;
  }
}

customElements.define('user-header-component', Headers);

document.addEventListener('DOMContentLoaded', () => {
  headerMenu();
});

function headerMenu() {
  const shadowRoot = document.querySelector('header-component').shadowRoot;
  const menuItems = shadowRoot.querySelectorAll('#header_menu > li');

  menuItems.forEach(item => {
    item.addEventListener('click', (event) => {
      event.stopPropagation();
      const currentItem = shadowRoot.querySelector('#header_menu > li.selector');
      if (currentItem) {
        currentItem.classList.remove('selector');
      }
      item.classList.add('selector');

    });
  });

  // 로컬 스토리지에서 저장된 선택된 메뉴 항목을 불러와서 설정합니다.
  const selectedMenu = localStorage.getItem('selectedMenu');
  if (selectedMenu) {
    const selectedItem = Array.from(menuItems).find(item => {
      const link = item.querySelector('a');
      return link && link.getAttribute('href') === selectedMenu;
    });

    if (selectedItem) {
      const currentItem = shadowRoot.querySelector('#header_menu > li.selector');
      if (currentItem) {
        currentItem.classList.remove('selector');
      }
      selectedItem.classList.add('selector');
    }
  }
}
