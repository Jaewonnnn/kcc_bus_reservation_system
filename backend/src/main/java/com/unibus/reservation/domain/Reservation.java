package com.unibus.reservation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    private Long reservationId;
    private String paymentImpUid;
    private Long memberSeq;
    private Long nonUserCode;
    private Long scheduleId;
    private String seatNumber;
    private Long price;
    private Date reservationDate;
    private Long reservationStatus;

}
