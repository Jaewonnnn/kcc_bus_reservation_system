package com.unibus.admin.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateScheduleDto {
    private String route;
    private String selectDate;
    private String startTime;
    private String endTime;
    private String busId;
    private String price;
}
