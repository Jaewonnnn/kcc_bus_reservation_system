package com.unibus.admin.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminScheduleDto {
    private String scheduleId;
    private String companyName;
    private String busNumber;
    private String route;
    private String departureTime;
    private String arrivalTime;
    private List<String> days;
    private String price;
    private boolean scheduleStatus;
}

