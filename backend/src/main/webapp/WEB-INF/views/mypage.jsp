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
    <link rel="stylesheet" href="/resources/css/mypage.css" />
    <link rel="stylesheet" href="/resources/css/header.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/gsap/3.11.5/gsap.min.js'></script>
    <title>Document</title>
  </head>

  <body>
    <user-header-component>
    </user-header-component>
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
                  <div class="d-flex flex-column align-items-center text-center mt-4">
                    <img
                      src="https://bootdey.com/img/Content/avatar/avatar6.png"
                      alt="Admin"
                      class="rounded-circle p-1 bg-primary"
                      width="110"
                    />
                    <div class="mt-1 profile_btn_area">
                      <h4 class="mt-2 mb-0">${member.memberName}</h4>
                      <p>${member.memberEmail}</p>
                      <button class="btn_mypage fs-5 text-center mt-3 mb-3" onclick="location.href='/user/mypage'">마이페이지</button>
                      <button class="btn_edit_member text-center fs-5 mb-3" data-bs-toggle="modal" data-bs-target="#cancelModal">회원정보수정</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>



            <div class="col-lg-9">
              <div class="card">
                <div class="card-body p-4">
                  <div class="card_body_header mb-4">
                    <div class="card_header_text ps-2"><h5>회원정보</h5></div>
                  </div>
                  <div class="card_body_main  user_info">
                      <div class="id_area col-lg-6 align-items-center user_info_id">
                        <div class="id_header col-lg-6 ps-5 ">아이디</div>
                        <div class="id_text col-lg-6">${member.memberId}</div>
                      </div>
                      <div class="tel_area col-lg-6 align-items-center user_info_pn">
                        <div class="tel_header col-lg-5 ps-5">핸드폰번호</div>
                        <div class="tel_text col-lg-7">${member.memberTel}</div>
                      </div>
                      <div class="name_area col-lg-6 align-items-center user_info_name">
                        <div class="name_header col-lg-6 ps-5">이름</div>
                        <div class="name_text col-lg-6">${member.memberName}</div>
                      </div>
                      <div class="email_area col-lg-6 align-items-center user_info_email">
                        <div class="email_header col-lg-5 ps-5">이메일</div>
                        <div class="email_text col-lg-7">${member.memberEmail}</div>
                      </div>
                  </div>
                </div>
              </div>
              <div class="card" >
                <div class="card-body mt-2" id="second_card">
                  <div class="card_body_header mb-4 ps-2 d-flex justify-content-between align-items-center">
                    <div class="card_header_text"><h5 class="mb-0">승차권 예매 현황</h5></div>
                    <div class="more_btn_area">
                      <button class="more_btn me-2 pt-0 pb-0">더보기</button>
                    </div>
                  </div>
                  <table class="table border border-1">
                    <colgroup>
                      <col style="width: 15%;">
                      <col style="width: 15%;">
                      <col style="width: 15%;">
                      <col style="width: 15%;">
                      <col style="width: 15%;">
                      <col style="width: 13%;">
                      <col style="width: 12%;">
                    </colgroup>
                    <thead>
                      <tr>
                        <th scope="col" class="text-center">출발일</th>
                        <th scope="col" class="text-center">도착일</th>
                        <th scope="col" class="text-center">출발지</th>
                        <th scope="col" class="text-center">도착지</th>
                        <th scope="col" class="text-center">출발시간</th>
                        <th scope="col" class="text-center">매수</th>
                        <th scope="col" class="text-center">금액</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <th scope="row" class="text-center">1</th>
                        <td class="text-center">Mark</td>
                        <td class="text-center">Mark</td>
                        <td class="text-center">Otto</td>
                        <td class="text-center">Otto</td>
                        <td class="text-center">@mdo</td>
                        <td class="text-center">@mdo</td>
                      </tr>
                      <tr>
                        <th scope="row" class="text-center">1</th>
                        <td class="text-center">Mark</td>
                        <td class="text-center">Mark</td>
                        <td class="text-center">Otto</td>
                        <td class="text-center">Otto</td>
                        <td class="text-center">@mdo</td>
                        <td class="text-center">@mdo</td>
                      </tr>
                      <tr>
                        <th scope="row" class="text-center">1</th>
                        <td class="text-center">Mark</td>
                        <td class="text-center">Mark</td>
                        <td class="text-center">Otto</td>
                        <td class="text-center">Otto</td>
                        <td class="text-center">@mdo</td>
                        <td class="text-center">@mdo</td>
                      </tr><tr>
                        <th scope="row" class="text-center">1</th>
                        <td class="text-center">Mark</td>
                        <td class="text-center">Mark</td>
                        <td class="text-center">Otto</td>
                        <td class="text-center">Otto</td>
                        <td class="text-center">@mdo</td>
                        <td class="text-center">@mdo</td>
                      </tr>
                      <tr>
                        <th scope="row" class="text-center">1</th>
                        <td class="text-center">Mark</td>
                        <td class="text-center">Mark</td>
                        <td class="text-center">Otto</td>
                        <td class="text-center">Otto</td>
                        <td class="text-center">@mdo</td>
                        <td class="text-center">@mdo</td>
                      </tr>
                      
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <div class="modal fade" id="cancelModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <form id="memberForm">
            <label for="password_check" class="me-4">비밀번호 </label>
              <input type="hidden" name="id" value="${member.memberId}">
            <input type="password" id="password_check" name="password">
            <button class="password_check_btn ms-5" type="submit">비밀번호 확인</button>
            </form>
          </div>
        </div>
      </div>
    </div>



  




    <script src="https://accounts.google.com/gsi/client" async defer></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <script src="/resources/js/mypage.js"></script>
    <script src="/resources/js/user_header.js"></script>
  </body>
</html>