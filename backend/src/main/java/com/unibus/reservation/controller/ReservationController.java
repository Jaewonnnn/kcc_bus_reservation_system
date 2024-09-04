package com.unibus.reservation.controller;

import com.unibus.reservation.domain.Reservation;
import com.unibus.reservation.dto.ReservationSummaryDTO;
import com.unibus.reservation.dto.ScheduleDto;
import com.unibus.reservation.service.ReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/check")
@AllArgsConstructor
@Slf4j
public class ReservationController {

    private ReservationService reservationService;

    // 회원 예약 리스트 이동
    @GetMapping("/reservation/{user_id}")
    public String bookingHistory (@PathVariable String user_id, Model model) {
        return "mypage_reservation_list";
    }

    // 회원 예약 리스트 조회 - view 에서 받음
    @GetMapping("/reservation/data/{user_id}")
    @ResponseBody
    public List<ReservationSummaryDTO> getReservationData(@PathVariable String user_id) {
        log.info("ReservationController getReservationData() called" + reservationService.findReservationsByMember(user_id));
        return reservationService.findReservationsByMember(user_id);
    }

    // 비회원 예약 리스트 조회
    @GetMapping("/reservation/non/{reservation_id}")
    @ResponseBody
    public List<ReservationSummaryDTO> checkReservation (@PathVariable String reservation_id) {
        log.info("ReservationController checkReservation() called" + reservationService.findReservationsByNonUser(reservation_id));
        return reservationService.findReservationsByNonUser(reservation_id);
    }

    // 예약 수정 reservation_status 가 기본 1 임으로 0이 예약 취소
    @PatchMapping("/cancel/reservation/{reservation_id}")
    @ResponseBody
    public String cancelReservation (@PathVariable int reservation_id) {
        log.info("ReservationController cancelReservation() called");

        int result = reservationService.updateReservation(reservation_id);
        log.info("ReservationController cancelReservation() called" + result);
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        if (result  == 0) {
            return "실패";
        }
        return "성공";
    }

    // 예약 상세 조회
    @GetMapping("/reservation/detail/{reservation_id}")
    @ResponseBody
    public List<ReservationSummaryDTO> getReservationDetail(@PathVariable String reservation_id) {
        log.info("ReservationController getReservationDetail() called" + reservationService.findReservationsByMember(reservation_id));
        return reservationService. finDetailReservation(reservation_id);
    }

    @GetMapping("/payment")
    public String payment(Model model) {
        return "payment";
    }

    @GetMapping("/{schedule_id}")
    @ResponseBody
    public ResponseEntity<ScheduleDto> getReservation(@PathVariable("schedule_id") int scheduleId) {
        ScheduleDto dto = reservationService.getTicket(scheduleId);
        return dto == null ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
