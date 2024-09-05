package com.unibus.admin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminSchedule {
    private int scheduleId;
    private int busId;
    private String routeId;
    private String scheduleStartTime;
    private String scheduleEndTime;
    private int schedulePrice;
    private boolean scheduleStatus;
}
