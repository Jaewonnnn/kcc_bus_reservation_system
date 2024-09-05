package com.unibus.admin.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SchedulePageDto {
    private int scheduleId;
    private String routeId;
    private String startName;
    private String endName;
    private String departureTime;
    private String arrivalTime;
    private String busNumber;
    private String companyName;
    private int price;
}
