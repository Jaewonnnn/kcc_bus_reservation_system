package com.unibus.reservation.mapper;

import com.unibus.reservation.domain.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {
    public int paymentSave(Payment payment);
}
