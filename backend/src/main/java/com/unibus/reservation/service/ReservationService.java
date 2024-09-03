package com.unibus.reservation.service;

import com.unibus.reservation.dto.ScheduleDto;

public interface ReservationService {
    public ScheduleDto getTicket(int scheduleId);
}
