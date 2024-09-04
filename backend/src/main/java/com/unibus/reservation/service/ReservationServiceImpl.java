package com.unibus.reservation.service;

import com.unibus.reservation.dto.ScheduleDto;
import com.unibus.reservation.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImpl implements  ReservationService{

    private final ReservationMapper reservationMapper;

    @Override
    public ScheduleDto getTicket(int scheduleId) {
        log.info("schduleId = {}", scheduleId);
        log.info("ScheduleDto = {}", reservationMapper.getTicketByScheduleId(scheduleId));
      return reservationMapper.getTicketByScheduleId(scheduleId);
    }
}
