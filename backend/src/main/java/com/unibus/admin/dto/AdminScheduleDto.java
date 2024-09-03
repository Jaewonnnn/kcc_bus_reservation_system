package com.unibus.admin.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminScheduleDto {
    private String scheduleId;
    private int busId;
    private String routeId;
    private String scheduleStartTime;
    private String scheduleEndTime;
    private String schedulePrice;
    private boolean scheduleStatus;
}
