package com.unibus.reservation.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unibus.config.PrincipalDetail;
import com.unibus.reservation.domain.Payment;
import com.unibus.reservation.domain.Reservation;
import com.unibus.reservation.dto.ReservationTicketDto;
import com.unibus.reservation.service.ReservationService;
import com.unibus.user.domain.Member;
import com.unibus.user.domain.NonMember;
import com.unibus.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/check")
@AllArgsConstructor
@Slf4j
@RequestMapping("/check")
public class ReservationController {

    private final ReservationService reservationService;
    private final UserService userService;

    @GetMapping("/payment")
    public String payment(Model model) {

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
    public ResponseEntity<Boolean> reservationAdd(@PathVariable("user_id") String memberId, @RequestBody ObjectNode saveobj)
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
            log.info("reservation={}", reservation);
            return reservationService.reservationSave(reservation,memberId,nonMember,payment) == true ?
                    new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        } else {
            reservation.setMemberSeq(member.getMemberSeq());
            return reservationService.reservationSave(reservation,memberId,nonMember,payment) == true ?
                    new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

    }
}
