<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <link
      href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
      integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="/resources/css/reset.css" />
    <link rel="stylesheet" href="/resources/css/header.css" />
    <link rel="stylesheet" href="/resources/css/payment.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.11.5/gsap.min.js"></script>
    <title>결제 페이지</title>
  </head>
  <body>
    <admin-header-component></admin-header-component>
    <div id="body_wrap">
      <!-- 탑 카드 영역 -->
      <section id="admin_info">
        <div class="" style="padding: 60px 0">
          <div class="paymentCard">
            <div>
              <img src="/resources/img/paymentInCheck.png" alt="Payment Check" />
              <span>예매정보</span>
              <div class="division-line"></div>
            </div>
            <div>
              <img src="/resources/img/paymentInCheck.png" alt="Payment Check" />
              <span>확인/결제</span>
              <div class="division-line"></div>
            </div>
            <div>
              <img src="/resources/img/paymentInCheck.png" alt="Payment Check" />
              <span>예매완료</span>
            </div>
          </div>
        </div>
      </section>
      <!-- 본문 결제 바디 영역 -->
      <section>
        <div class="payment-card-body">
          <div style="display: flex;justify-content: space-between;">
            <h1 style="margin-left: 45px">예매완료</h1>
            <button class="button2" style="margin-right: 3%;">예매 확인</button>
          </div>
          <p style="padding: 35px 0px 20px 77px">승차권 정보</p>
          <div>
            <div class="payment-body-card-top">2024. 09. 10 화</div>
            <div class="payment-body-card-body" style="display: flex">
              <div class="payment-body-card-body-left">
                <div>
                  <img src="/resources/img/start_end.png" alt="" />
                </div>
                <div class="payment-left-location">
                  <h1>동서울</h1>
                  <h1>부산</h1>
                </div>
              </div>
              <div class="payment-body-card-body-right">
                <div>
                  <span>고속사</span>
                  <p>(주)중앙고속</p>
                </div>
                <div>
                  <span>등급</span>
                  <p>우등</p>
                </div>
                <div>
                  <span>매수</span>
                  <p>일반 1명</p>
                </div>
                <div>
                  <span>좌석번호</span>
                  <p>7</p>
                </div>
              </div>
            </div>
       
          </div>
        </div>
      </section>
    </div>

    <script src="https://accounts.google.com/gsi/client" async defer></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <script src="/resources/js/mypage.js"></script>
    <script src="/resources/js/admin_header.js"></script>
  </body>
</html>
