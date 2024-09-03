package com.unibus.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {
    private int scheduleId;
    private int busId;
    private String routeId;
    private String startTime;
    private String endTime;
    private int price;
//    private int scheduleStatus;
}
