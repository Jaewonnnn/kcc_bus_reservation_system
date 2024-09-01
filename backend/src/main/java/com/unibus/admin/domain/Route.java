package com.unibus.admin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    private String RouteId;
    private String routeStartName;
    private String routeEndName;
    private Date requiredTime;
    private boolean routeStatus;

}
