package com.unibus.reservation.controller;

import ch.qos.logback.core.model.Model;
import com.unibus.reservation.dto.ScheduleDto;
import com.unibus.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/reservation/*")
public class ReservationController {

    private final ReservationService reservationService;

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
