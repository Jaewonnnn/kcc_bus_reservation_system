package com.unibus.admin.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RouteDto {
    private String routeId;
    private TerminalDto routeStartTerminal;
    private TerminalDto routeEndTerminal;
    private Date requiredTime;
    private boolean routeStatus;

}
