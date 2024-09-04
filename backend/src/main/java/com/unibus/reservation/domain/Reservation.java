package com.unibus.reservation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    private int reservationId;
    private int paymentImpUid;
    private int memberSeq;
    private int nonUserCode;
    private int scheduleId;
    private int seatNumber;
    private int price;
    private Date reservationDate;
    private boolean reservationStatus;

}
