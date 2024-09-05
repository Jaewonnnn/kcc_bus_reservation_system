<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <link rel="stylesheet" href="/resources/css/reset.css" />
    <link rel="stylesheet" href="/resources/css/reservation_seat3.css" />
    <link rel="stylesheet" href="/resources/css/header.css">
    <script src='https://cdnjs.cloudflare.com/ajax/libs/gsap/3.11.5/gsap.min.js'></script>
    <title>Document</title>
  </head>
  <body>
    <user-header-component></user-header-component>
    <div id="reservation_seat_wrap">
      <div id="main_reservation_bar">
        <ul id="main_reservation_bar">
          <li class="selector">
            <div class="main_reservation_bar_box"><i class="fa-solid fa-check"></i></div>
            <p>예매정보</p>
          </li>

          <li>
            <div class="line"></div>
          </li>

          <li>
            <div class="main_reservation_bar_box"><p>2</p></div>
            <p>확인/결제</p>
          </li>

          <li>
            <div class="line"></div>
          </li>

          <li>
            <div class="main_reservation_bar_box"><p>3</p></div>
            <p>예매완료</p>
          </li>
        </ul>

      </div>

      <div id="reservation_seat_wrap_detail">
        <section id="reservation_seat_select">
          <div id="reservation_seat_select_info">
            <div id="reservation_seat_select_info1">
              <i class="fa-solid fa-check"></i>
            </div>
            <div id="reservation_seat_select_info2">
              <p>
                당일 출발하는 차량의 출발시각 20분 이전까지 예매가 가능합니다.<br>
                선택하신 좌석은 실제 좌석과 상이 할 수 있습니다.<br>
                승객들의 원활한 승차, 수송을 위하여 10분전까지 발권하여 주시기 바랍니다.
              </p>
            </div>
          </div>

          <!-- =================좌석선택================ -->
          <div class="container">
            <div id="seat_map_wrap">
              <div class="seat-map3">
              
                <div class="seat" id="seat-1">1</div>
                <div class="seat" id="seat-2">2</div>

                <div class="seat-blank"></div>

                <div class="seat" id="seat-3">3</div>

                <div class="seat" id="seat-4">4</div>
                <div class="seat" id="seat-5">5</div>

                <div class="seat-blank"></div>

                <div class="seat" id="seat-6">6</div>

                <div class="seat" id="seat-7">7</div>
                <div class="seat" id="seat-8">8</div>

                <div class="seat-blank"></div>

                <div class="seat" id="seat-9">9</div>

                <div class="seat" id="seat-10">10</div>
                <div class="seat" id="seat-11">11</div>

                <div class="seat-blank"></div>

                <div class="seat" id="seat-12">12</div>

                <div class="seat" id="seat-13">13</div>
                <div class="seat" id="seat-14">14</div>

                <div class="seat-blank"></div>

                <div class="seat" id="seat-15">15</div>

                <div class="seat" id="seat-16">16</div>
                <div class="seat" id="seat-17">17</div>

                <div class="seat-blank"></div>

                <div class="seat" id="seat-18">18</div>

                <div class="seat" id="seat-19">19</div>
                <div class="seat" id="seat-20">20</div>

                <div class="seat-blank"></div>

                <div class="seat" id="seat-21">21</div>
              </div>

            </div>

            
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
                
            </div>
        </div>





          <!-- =================좌석선택================ -->



        </section>

        <section id="reservation_seat_info">
          <!-- =================임시내용================ -->
          <h2>2024.09.10 화</h2>
  

          <!-- =================임시내용================ -->
          <div id="reservation_course_info_detail">
            
            <div id="reservation_course_info_other1">
              <div id="reservation_course_info_wrap1">
                <div class="reservation_course_icon">
                  <p>출발</p>
                </div>
                <div id="reservation_course_line">
      
                </div>
                <div class="reservation_course_icon">
                  <p>도착</p>
                </div>
              </div>
    
              <div id="reservation_course_info_wrap2">
                <div id="reservation_course_start">
                  <p>동서울</p>
                </div>
                <div id="reservation_course_blank">
      
                </div>
                <div id="reservation_course_end">
                  <p>부산</p>
                </div>
              </div>

            </div>
            

            <div id="reservation_course_info_other2">
              <ul id="reservation_course_info_otherlist">
                <li>
                  <p class="reservation_course_info_otherlistin">소요시간</p>
                  <p id="reservation_course_time">4시간 30분</p>
                </li>

              </ul>
            </div>

          </div>

          <div id="reservation_course_info_summary">
            <h3>탑승인원 및 요금</h3>
            <ul id="reservation_course_info_summary_list">
              <li>
                <p class="people">성인</p>
                <p><span id="adults_right">0</span>명</p>
              </li>
              <li>
                <p class="people">중고생</p>
                <p><span id="students_right">0</span>명</p>
              </li>
              <li>
                <p class="people">아동</p>
                <p><span id="children_right">0</span>명</p>
              </li>
            </ul>
          </div>

          <div class="summary">
            <p>총 결제 금액: </p>
            <p><span id="total_amount">0</span>원</p>
          </div>

          <button class="button2">결제 하기</button>
        </section>
      </div>


    </div>


    <script src="https://accounts.google.com/gsi/client" async defer></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <script src="/resources/js/reservation_seat.js"></script>
    <script src="/resources/js/user_header.js"></script>
  </body>
</html>