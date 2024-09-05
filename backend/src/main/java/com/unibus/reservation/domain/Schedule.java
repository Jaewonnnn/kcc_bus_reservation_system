package com.unibus.reservation.domain;

import lombok.*;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Schedule {

    private int scheduleId;
    private int busId;
    private String routeId;
    private Date scheduleStartTime;
    private Date scheduleEndTime;
    private int schedulePrice;


    private String companyName;
    private String gradeName;


}
