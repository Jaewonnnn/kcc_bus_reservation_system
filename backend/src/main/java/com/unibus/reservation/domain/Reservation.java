package com.unibus.reservation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    private int reservationId;
    private int paymentImpUid;
    private int memberSeq;
    private int nonUserCode;
    private int scheduleId;
    private String seatNumber;
    private int price;
    private Date reservationDate;
    private boolean reservationStatus;

}
