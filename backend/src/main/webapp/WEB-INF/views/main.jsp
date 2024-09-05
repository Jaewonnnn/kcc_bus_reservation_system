<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap"
      rel="stylesheet"
    />
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css' integrity='sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==' crossorigin='anonymous'/>
    <link rel="stylesheet" href="/resources/css/reset.css" />
    <link rel="stylesheet" href="/resources/css/main.css" />
    <link rel="stylesheet" href="/resources/css/header.css">
    <script src='https://cdnjs.cloudflare.com/ajax/libs/gsap/3.11.5/gsap.min.js'></script>
    <title>Document</title>
  </head>
  <body>
    <header-component></header-component>
    <div id="main_wrap">
      <section id="main" class="swiper mySwiper">
        <div id="main_view" class="swiper-wrapper">
          <img src="/resources/img/main_view.jpg" class="swiper-slide">
          <img src="/resources/img/main_view2.jpg" class="swiper-slide">
          <img src="/resources/img/main_view3.jpg" class="swiper-slide">
          <img src="/resources/img/main_view4.jpg" class="swiper-slide">
          <div></div>
        </div>

        <div id="main_reservation_wrap">
          <div id="main_reservation_menu">
            <button id="oneway" class="selector">편도</button>
            <button id="roundtrip">왕복</button>
          </div>
          <section id="main_reservation">
            <ul id="main_reservation_list">
              <li class="main_reservation_start">
                <p class="main_reservation_list_small">
                  <i class="fa-solid fa-car"></i>출발지
                </p>
                <p class="main_reservation_list_big">출발지 입력</p>
              </li>

              <li class="main_reservation_start">
                <p class="main_reservation_list_small">
                  <i class="fa-solid fa-hotel"></i>도착지
                </p>
                <p class="main_reservation_list_big">도착지 입력</p>
              </li>

              <li class="main_reservation_calendar">
                <p class="main_reservation_list_small">
                  <i class="fa-regular fa-calendar-days"></i>가는날
                </p>
                <p class="main_reservation_list_big">날짜 입력</p>
              </li>

<%--              <li class="main_reservation_calendar">--%>
<%--                <p class="main_reservation_list_small">--%>
<%--                  <i class="fa-regular fa-calendar-days"></i>오는날--%>
<%--                </p>--%>
<%--                <p class="main_reservation_list_big">날짜 입력</p>--%>
<%--              </li>--%>

              <li class="main_reservation_person">
                <p class="main_reservation_list_small">
                  <i class="fa-solid fa-user"></i>인원수
                </p>
                <p class="main_reservation_list_big">인원수 추가</p>
              </li>
            </ul>

            <button id="main_reservation_search">
              <i class="fa-solid fa-magnifying-glass"></i>
            </button>
          </section>
        </div>

        <div id="main_down_list">
          <div id="main_announcement">
            <h2>공지사항</h2>
            <ul id="main_announcement_list">
              <li>경기도 버스운수종사자 양성과정 교육생 모집 및 채용박람회</li>
              <li>NICE아이디서비스 작업안내</li>
              <li>[네이버페이] 8월 은행_증권가 시스템 점검 일정 안내</li>
              <li>경기도 버스운수종사자 양성교육 모집 및 채용박람회</li>
            </ul>
          </div>
          <div id="main_question">
            <h2>자주찾는질문</h2>
            <ul id="main_question_list">
              <li>승차권 예매에 관한 질문</li>
              <li>승차권 조회/변경/취소에 관한 질문</li>
              <li>운행 정보에 관한 질문</li>
              <li>기타 다양한 사항에 관한 질문</li>
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
                <li data-region="11">서울특별시</li>
                <li data-region="21">부산광역시</li>
                <li data-region="22">대구광역시</li>
                <li data-region="23">인천광역시</li>
                <li data-region="24">광주광역시</li>
                <li data-region="25">대전광역시</li>
                <li data-region="26">울산광역시</li>
                <li data-region="31000">경기도</li>
                <li data-region="32000">강원도</li>
                <li data-region="33000">충청북도</li>
                <li data-region="34000">충청남도</li>
                <li data-region="35000">전라북도</li>
                <li data-region="36000">전라남도</li>
                <li data-region="37000">경상북도</li>
                <li data-region="38000">경상남도</li>
              </ul>
            </div>
            <div id="terminal-buttons">
              <!-- 더 많은 목적지를 추가 -->
            </div>
          </div>
        </div>
      </div>
      <!-- 달력 -->
      <div id="modal2">
        <div id="calendarWrapper">
          <div class="calendar-header" id="currentDate"></div>
          <div id="wrapper">
            <div>일</div>
            <div>월</div>
            <div>화</div>
            <div>수</div>
            <div>목</div>
            <div>금</div>
            <div>토</div>
          </div>
          <div class="nav-buttons">
            <button id="prevMonthButton" class="button2"><</button>
            <button id="nextMonthButton" class="button2">></button>
          </div>
        </div>
      </div>
      <!-- 인원수 추가 -->
      <div id="modal3">
        <div id="modal3-content">
          <div class="controls">
            <div class="passenger-type">
              <label for="adults">성인</label>
              <button id="adult-minus">-</button>
              <span id="adults">0</span>
              <button id="adult-plus">+</button>
            </div>
            <div class="passenger-type">
              <label for="students">중고생</label>
              <button id="student-minus">-</button>
              <span id="students">0</span>
              <button id="student-plus">+</button>
            </div>
            <div class="passenger-type">
              <label for="children">아동</label>
              <button id="child-minus">-</button>
              <span id="children">0</span>
              <button id="child-plus">+</button>
            </div>
            <button id="modal3-close">완료</button>
          </div>
        </div>
      </div>
    </div>
    <div id="tost_message">
      현재 날짜 이전으로는 날짜를 선택할 수 없습니다. 다른 날짜를 선택해 주세요.
    </div>
    <script src="https://accounts.google.com/gsi/client" async defer></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <script src="/resources/js/main.js"></script>
    <script src="/resources/js/header.js"></script>
  </body>
</html>
