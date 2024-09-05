package com.unibus.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScheduleSeatNumber {
    private Long scheduleId;
    private String seatNumber;
    private String startTerminalName;
    private String endTerminalName;
    private String busGrade;
    private Date startTime;
    private Date endTime;
}
