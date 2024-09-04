package com.unibus.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SchedulePageDto {
    private String scheduleId;
    private String startName;
    private String endName;
    private String requiredTime;
    private int price;
}
