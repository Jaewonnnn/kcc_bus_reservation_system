package com.unibus.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RouteDto {
    private String routeId;
    private TerminalDto routeStartTerminal;
    private TerminalDto routeEndTerminal;
    private Date requiredTime;
    private boolean routeStatus;

}
