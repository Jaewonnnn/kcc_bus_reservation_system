package com.unibus.reservation.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationTerminalDto {
    private String terminalId;
    private String terminalName;
    private int city;
}
