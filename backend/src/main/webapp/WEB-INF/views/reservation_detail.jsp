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
    <link rel="stylesheet" href="/resources/css/reservaton_detail.css" />
    <link rel="stylesheet" href="/resources/css/header.css">
    <script src='https://cdnjs.cloudflare.com/ajax/libs/gsap/3.11.5/gsap.min.js'></script>
    <title>Document</title>
  </head>
  <body>
    <user-header-component></user-header-component>
    <div id="reservation_detail_wrap">
      <div id="main_reservation_bar">
        ${schedules}
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
      
      <div id="reservation_course_wrap">
        <section id="reservation_course">
          <button id="reservation_course_calander">
            <i class="fa-solid fa-calendar-days"></i>
            날짜선택
          </button>
          <div id="reservation_course_menu_wrap">
            <ul id="reservation_course_menu">
              <li>출발</li>
              <li>고속사</li>
              <li>등급</li>
              <li>잔여석</li>
            </ul>
            <div></div>
          </div>
          
          
          <ul id="reservation_course_list">
            <!-- =================임시내용================ -->



            <!-- =================임시내용================ -->

          </ul>

        </section>
  
        <section id="reservation_course_info">
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
                  <p>${startTerminalName}</p>
                </div>
                <div id="reservation_course_blank">
      
                </div>
                <div id="reservation_course_end">
                  <p>${destinationTerminalName}</p>
                </div>
              </div>

            </div>
            

            <div id="reservation_course_info_other2">
              <ul id="reservation_course_info_otherlist">
                <li>
                  <p class="reservation_course_info_otherlistin">소요시간</p>
                  <p id="reservation_course_time">NULL</p>
                </li>

              </ul>
            </div>


            
            
              

          </div>

          <div id="reservation_course_pay">
            <h3>요금정보</h3>
            <ul id="reservation_course_pay_list">
              <li>
                <p class="reservation_course_pay_grade">우등</p>
                <p class="reservation_course_pay_price">21,000원</p>
              </li>
              <li>
                <p class="reservation_course_pay_grade">프리미엄</p>
                <p class="reservation_course_pay_price">32,000원</p>
              </li>

            </ul>
          </div>
        </section>
      </div>
      

    </div>
    <script>
      // 서버에서 전달된 스케줄 데이터를 전역 변수로 설정
      <%--const schedules = ${schedules};--%>

      <%--const schedules = ${schedulesJson};--%>

      let schedules = ${schedulesJson}
      // console.log(a);

      // JSP에서 JSON 문자열을 JavaScript 객체로 변환
      <%--const schedules = JSON.parse('<%= schedulesJson %>');--%>






    </script>
    <script src="https://accounts.google.com/gsi/client" async defer></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <script src="/resources/js/reservation_detail.js"></script>
    <script src="/resources/js/user_header.js"></script>
  </body>
</html>