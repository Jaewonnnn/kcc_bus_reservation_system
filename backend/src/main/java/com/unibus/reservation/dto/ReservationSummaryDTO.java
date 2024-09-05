package com.unibus.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationSummaryDTO {
    private int paymentImpUid;
    private String scheduleDate;
    private String startTime;
    private String scheduleEndTime;
    private String startTerminalName;
    private String endTerminalName;
    private List<Integer> seatNumber;
    private Integer reservationCount;
    private Double totalPrice;

}
