package com.unibus.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationFinishDto {
    private int paymentImpUid;
    private String reservationStartDate;
    private String startTerminal;
    private String endTerminal;
    private String companyName;
    private String busGradeName;
    private List<Integer> seatNumber;
    private int totalPrice;
    private int ticketCount;
}
