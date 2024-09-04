package com.unibus.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationSummaryDTO {
    private String scheduleDate;
    private String startTime;
    private String scheduleEndTime;
    private String startTerminalName;
    private String endTerminalName;
    private String seatNumber;
    private Integer reservationCount;
    private Double totalPrice;

}
