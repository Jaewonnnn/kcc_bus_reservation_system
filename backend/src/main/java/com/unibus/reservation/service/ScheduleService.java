package com.unibus.reservation.service;

import com.unibus.reservation.dto.ScheduleDto;
import com.unibus.reservation.dto.ScheduleSeatNumber;

import java.util.List;

public interface ScheduleService {
    List<ScheduleDto> getSchedules(ScheduleDto request);

    ScheduleDto getScheduleDetail(int scheduleId);

    // 노선별 버스 좌석 번호 확인
    public List<ScheduleSeatNumber> busSeatNumber(int reservationId);
}
