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
              <img src="/resources/img/paymentTopThree.png" alt="Payment Check" />
              <span>예매완료</span>
            </div>
          </div>
        </div>
      </section>
      <!-- 본문 결제 바디 영역 -->
      <section>
        <div class="payment-card-body">
          <h1 style="margin-left: 45px">결제</h1>

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
            <!-- 결제 정보 입력 -->
            <div class="pay-info-container">
              <div class="payment-info">
                <h1 style="margin-left: 41px">결제 정보 입력</h1>
                <div class="payment-info-card">
                  <div class="card-img">
                    <div>
                      <img
                        src="/resources/img/TossPayments_Logo_Simple_Primary.png"
                        id="toxx"
                      />
                    </div>
                    <div><img src="/resources/img/badge_npay.svg" alt="" /></div>
                    <div><img src="/resources/img/payment_icon_yellow_large.png" /></div>
                  </div>
                  <!-- 카드결제 정보 입력 -->
                  <h1 style="margin-left: 41px; padding: 40px 0 20px 0">
                    카드결제
                  </h1>
                  <div class="card-type">
                    <span>카드종류</span>
                    <div>
                      <span>개인</span>
                      <span class="on personal"></span>
                      <span>법인</span>
                      <span class="legal"></span>
                    </div>
                  </div>
                  <div class="card-type">
                    <span>카드선택</span>
                    <div>
                      <span>카드를 선택하세요</span>
                    </div>
                  </div>
                  <div class="card-type">
                    <span>카드번호</span>
                    <div style="display: flex; align-items: center">
                      <input
                        type="text"
                        style="
                          background: #eee;
                          border: none;
                          text-align: center;
                        "
                        placeholder="입력"
                        maxlength="4"
                      />
                      <div
                        style="border-top: 2px solid #444444; width: 20px"
                      ></div>
                      <input
                        type="text"
                        style="
                          background: #eee;
                          border: none;
                          text-align: center;
                        "
                        placeholder="입력"
                        maxlength="4"
                      />
                      <div
                        style="border-top: 2px solid #444444; width: 20px"
                      ></div>
                      <input
                        type="password"
                        style="
                          background: #eee;
                          border: none;
                          text-align: center;
                        "
                        placeholder="입력"
                        maxlength="4"
                      />
                      <div
                        style="border-top: 2px solid #444444; width: 20px"
                      ></div>
                      <input
                        type="password"
                        style="
                          background: #eee;
                          border: none;
                          text-align: center;
                        "
                        placeholder="입력"
                        maxlength="4"
                      />
                    </div>
                  </div>
                  <!-- 오른쪽 결제하기 -->
                  <div style="display: flex; width: 96%">
                    <div class="card-type" style="width: 40%">
                      <span>유효기간 월(MONTH)</span>
                      <input
                        type="text"
                        style="
                          background: #eee;
                          border: none;
                          text-align: right;
                        "
                        placeholder="2자리 입력(MM)"
                        maxlength="2"
                      />
                    </div>
                    <div class="card-type" style="width: 40%">
                      <span>유효기간 월(MONTH)</span>
                      <input
                        type="text"
                        style="
                          background: #eee;
                          border: none;
                          text-align: right;
                        "
                        placeholder="2자리 입력(MM)"
                        maxlength="2"
                      />
                    </div>
                  </div>
                  <div class="card-type" style="width: 33%">
                    <span>카드 비밀번호</span>
                    <input
                      type="password"
                      style="background: #eee; border: none; text-align: right"
                      placeholder="비밀번호 앞 2자리 입력"
                      maxlength="2"
                    />
                  </div>
                  <div class="card-type">
                    <span>생년월일 6자리(YYYYMMDD)</span>
                    <div>
                      <input
                        type="text"
                        style="background: #eee; border: none; width: 200px"
                        placeholder="예) 1980년 11월 11일 > 801111"
                      />
                    </div>
                  </div>
                </div>
              </div>
              <div class="payment-amount">
                <div class="amount-body">
                  <div>
                    <h2 style="font-size: 1.5em">예매 금액</h2>

                    <p style="font-weight: bold">43,000원</p>
                  </div>
                  <div>
                    <h2 style="font-size: 1.5em">총 결제 금액</h2>
                    <p style="font-weight: bold">43,000원</p>
                  </div>
                  <button class="button2">결제 하기</button>
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
