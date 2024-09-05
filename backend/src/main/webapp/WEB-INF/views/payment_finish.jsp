<%@ page import="com.unibus.config.PrincipalDetail" %>
<%@ page import="org.springframework.security.authentication.AnonymousAuthenticationToken" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link rel="stylesheet" href="/resources/css/reset.css" />
    <link rel="stylesheet" href="/resources/css/header.css" />
    <link rel="stylesheet" href="/resources/css/payment.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.11.5/gsap.min.js"></script>
    <title>결제 페이지</title>
  </head>
  <body>
    <admin-header-component></admin-header-component>
    ${reservation}
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
            <c:if test="${!username.equals('Guest')}">
              <button class="button2" style="margin-right: 3%;" onclick="location.href='/user/mypage'">예매 확인</button>
            </c:if>
            <c:if test="${username.equals('Guest')}">
              <button class="button2" style="margin-right: 3%;" onclick="location.href='/nonuser/mypage'">예매 확인</button>
            </c:if>
          </div>
          <p style="padding: 35px 0px 20px 77px">승차권 정보</p>
          <div>
            <div class="payment-body-card-top" id="reservationStartDate">${r.reservationStartDate}</div>
            <div class="payment-body-card-body" style="display: flex">
              <div class="payment-body-card-body-left">
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
                <div class="payment-left-location">
                  <h1 id="startTerminal">${r.startTerminal}</h1>
                  <h1 id="endTerminal">${r.endTerminal}</h1>
                </div>
              </div>
              <div class="payment-body-card-body-right">
                <div>
                  <span>고속사</span>
                  <p id="companyName">${r.companyName}</p>
                </div>
                <div>
                  <span>등급</span>
                  <p id="busGradeName">${r.busGradeName}</p>
                </div>
                <div>
                  <span>매수</span>
                  <p><span id="ticketCount">${r.ticketCount}</span>명</p>
                </div>
                <div>
                  <span>좌석번호</span>
                  <p id="seatNumber">
                    <c:forEach var="num" items="${r.seatNumber}" varStatus="status">
                      ${num}<c:if test="${!status.last}"> ,</c:if>
                    </c:forEach>
                  </p>

                </div>
                <div>
                  <span>결제 금액</span>
                  <p id="totalPrice"><fmt:formatNumber value="${r.totalPrice}" pattern="#,##0"></fmt:formatNumber> 원</p>
                </div>
              </div>
            </div>
       
          </div>
        </div>
      </section>
    </div>
    <script src="https://accounts.google.com/gsi/client" async defer></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <script src="/resources/js/payment_finish.js"></script>
    <script src="/resources/js/admin_header.js"></script>
    <%
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String username = "Guest"; // 기본값을 Guest로 설정

      if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
        // 로그인된 사용자 처리
        PrincipalDetail userDetails = (PrincipalDetail) authentication.getPrincipal();
        username = userDetails.getUsername();
      }
    %>
    <div class="hidden">
      <input type="hidden" value="<%=username%>" id="memberId">
    </div>

  </body>
</html>
