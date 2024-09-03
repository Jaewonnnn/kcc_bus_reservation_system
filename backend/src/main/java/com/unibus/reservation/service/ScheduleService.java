package com.unibus.reservation.service;

import com.unibus.reservation.dto.ScheduleDto;

import java.util.List;

public interface ScheduleService {
    List<ScheduleDto> getSchedules(ScheduleDto request);

    ScheduleDto getScheduleDetail(int scheduleId);
}
