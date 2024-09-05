package com.unibus.reservation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private int paymentImpUid;
    private String paymentProvider;
    private String paymentMethod;
}
