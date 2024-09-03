<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdn.iamport.kr/v1/iamport.js"></script>
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

          <p style="padding: 35px 0px 20px 50px; font: 600 30px 'Nato Sans KR';" >승차권 정보</p>
          <div>
            <div class="payment-body-card-top" id="scheduleStartDate"></div>
            <div class="payment-body-card-body" style="display: flex">
              <div class="payment-body-card-body-left">
                <div>
                  <img src="/resources/img/start_end.png" alt="" />
                </div>
                <div class="payment-left-location">
                  <h1 id="startTerminal"></h1>
                  <h1 id="endTerminal"></h1>
                </div>
              </div>
              <div class="payment-body-card-body-right">
                <div>
                  <span>고속사</span>
                  <p id="companyName"></p>
                </div>
                <div>
                  <span>등급</span>
                  <p id="busGradeName"></p>
                </div>
                <div>
                  <span>매수</span>
                  <p>일반 <span class="count">1</span> 명</p>
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
                    <div><a type="button" id="kakaoPayment"><img src="/resources/img/payment_icon_yellow_large.png"/></a></div>
                  </div>
                  <!-- 카드결제 정보 입력 -->
                  <h1 style="margin-left: 41px; padding: 40px 0 20px 0">
                    카드결제
                  </h1>
                  <div class="card-type">
                    <span>카드선택</span>
                    <select>
                      <option selected>카드선택</option>
                      <option value="KB국민은행">KB국민은행</option>
                      <option value="우리은행">우리은행</option>
                      <option value="IBK 기업은행">IBK 기업은행</option>
                      <option value="NH 농협은행">NH 농협은행</option>
                      <option value="하나은행">하나은행</option>
                      <option value="신한은행">신한은행</option>
                      <option value="카카오뱅크">카카오뱅크</option>
                      <option value="수협은행">수협은행</option>
                    </select>
                  </div>
                  <div class="card-type">
                    <span>카드번호</span>
                    <div style="display: flex; align-items: center" class="input_card">
                      <input
                        type="text"
                        style="
                          background: #eee;
                          border: none;
                          height: 40px;
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
                          height: 40px;
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
                          height: 40px;
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
                          height: 40px;
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
                      <label for="two_month">유효기간 월(MONTH)</label>
                      <input
                        type="text"
                        style="
                          background: #eee;
                          border: none;
                          text-align: right;
                        "
                        id="two_month"
                        placeholder="2자리 입력(MM)"
                        maxlength="2"
                      />
                    </div>
                    <div class="card-type" style="width: 40%">
                      <label for="two_year">유효기간 년(YEAR)</label>
                      <input
                        type="text"
                        style="
                          background: #eee;
                          border: none;
                          text-align: right;
                        "
                        placeholder="2자리 입력(YY)"
                        maxlength="2"
                        id="two_year"
                      />
                    </div>
                  </div>
                  <div class="card-type" style="width: 33%">
                    <label for="card_password">카드 비밀번호</label>
                    <input
                      type="password"
                      style="background: #eee; border: none; text-align: right"
                      placeholder="비밀번호 앞 2자리 입력"
                      maxlength="2"
                      id="card_password"
                    />
                  </div>
                  <div class="card-type">
                    <label for="birth">생년월일 6자리(YYYYMMDD)</label>
                    <div>
                      <input
                        type="text"
                        style="background: #eee; border: none; width: 250px"
                        placeholder="예) 1980년 11월 11일 > 801111"
                        id="birth"
                      />
                    </div>
                  </div>
                </div>
              </div>
              <div class="payment-amount">
                <div class="amount-body">
                  <div>
                    <h2 style="font-size: 1.5em">예매 금액</h2>
                    <p style="font-weight: bold; margin-top: 30px;">
                      <span id="schedulePrice">

                      </span>원</p>
                  </div>
                  <div>
                    <h2 style="font-size: 1.5em">총 결제 금액</h2>
                    <p style="font-weight: bold; margin-top: 30px;">
                      <span id="price"></span>원</p>
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
    <script src="/resources/js/payment.js"></script>
    <script src="/resources/js/admin_header.js"></script>
  </body>
</html>
