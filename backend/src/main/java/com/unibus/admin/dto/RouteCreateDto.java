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
public class RouteCreateDto {
    private String routeId;
    private String routeStartTerminal;
    private String routeEndTerminal;
    private Date requiredTime;
    private boolean routeStatus;

}
