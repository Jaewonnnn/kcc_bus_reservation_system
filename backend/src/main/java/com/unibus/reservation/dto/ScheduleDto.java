package com.unibus.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {

    private String startTerminalId;
    private String destinationTerminalId;
    private String startTime;
    private int scheduleId;
    private int busId;
    private String routeId;
    private String scheduleStartTime;
    private String scheduleEndTime;
    private int schedulePrice;

    private String companyName;
    private String gradeName;


}
