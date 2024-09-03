package com.unibus.reservation.mapper;

import com.unibus.reservation.dto.ScheduleDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduleMapper {
    List<ScheduleDto> findSchedulesByCriteria(ScheduleDto scheduleDto);

    ScheduleDto findScheduleById(int scheduleId);
}
