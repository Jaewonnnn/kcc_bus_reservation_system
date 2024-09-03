package com.unibus.reservation.domain;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Schedule {
    private int scheduleId;
    private int busId;
    private String routeId;
    private String startTime;
    private String endTime;
    private int price;
//    private int scheduleStatus;
}
