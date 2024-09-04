package com.unibus.reservation.mapper;

import com.unibus.reservation.dto.ScheduleDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {
    public ScheduleDto getTicketByScheduleId(int scheduleId);
}
