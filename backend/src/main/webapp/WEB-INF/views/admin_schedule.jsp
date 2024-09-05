<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
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
    <link rel="stylesheet" href="../../resources/css/reset.css"/>
    <link rel="stylesheet" href="../../resources/css/header.css"/>
    <link rel="stylesheet" href="../../resources/css/admin_route.css"/>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
            crossorigin="anonymous"
    />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.11.5/gsap.min.js"></script>
    <style>
        .btn-check:checked + .btn.btn-outline-primary{
            background-color: #212954;
        }
        .btn-outline-primary:hover{
            background-color: #212954;
            border: none;
            color: white;
        }
        .btn-outline-primary{
            color: black;
            background-color: #f4f4f4;
            border: none;
        }
    </style>
    <title>Document</title>
</head>
<body>
<!-- 등록 모달 -->
<div
        class="modal fade"
        id="staticBackdrop"
        data-bs-backdrop="static"
        data-bs-keyboard="false"
        tabindex="-1"
        aria-labelledby="staticBackdropLabel"
        aria-hidden="true"
>
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1
                        class="modal-title fs-5"
                        id="staticBackdropLabel"
                        style="font: 900 15px Noto sans KR; color: black"
                >
                    배차 등록
                </h1>

            </div>
            <div class="modal-body">
                <label
                        class="form-label"
                        style="
                font: 750 14px Noto sans KR;
                margin: 0px 0px 10px 10px;
                color: #5c5c5c;
              "
                >
                    노선 선택
                </label>
                <div class="input-group mb-3">
                    <select
                            class="form-select"
                            id="inputGroupSelectRoute"
                            style="
                  background-color: #f4f4f4;
                  border: none;
                  border-radius: 10px;
                  padding: 0rem, 1rem;
                  font: 600 15px Noto sans KR;
                  height: 5vh;
                "
                    >
                        <c:forEach var="item" items="${routeList}">
                            <option selected>${item.routeStartTerminal.terminalName}
                                -> ${item.routeEndTerminal.terminalName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="modal-body" style="padding: 0rem 1rem 1rem 1rem">
                <label
                        class="form-label"
                        style="
                font: 750 14px Noto sans KR;
                margin: 0px 0px 10px 10px;
                color: #5c5c5c;
              "
                >
                    요일 선택
                </label>
                <div id="dayCheckBox" style="margin-left: 10px">
                    <input type="checkbox" class="btn-check" id="btn-check-outlined-1" autocomplete="off">
                    <label class="btn btn-outline-primary" for="btn-check-outlined-1" >월</label>
                    <input type="checkbox" class="btn-check" id="btn-check-outlined-2" autocomplete="off">
                    <label class="btn btn-outline-primary" for="btn-check-outlined-2">화</label>
                    <input type="checkbox" class="btn-check" id="btn-check-outlined-3" autocomplete="off">
                    <label class="btn btn-outline-primary" for="btn-check-outlined-3">수</label>
                    <input type="checkbox" class="btn-check" id="btn-check-outlined-4" autocomplete="off">
                    <label class="btn btn-outline-primary" for="btn-check-outlined-4">목</label>
                    <input type="checkbox" class="btn-check" id="btn-check-outlined-5" autocomplete="off">
                    <label class="btn btn-outline-primary" for="btn-check-outlined-5">금</label>
                    <input type="checkbox" class="btn-check" id="btn-check-outlined-6" autocomplete="off">
                    <label class="btn btn-outline-primary" for="btn-check-outlined-6">토</label>
                    <input type="checkbox" class="btn-check" id="btn-check-outlined-7" autocomplete="off">
                    <label class="btn btn-outline-primary" for="btn-check-outlined-7">일</label>
                </div>
            </div>
            <div class="modal-body">
                <label
                        class="form-label"
                        style="
                font: 750 14px Noto sans KR;
                margin: 0px 0px 0px 10px;
                color: #5c5c5c;
              "
                >
                    출발 시간
                </label>
                <div
                        class="input-group mb-3"
                        style="display: flex; align-items: center"
                >
                    <select
                            class="form-select"
                            id="inputGroupSelectDeparture"
                            style="
                  background-color: #f4f4f4;
                  border: none;
                  border-radius: 10px;
                  padding: 0rem, 1rem;
                  font: 600 15px Noto sans KR;
                  height: 5vh;
                "
                    >
                        <option selected>00</option>
                        <option selected>01</option>
                        <option selected>02</option>
                        <option selected>03</option>
                        <option selected>04</option>
                        <option selected>05</option>
                        <option selected>06</option>
                        <option selected>07</option>
                        <option selected>08</option>
                        <option selected>09</option>
                        <option selected>10</option>
                        <option selected>11</option>
                        <option selected>12</option>
                        <option selected>13</option>
                        <option selected>14</option>
                        <option selected>15</option>
                        <option selected>16</option>
                        <option selected>17</option>
                        <option selected>18</option>
                        <option selected>19</option>
                        <option selected>20</option>
                        <option selected>21</option>
                        <option selected>22</option>
                        <option selected>23</option>
                    </select>
                    <div class="form-floating" style="margin-left: 10px">
                        <input
                                type="text"
                                class="form-control"
                                id="floatingInputDeparture"
                                placeholder="00"
                                style="
                    background-color: #f4f4f4;
                    border: none;
                    border-radius: 10px;
                    padding: 0rem, 1rem;
                    font: 600 15px Noto sans KR;
                    height: 5vh;
                  "
                        />
                    </div>
                </div>
            </div>
            <div class="modal-body">
                <label
                        class="form-label"
                        style="
                font: 750 14px Noto sans KR;
                margin: 0px 0px 10px 10px;
                color: #5c5c5c;
              "
                >
                    도착 시간
                </label>
                <div
                        class="input-group mb-3"
                        style="display: flex; align-items: center"
                >
                    <select
                            class="form-select"
                            id="inputGroupSelectArrival"
                            style="
                  background-color: #f4f4f4;
                  border: none;
                  border-radius: 10px;
                  padding: 0rem, 1rem;
                  font: 600 15px Noto sans KR;
                  height: 5vh;
                "
                    >
                        <option selected>00</option>
                        <option selected>01</option>
                        <option selected>02</option>
                        <option selected>03</option>
                        <option selected>04</option>
                        <option selected>05</option>
                        <option selected>06</option>
                        <option selected>07</option>
                        <option selected>08</option>
                        <option selected>09</option>
                        <option selected>10</option>
                        <option selected>11</option>
                        <option selected>12</option>
                        <option selected>13</option>
                        <option selected>14</option>
                        <option selected>15</option>
                        <option selected>16</option>
                        <option selected>17</option>
                        <option selected>18</option>
                        <option selected>19</option>
                        <option selected>20</option>
                        <option selected>21</option>
                        <option selected>22</option>
                        <option selected>23</option>
                    </select>
                    <div class="form-floating" style="margin-left: 10px">
                        <input
                                type="text"
                                class="form-control"
                                id="floatingInputArrival"
                                placeholder="00"
                                style="
                    background-color: #f4f4f4;
                    border: none;
                    border-radius: 10px;
                    padding: 0rem, 1rem;
                    font: 600 15px Noto sans KR;
                    height: 5vh;
                  "
                        />
                    </div>
                </div>
            </div>
            <div class="modal-body">
                <label class="form-label" style="font: 750 14px Noto sans KR; margin: 0px 0px 10px 10px; color: #5c5c5c;">
                    운송회사
                </label>
                <div class="input-group mb-3" style="display: flex; align-items: center">
                    <select class="form-select" id="companySelect" style="background-color: #f4f4f4; border: none; border-radius: 10px; padding: 0rem, 1rem; margin-right: 1rem; font: 600 15px Noto sans KR; height: 5vh;">
                        <option value="">운송 회사</option>
                        <c:forEach var="item" items="${companyList}">
                            <option value="${item.companyName}">${item.companyName}</option>
                        </c:forEach>
                    </select>
                    <select class="form-select" id="busSelect" style="background-color: #f4f4f4; border: none; border-radius: 10px; padding: 0rem, 1rem; font: 600 15px Noto sans KR; height: 5vh;">
                        <option value="">버스 번호</option>
                    </select>
                </div>
            </div>
            <div class="modal-body">
                <label
                        class="form-label"
                        style="
                font: 750 14px Noto sans KR;
                margin: 0px 0px 10px 10px;
                color: #5c5c5c;
              "
                >
                    가격
                </label>
                <div class="form-floating">
                    <input
                            type="text"
                            class="form-control"
                            id="floatingInputPrice"
                            placeholder="가격을 입력해주세요."
                            style="
                  background-color: #f4f4f4;
                  border: none;
                  border-radius: 10px;
                  padding: 0rem, 1rem;
                  font: 600 15px Noto sans KR;
                  height: 5vh;
                "
                    />
                </div>
            </div>

            <div class="modal-footer">
                <button
                        type="button"
                        class="btn btn-secondary"
                        data-bs-dismiss="modal"
                >
                    취소
                </button>
                <button
                        type="button"
                        class="btn btn-primary"
                        style="background-color: #212954"
                        data-bs-dismiss="modal"
                        onclick="submitFormData()"
                >
                    저장
                </button>
            </div>
        </div>
    </div>
</div>
<!-- 수정 모달 -->
<div
        class="modal fade"
        id="staticBackdrop-2"
        data-bs-backdrop="static"
        data-bs-keyboard="false"
        tabindex="-1"
        aria-labelledby="staticBackdropLabel"
        aria-hidden="true"
>
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1
                        class="modal-title fs-5"
                        id="staticBackdropLabel2"
                        style="font: 900 15px Noto sans KR; color: black"
                >
                    배차 정보 수정
                </h1>
                <button
                        type="button"
                        class="btn-primary"
                        style="
                width: 5rem;
                height: 2rem;
                background-color: #dc2e2e;
                border: none;
                border-radius: 0.25rem;"
                data-bs-dismiss="modal"
                onclick="deleteSchedule()"
                >
                    배차 삭제
                </button>
            </div>
            <div class="modal-body">
                <label
                        class="form-label"
                        style="
                font: 750 14px Noto sans KR;
                margin: 0px 0px 10px 10px;
                color: #5c5c5c;
              "
                >
                    노선 선택
                </label>
                <div class="input-group mb-3">
                    <select
                            class="form-select"
                            id="updateGroupSelectRoute"
                            style="
                  background-color: #f4f4f4;
                  border: none;
                  border-radius: 10px;
                  padding: 0rem, 1rem;
                  font: 600 15px Noto sans KR;
                  height: 5vh;
                "
                    >
                        <c:forEach var="item" items="${routeList}">
                            <option selected>${item.routeStartTerminal.terminalName}
                                -> ${item.routeEndTerminal.terminalName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="modal-body">
                <label
                        class="form-label"
                        style="
                font: 750 14px Noto sans KR;
                margin: 0px 0px 0px 10px;
                color: #5c5c5c;
              "
                >
                    출발 시간
                </label>
                <div
                        class="input-group mb-3"
                        style="display: flex; align-items: center"
                >
                    <select
                            id="startTimeSelecter"
                            class="form-select"
                            style="
                  background-color: #f4f4f4;
                  border: none;
                  border-radius: 10px;
                  padding: 0rem, 1rem;
                  font: 600 15px Noto sans KR;
                  height: 5vh;
                "
                    >
                        <option selected>00</option>
                        <option>01</option>
                        <option>02</option>
                        <option>03</option>
                        <option>04</option>
                        <option>05</option>
                        <option>06</option>
                        <option>07</option>
                        <option>08</option>
                        <option>09</option>
                        <option>10</option>
                        <option>11</option>
                        <option>12</option>
                        <option>13</option>
                        <option>14</option>
                        <option>15</option>
                        <option>16</option>
                        <option>17</option>
                        <option>18</option>
                        <option>19</option>
                        <option>20</option>
                        <option>21</option>
                        <option>22</option>
                        <option>23</option>
                    </select>
                    <div class="form-floating" style="margin-left: 10px">
                        <input
                                type="text"
                                class="form-control"
                                id="floatingUpdateDepature"
                                placeholder="00"
                                style="
                    background-color: #f4f4f4;
                    border: none;
                    border-radius: 10px;
                    padding: 0rem, 1rem;
                    font: 600 15px Noto sans KR;
                    height: 5vh;
                  "
                        />
                    </div>
                </div>
            </div>
            <div class="modal-body">
                <label
                        class="form-label"
                        style="
                font: 750 14px Noto sans KR;
                margin: 0px 0px 10px 10px;
                color: #5c5c5c;
              "
                >
                    도착 시간
                </label>
                <div
                        class="input-group mb-3"
                        style="display: flex; align-items: center"
                >
                    <select
                            class="form-select"
                            id="endTimeSelecter"
                            style="
                  background-color: #f4f4f4;
                  border: none;
                  border-radius: 10px;
                  padding: 0rem, 1rem;
                  font: 600 15px Noto sans KR;
                  height: 5vh;
                "
                    >
                        <option selected>00</option>
                        <option selected>01</option>
                        <option selected>02</option>
                        <option selected>03</option>
                        <option selected>04</option>
                        <option selected>05</option>
                        <option selected>06</option>
                        <option selected>07</option>
                        <option selected>08</option>
                        <option selected>09</option>
                        <option selected>10</option>
                        <option selected>11</option>
                        <option selected>12</option>
                        <option selected>13</option>
                        <option selected>14</option>
                        <option selected>15</option>
                        <option selected>16</option>
                        <option selected>17</option>
                        <option selected>18</option>
                        <option selected>19</option>
                        <option selected>20</option>
                        <option selected>21</option>
                        <option selected>22</option>
                        <option selected>23</option>
                    </select>
                    <div class="form-floating" style="margin-left: 10px">
                        <input
                                type="text"
                                class="form-control"
                                id="floatingUpdateArrival"
                                placeholder="00"
                                style="
                    background-color: #f4f4f4;
                    border: none;
                    border-radius: 10px;
                    padding: 0rem, 1rem;
                    font: 600 15px Noto sans KR;
                    height: 5vh;
                  "
                        />
                    </div>
                </div>
            </div>
            <div class="modal-body">
                <label class="form-label" style="font: 750 14px Noto sans KR; margin: 0px 0px 10px 10px; color: #5c5c5c;">
                    운송회사
                </label>
                <div class="input-group mb-3" style="display: flex; align-items: center">
                    <select class="form-select" id="companySelect-1" style="background-color: #f4f4f4; border: none; border-radius: 10px; padding: 0rem, 1rem; margin-right: 1rem; font: 600 15px Noto sans KR; height: 5vh;">
                        <option value="">운송 회사</option>
                        <c:forEach var="item" items="${companyList}">
                            <option value="${item.companyName}">${item.companyName}</option>
                        </c:forEach>
                    </select>
                    <select class="form-select" id="busSelect-1" style="background-color: #f4f4f4; border: none; border-radius: 10px; padding: 0rem, 1rem; font: 600 15px Noto sans KR; height: 5vh;">
                        <option value="">버스 번호</option>
                    </select>
                </div>
            </div>
            <div class="modal-body">
                <label
                        class="form-label"
                        style="
                font: 750 14px Noto sans KR;
                margin: 0px 0px 10px 10px;
                color: #5c5c5c;
              "
                >
                    가격
                </label>
                <div class="form-floating">
                    <input
                            type="text"
                            class="form-control"
                            id="floatingUpdatePrice"
                            placeholder="가격을 입력해주세요."
                            style="
                  background-color: #f4f4f4;
                  border: none;
                  border-radius: 10px;
                  padding: 0rem, 1rem;
                  font: 600 15px Noto sans KR;
                  height: 5vh;
                "
                    />
                </div>
            </div>

            <div class="modal-footer">
                <button
                        type="button"
                        class="btn btn-secondary"
                        data-bs-dismiss="modal"
                >
                    취소
                </button>
                <button
                        type="button"
                        class="btn btn-primary"
                        style="background-color: #212954"
                        data-bs-dismiss="modal"
                        onclick="submitFormData()"
                >
                    저장
                </button>
            </div>
        </div>
    </div>
</div>

<admin-header-component></admin-header-component>
<div id="body_wrap">
    <section id="admin_info"></section>
    <section id="admin_all">
        <div class="container">
            <h4 style="font: 800 25px Noto Sans KR">배차 관리</h4>
            <div class="main-body">
                <div class="row">
                    <div class="col-lg-3">
                        <div class="card">
                            <div class="card-body">
                                <div
                                        class="d-flex flex-column align-items-center text-center"
                                >
                                    <img
                                            src="../../resources/img/logo.png"
                                            alt="Admin image"
                                            class="rounded-circle p-1 bg-light"
                                            width="110"
                                    />
                                    <div class="mt-3">
                                        <h4>관리자</h4>
                                        <p>관리자님 환영합니다</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-9">
                        <div class="card">
                            <div class="card-body">
                                <div class="container rcard-header" style="width: 94%">
                                    <div
                                            class="col-sm-9 text-secondary"
                                            style="
                          display: flex;
                          justify-content: space-between;
                          width: 100%;
                        "
                                    >
                        <span
                                style="font: 700 20px Noto Sans KR; color: #5c5c5c"
                        >
                          배차 조회
                        </span>
                                        <button
                                                type="button"
                                                class="btn btn-primary"
                                                data-bs-toggle="modal"
                                                data-bs-target="#staticBackdrop"
                                                style="
                            background-color: #212954 !important;
                            font: 400 20px Noto Sans KR;
                            color: #f9fafc !important;
                            width: 80px;
                          "
                                        >
                                            배차 등록
                                        </button>
                                    </div>
                                </div>
                                <div class="container" id="container">
                                    <div class="container m-4">
                                        <div class="row height d-flex">
                                            <div class="col-md-12">
                                                <div class="form">
                                                    <div class="input-group">
                                <span
                                        class="input-group-text"
                                        id="basic-addon1"
                                        style="background-color: #f4f4f4"
                                >
                                  <i
                                          class="fas fa-search"
                                          style="color: #c8c8c8; opacity: 0.4"
                                  ></i>
                                </span>
                                                        <input
                                                                type="text"
                                                                class="form-control form-input"
                                                                placeholder="배차ID를 입력해주세요"
                                                                id="search-box"
                                                                style=" background-color: #f9fafc; font: 500 13px Noto Sans KR;"
                                                        />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="table-responsive mt-4">
                                            <table class="table table-hover align-middle">
                                                <thead id="table-head">
                                                <tr>
                                                    <th scope="col" id="route-id" class="col-md-1">
                                                        노선ID&nbsp;&nbsp;<i class="fas fa-sort"></i>
                                                    </th>
                                                    <th scope="col" id="schedule-id" class="col-md-2">
                                                        배차ID&nbsp;&nbsp;<i class="fas fa-sort"></i>
                                                    </th>
                                                    <th scope="col" id="schedule-start-location" class="col-md-2">
                                                        출발지&nbsp;&nbsp;<i class="fas fa-sort"></i>
                                                    </th>
                                                    <th scope="col" id="schedule-end-location" class="col-md-2">
                                                        도착지&nbsp;&nbsp;<i class="fas fa-sort"></i>
                                                    </th>
                                                    <th scope="col" id="schedule-departure-time" class="col-md-2">
                                                        출발 시간&nbsp;&nbsp;<i class="fas fa-sort"></i>
                                                    </th>
                                                    <th scope="col" id="schedule-arrival-time" class="col-md-2">
                                                        도착 시간&nbsp;&nbsp;<i class="fas fa-sort"></i>
                                                    </th>
                                                    <th scope="col" id="schedule-price" class="col-md-1">
                                                        가격&nbsp;&nbsp;<i class="fas fa-sort"></i>
                                                    </th>
                                                    <th scope="col" id="schedule-detail" ></th>
                                                </tr>
                                                </thead>

                                            <tbody id="table-body">
                                            <c:forEach var="item" items="${scheduleList}">
                                                <tr>
                                                    <td id="schedule-id-value" >${item.scheduleId}</td>
                                                    <td id="user-id">${item.routeId}</td>
                                                    <td>${item.startName}</td>
                                                    <td>${item.endName}</td>
                                                    <td>${item.departureTime}</td>
                                                    <td>${item.arrivalTime}</td>
                                                    <td>${item.price}</td>
                                                    <td>
                                                        <button
                                                                type="button"
                                                                class="btn btn-primary"
                                                                data-bs-toggle="modal"
                                                                data-bs-target="#staticBackdrop-2"
                                                                style="background-color: #212954 !important; font: 400 20px Noto Sans KR; color: #f9fafc !important; width: 50px;"
                                                                onclick="getFormData(this)"
                                                        >
                                                            수정
                                                        </button>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                            </table>
                                            <div id="page">
                                                <ul class="pagination">
                                                    <li class="page-item">
                                                        <a class="page-link prev" href="#"> <<</a>
                                                    </li>
                                                    <li class="page-item" id="page-number">
                                                        <a class="page-link" href="#">1</a>
                                                    </li>
                                                    <li class="page-item">
                                                        <a class="page-link next" href="#">>></a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
<script>
    const busData = {};
    <c:forEach var="item" items="${busList}">
    if (!busData["${item.companyName}"]) {
        busData["${item.companyName}"] = [];
    }
    busData["${item.companyName}"].push("${item.busNumber}");
    </c:forEach>

    document.getElementById('companySelect').addEventListener('change', function() {
        const selectedCompany = this.value;
        const busSelect = document.getElementById('busSelect');

        busSelect.innerHTML = '<option value="">버스 번호</option>';

        if (busData[selectedCompany]) {
            busData[selectedCompany].forEach(function(busNumber) {
                const option = document.createElement('option');
                option.value = busNumber;
                option.textContent = busNumber;
                busSelect.appendChild(option);
            });
        }
    });
    document.getElementById('companySelect-1').addEventListener('change', function() {
        const selectedCompany = this.value;
        const busSelect = document.getElementById('busSelect-1');

        busSelect.innerHTML = '<option value="">버스 번호</option>';

        if (busData[selectedCompany]) {
            busData[selectedCompany].forEach(function(busNumber) {
                const option = document.createElement('option');
                option.value = busNumber;
                option.textContent = busNumber;
                busSelect.appendChild(option);
            });
        }
    });
</script>

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"
></script>
<script src="https://accounts.google.com/gsi/client" async defer></script>
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<script src="../../resources/js/admin_header.js"></script>
<script src="../../resources/js/admin_schedule.js"></script>
<script src="../../resources/js/admin_paging.js"></script>
</body>
</html>
