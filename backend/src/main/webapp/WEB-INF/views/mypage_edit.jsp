<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link rel="stylesheet" href="/resources/css/mypage _edit.css" />
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
                      <button class="btn_delete_member text-center fs-5" data-bs-toggle="modal" data-bs-target="#cancelModal">회원탈퇴</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>



            <div class="col-lg-9">
              <div class="card" >
                <div class="card-body mt-2" id="second_card">
                  <div class="card_body_header mb-4 ps-2 d-flex justify-content-between align-items-center">
                    <div class="card_header_text"><h5 class="mb-0">회원정보수정</h5></div>
                    <button class="change_member_btn">변경하기</button>
                  </div>
                  <div class="table_div">
                    <table class="table mt-2">
                      <colgroup>
                        <col style="width: 40%;">
                        <col style="width: 60%;">
                      </colgroup>
                      <thead>
                        <tr>
                          <th scope="col" class="text-left">아이디</th>
                          <th scope="col" class="text-left" id="memberId">${member.memberId}</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td class="text-left">비밀번호</td>
                          <td class="text-left">
                              <button class="change_btn">비밀번호변경</button>
                              <div class="input_hide_area hide " >
                                <input type="password" id="hide_password_input" class="form-control hide_input" placeholder="변경할 비밀번호를 입력해주세요">
                                <button class="change_password_btn ms-3">변경하기</button>
                              </div>
                          </td>
                        </tr>
                        <tr>
                          <td class="text-left">이름</td>
                          <td class="text-left name"><input class="form-control text-center" id="memberName" type="text" placeholder="${member.memberName}"></td>
                        </tr>
                        <tr>
                          <td class="text-left">생년월일</td>
                          <td class="text-left birth"><fmt:formatDate value="${member.memberBirth}" pattern="yyyy년 MM월 dd일" /></td>
                        </tr><tr>
                          <td class="text-left">성별</td>
                          <td class="text-left ">
                              <label class="form-check-label men me-2" for="flexCheckMan">
                                남
                              </label>
                              <input class="form-check-input me-5" type="checkbox" value="남" id="flexCheckMan" checked>
                              <label class="form-check-label women me-2" for="flexCheckWoman">
                                여
                              </label>
                              <input class="form-check-input " type="checkbox" value="여" id="flexCheckWoman">
                          </td>
                        </tr>
                        <tr>
                          <td class="text-left">핸드폰번호</td>
                          <td class="text-left">
                            <div class="input_area d-flex align-items-center">
                              <input class="form-control text-center" id="firstTel" type="text" value="010" maxlength="3">
                              <i class="fa-solid fa-minus fa-xs me-2 ms-2"></i>
                              <c:set var="memberTel" value="${member.memberTel}"/>
                              <c:set var="parts" value="${fn:split(memberTel,'-')}"/>
                              <c:set var="middleNumber" value="${parts[1]}"/>
                              <c:set var="lastNumber" value="${parts[2]}"/>

                              <input class="form-control text-center" type="text" id="middleTel" placeholder="${middleNumber}" maxlength="4">
                              <i class="fa-solid fa-minus fa-xs me-2 ms-2"></i>
                              <input class="form-control text-center" type="text" id="lastTel" placeholder="${lastNumber}" maxlength="4">
                            </div>
                          </td>
                        </tr>
                        <tr>
                          <td class="text-left">이메일</td>
                          <td class="text-left">
                            <div class="input_email_area d-flex align-items-center">
                              <c:set var="email" value="${member.memberEmail}"/>
                              <c:set var="parts" value="${fn:split(email, '@')}"/>
                              <c:set var="email_first" value="${parts[0]}"/>
                              <c:set var="email_last" value="${parts[1]}"/>
                              <input class="form-control me-2 text-center" type="text" id="firstEmail" placeholder="${email_first}">
                              <div class="me-2">@</div>
                              <input class="form-control me-3 text-center" type="text" id="lastEmail" placeholder="${email_last}">
                              <input class="form-control text-center" type="text" placeholder="직접 입력">
                            </div>
                          </td>
                        </tr>
                        
                      </tbody>
                    </table>
                  </div>  
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    

    <!-- 모달창 -->
    <div class="modal fade" id="cancelModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">

              <label for="password_check" class="me-4">비밀번호 </label>
              <input type="password" id="password_check">
              <button class="password_check_btn ms-4" type="button">비밀번호 확인</button>

          </div>
        </div>
      </div>
    </div>
  




    <script src="https://accounts.google.com/gsi/client" async defer></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <script src="/resources/js/mypage_edit.js"></script>
    <script src="/resources/js/user_header.js"></script>
  </body>
</html>