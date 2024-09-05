package com.unibus.admin.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateScheduleDto {
    private String routeId;
    private String startTime;
    private String endTime;
    private Integer busId;
    private String price;
}
