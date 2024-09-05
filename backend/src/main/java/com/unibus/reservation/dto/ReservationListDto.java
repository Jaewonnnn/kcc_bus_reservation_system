package com.unibus.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationListDto {
    private int paymentImpUid;
    private String startTerminal;
    private String endTerminal;
    private String reservationDate;
    private String companyName;
    private String busGradeName;
    private int ticketCount;
    private int seatNumber;
    private int price;
}
