package com.unibus.reservation.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TerminalDto {
    private String terminalId;
    private String terminalName;
    private int city;
}
