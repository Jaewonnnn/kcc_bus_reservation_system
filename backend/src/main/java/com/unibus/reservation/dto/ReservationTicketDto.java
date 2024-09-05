package com.unibus.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationTicketDto {
    private int scheduleId;
    private int busId;
    private String routeId;
    private String scheduleStartDate;
    private String scheduleEndDate;
    private int schedulePrice;
    private String startTerminal;
    private String endTerminal;
    private String busGradeName;
    private String companyName;
}
