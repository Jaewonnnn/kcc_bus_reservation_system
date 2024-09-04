package com.unibus.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationTicketDto {
        private int scheduleId;
        private int busId;
        private String routeId;
        private String scheduleStartDate;
        private int schedulePrice;
        private String startTerminal;
        private String endTerminal;
        private String busGradeName;
        private String companyName;
}
