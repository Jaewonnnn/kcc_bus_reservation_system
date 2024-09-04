package com.unibus.reservation.controller;

import com.unibus.reservation.domain.Payment;
import com.unibus.reservation.service.PaymentService;
import com.unibus.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Payment> register(@RequestBody Payment payment) throws Exception {
        boolean flag = paymentService.paymentSave(payment);
        if (flag) {
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } else {
            throw new Exception("register fail");
        }
    }
}
