package com.unibus.reservation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reservation {
    private int reservationId;
    private Schedule schedule;
    private String seatNumber;
    private int price;
    private LocalDateTime reservationDate;
}
