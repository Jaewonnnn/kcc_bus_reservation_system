package com.unibus.reservation.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unibus.config.PrincipalDetail;
import com.unibus.reservation.domain.Payment;
import com.unibus.reservation.domain.Reservation;
import com.unibus.reservation.dto.ReservationFinishDto;
import com.unibus.reservation.dto.ReservationSummaryDTO;
import com.unibus.reservation.dto.ReservationTicketDto;
import com.unibus.reservation.dto.ScheduleDto;
import com.unibus.reservation.service.ReservationService;
import com.unibus.user.domain.Member;
import com.unibus.user.domain.NonMember;
import com.unibus.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/check")
public class ReservationController {

    private final ReservationService reservationService;
    private final UserService userService;

    @GetMapping("/payment")
    public String payment(Model model,
                          @RequestParam(required = false) String seatIds,
                          @RequestParam(required = false) Integer passengerCount,
                          @RequestParam(required = false) Integer totalAmount,
                          @RequestParam(required = false) String busGrade,
                          @RequestParam(required = false) String startTerminal,
                          @RequestParam(required = false) String endTerminal,
                          @RequestParam(required = false) Integer adults,
                          @RequestParam(required = false) Integer students,
                          @RequestParam(required = false) Integer children,
                          @RequestParam(required = false) String scheduleId) {
        model.addAttribute("seatIds", seatIds);
        model.addAttribute("passengerCount", passengerCount);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("busGrade", busGrade);
        model.addAttribute("startTerminal", startTerminal);
        model.addAttribute("endTerminal", endTerminal);
        model.addAttribute("adults", adults);
        model.addAttribute("students", students);
        model.addAttribute("children", children);
        model.addAttribute("scheduleId", scheduleId);

        return "payment";
    }



    @GetMapping("/{schedule_id}")
    @ResponseBody
    public ResponseEntity<ReservationTicketDto> getReservation(@PathVariable("schedule_id") int scheduleId) {
        ReservationTicketDto dto = reservationService.getTicket(scheduleId);
        return dto == null ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @PostMapping("/payment/{user_id}")
    @ResponseBody
    public ResponseEntity<Reservation> reservationAdd(@PathVariable("user_id") String memberId, @RequestBody ObjectNode saveobj)
            throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Payment payment = mapper.treeToValue(saveobj.get("payment"), Payment.class);
        Reservation reservation = mapper.treeToValue(saveobj.get("reservation"), Reservation.class);
        NonMember nonMember = mapper.treeToValue(saveobj.get("nonMember"), NonMember.class);
        Member member = userService.userInfo(memberId);


        if(member == null){
            log.info("nonMember ={}", nonMember);
            log.info("memberId={}", memberId);
            userService.nonUserSave(nonMember);
            reservation.setNonUserCode(nonMember.getNonUserCode());
            log.info("reservation={}", reservation.getSeatNumber());
            return reservationService.reservationSave(reservation,memberId,nonMember,payment) == true ?
                    new ResponseEntity<>(reservation, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            reservation.setMemberSeq(member.getMemberSeq());
            return reservationService.reservationSave(reservation,memberId,nonMember,payment) == true ?
                    new ResponseEntity<>(reservation, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/payment/finish/{paymentImpUid}")
    public ModelAndView paymentFinish(@PathVariable("paymentImpUid") int paymentImpUid) throws Exception {

        ReservationFinishDto dto = reservationService.finishReservation(paymentImpUid);
        if(dto == null){
            throw new Exception("NOT FOUND");
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("r", dto);
        mav.setViewName("payment_finish");
        return mav;

    }
    
    // 회원 예약 리스트 이동
    @GetMapping("/reservation/{user_id}")
    public String bookingHistory (@PathVariable String user_id, @AuthenticationPrincipal PrincipalDetail principalDetail, Model model) {
        Member member = userService.userInfo(principalDetail.getUsername());
        model.addAttribute("member", member);
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
    @GetMapping("/reservation/detail/{paymentImpUid}/{member_id}")
    @ResponseBody
    public ResponseEntity<ReservationSummaryDTO> getReservationDetail(@PathVariable("paymentImpUid") int paymentImpUid, @PathVariable("member_id") String memberId) {
        ReservationSummaryDTO dto = reservationService.finDetailReservation(paymentImpUid,memberId);
        return dto != null ? new ResponseEntity<>(dto, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    
}
