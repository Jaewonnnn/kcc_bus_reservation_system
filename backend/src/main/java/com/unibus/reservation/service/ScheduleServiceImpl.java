package com.unibus.reservation.service;

import com.unibus.reservation.dto.ScheduleDto;
import com.unibus.reservation.mapper.ScheduleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleMapper scheduleMapper;

    public ScheduleServiceImpl(ScheduleMapper scheduleMapper) {
        this.scheduleMapper = scheduleMapper;
    }

    @Override
    public List<ScheduleDto> getSchedules(ScheduleDto request) {
        return scheduleMapper.findSchedulesByCriteria(request);
    }

    @Override
    public ScheduleDto getScheduleDetail(int scheduleId) {
        return scheduleMapper.findScheduleById(scheduleId);
    }
}
