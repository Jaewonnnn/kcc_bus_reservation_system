<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap"
      rel="stylesheet"
    />
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css' integrity='sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==' crossorigin='anonymous'/>
    <link rel="stylesheet" href="/css/reset.css" />
    <link rel="stylesheet" href="/css/main.css" />
    <link rel="stylesheet" href="/css/header.css">
    <script src='https://cdnjs.cloudflare.com/ajax/libs/gsap/3.11.5/gsap.min.js'></script>
    <title>Document</title>
  </head>
  <body>
    <header-component></header-component>
    <div id="main_wrap">
      <section id="main">
        <div id="main_view">
          <img src="/img/main_view.jpg">
          <div></div>
        </div>

        <div id="main_reservation_wrap">

          <div id="main_reservation_menu">
            <button class="selector">편도</button>
            <button>왕복</button>
          </div>

          <section id="main_reservation">
            
            <ul id="main_reservation_list">
              <li class="main_reservation_start">
                <p class="main_reservation_list_small"><i class="fa-solid fa-car"></i>출발지</p>
                <p class="main_reservation_list_big">출발지 입력</p>
              </li>

              <li class="main_reservation_start">
                <p class="main_reservation_list_small"><i class="fa-solid fa-hotel"></i>도착지</p>
                <p class="main_reservation_list_big">도착지 입력</p>
              </li>

              <li>
                <p class="main_reservation_list_small"><i class="fa-solid fa-user"></i>가는날</p>
                <p class="main_reservation_list_big">날짜 입력</p>
              </li>

              <li>
                <p class="main_reservation_list_small"><i class="fa-solid fa-user"></i>오는날</p>
                <p class="main_reservation_list_big">날짜 입력</p>
              </li>

              <li>
                <p class="main_reservation_list_small"><i class="fa-solid fa-user"></i>인원수</p>
                <p class="main_reservation_list_big">인원수 추가</p>
              </li>

            </ul>

            <button id="main_reservation_search"><i class="fa-solid fa-magnifying-glass"></i></button>
          </section>

        </div>

        <div id="main_down_list">
          <div id="main_announcement">
            <h2>공지사항</h2>
            <ul id="main_announcement_list">
              <li>
                경기도 버스운수종사자 양성과정 교육생 모집 및 채용박람회
              </li>
              <li>
                NICE아이디서비스 작업안내
              </li>
              <li>
                [네이버페이] 8월 은행_증권가 시스템 점검 일정 안내
              </li>
              <li>
                경기도 버스운수종사자 양성교육 모집 및 채용박람회
              </li>
            </ul>
          </div>
          <div id="main_question">
            <h2>자주찾는질문</h2>
            <ul id="main_question_list">
              <li>
                승차권 예매에 관한 질문
              </li>
              <li>
                승차권 조회/변경/취소에 관한 질문
              </li>
              <li>
                운행 정보에 관한 질문
              </li>
              <li>
                기타 다양한 사항에 관한 질문
              </li>
            </ul>

          </div>

        </div>


      </section>

      <!-- ==========================모달디자인========================== -->
      <div id="modal1">
        <div id="modal-content">
          <span class="close-btn">&times;</span>
          <div id="modal-header">
            <div>출발</div>
            <div>도착</div>
          </div>
          <div id="modal-search">
            <input type="text" placeholder="터미널 / 지역 이름 검색" />
            <button><i class="fa-solid fa-magnifying-glass"></i></button>
          </div>
          <div id="modal-options">
            <div id="modal-regions">
              <ul>
                <li>서울</li>
                <li>인천/경기</li>
                <li>강원</li>
                <li>대전/충남</li>
                <li>충북</li>
                <li>광주/전남</li>
                <li>전북</li>
                <li>부산/경남</li>
                <li>대구/경북</li>
              </ul>
            </div>
            <div id="modal-destinations">
              <button>강릉</button>
              <button>강진</button>
              <button>경북도청</button>
              <!-- 더 많은 목적지를 추가 -->
            </div>
          </div>
        </div>
      </div>


    </div>
    <script src="https://accounts.google.com/gsi/client" async defer></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <script src="/js/main.js"></script>
    <script src="/js/header.js"></script>
  </body>
</html>