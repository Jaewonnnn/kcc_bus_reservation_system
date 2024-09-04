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
    <link rel="stylesheet" href="/resources/css/mypage_reservation_list.css" />
    <link rel="stylesheet" href="/resources/css/header.css" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
      crossorigin="anonymous"
    />
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
      integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    ></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.11.5/gsap.min.js"></script>
    <title>Document</title>
  </head>

  <body>
    <user-header-component> </user-header-component>
    <section id="admin_all">
      <div class="header_area container mb-4 pt-5">
        <h3 class="header_text ms-3">마이페이지</h3>
      </div>

      <div class="container">
        <div class="main-body">
          <div class="row">
            <div class="col-lg-3">
              <div class="card">
                <div class="card-body">
                  <div
                    class="d-flex flex-column align-items-center text-center mt-4"
                  >
                    <img
                      src="https://bootdey.com/img/Content/avatar/avatar6.png"
                      alt="Admin"
                      class="rounded-circle p-1 bg-primary"
                      width="110"
                    />
                    <div class="mt-1 profile_btn_area">
                      <h4 class="mt-2 mb-0">원승언</h4>
                      <p>dnjstmddjs12@naver.com</p>
                      <button class="btn_mypage fs-5 text-center mt-3 mb-3">
                        마이페이지
                      </button>
                      <button class="btn_edit_member text-center fs-5 mb-3">
                        회원정보수정
                      </button>
                      <button class="btn_delete_member text-center fs-5">
                        회원탈퇴
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div class="col-lg-9">
              <div class="card">
                <div class="card-body mt-2" id="second_card">
                  <div
                    class="card_body_header mb-4 ps-2 d-flex justify-content-between align-items-center"
                  >
                    <div class="card_header_text">
                      <h5 class="mb-0">승차권 예매 현황</h5>
                    </div>
                    <div class="more_btn_area">
                      <button class="more_btn me-2 pt-0 pb-0">삭제</button>
                    </div>
                  </div>
                  <table class="table border border-1">
                    <colgroup>
                      <col style="width: 12%">
                      <col style="width: 12%;">
                      <col style="width: 12%;">
                      <col style="width: 12%;">
                      <col style="width: 12%;">
                      <col style="width: 12%;">
                      <col style="width: 12%;">
                      <col style="width: 12%;">
                    </colgroup>
                    <thead>
                      <tr>
                        <th scope="col" class="text-center">
                          <input
                            class="form-check-input"
                            type="checkbox"
                            value=""
                            id="flexCheckDefault"
                          />
                        </th>
                        <th scope="col" class="text-center">출발일</th>
                        <th scope="col" class="text-center">출발지</th>
                        <th scope="col" class="text-center">도착지</th>
                        <th scope="col" class="text-center">출발시간</th>
                        <th scope="col" class="text-center">매수</th>
                        <th scope="col" class="text-center">금액</th>
                        <th scope="col" class="text-center"></th>
                      </tr>
                    </thead>
                    <tbody id="my-reservation-list">

                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    

     <!-- Modal -->
     <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header d-flex justify-content-between">
            <h1 class="modal-title ms-0" id="staticBackdropLabel">승차권 상세보기</h1>
            <button type="button" class="close_btn text-center" data-bs-dismiss="modal">닫기</button>
          </div>
          <div class="modal-body">
            <table class="table table-borderless">
              <thead>
                <tr>
                  <th scope="col" class="text-left" id="scheduleDate"></th>
                  <th scope="col" class="text-end"></th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td class="text-left">출발지</td>
                  <td class="text-end" id="startTerminalName"></td>
                </tr>
                <tr>
                  <td class="text-left">도착지</td>
                  <td class="text-end" id="endTerminalName">부산</td>
                </tr>
                <tr>
                  <td class="text-left">출발시간</td>
                  <td class="text-end" id="startTime">12:30</td>
                </tr>
                <tr>
                  <td class="text-left">매수</td>
                  <td class="text-end" id="dataLength">2매</td>
                </tr>
                <tr>
                  <td class="text-left">좌석번호</td>
                  <td class="text-end" id="seatNumber">21석, 22석</td>
                </tr>
                <tr>
                  <td class="text-left">금액</td>
                  <td class="text-end" id="totalPrice"></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>        
     

  




    <script src="https://accounts.google.com/gsi/client" async defer></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <script src="/resources/js/mypage_reservation_list.js"></script>
    <script src="/resources/js/user_header.js"></script>
  </body>
</html>
