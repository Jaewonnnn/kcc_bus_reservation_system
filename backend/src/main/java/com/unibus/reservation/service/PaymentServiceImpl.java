package com.unibus.reservation.service;

import com.unibus.reservation.domain.Payment;
import com.unibus.reservation.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;


    @Override
    public Boolean paymentSave(Payment payment) {
        return paymentMapper.paymentSave(payment) > 0 ? true : false;
    }
}
