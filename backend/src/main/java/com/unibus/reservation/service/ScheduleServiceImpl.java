package com.unibus.reservation.service;

import com.unibus.reservation.dto.ScheduleDto;
import com.unibus.reservation.mapper.ScheduleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleMapper scheduleMapper;

    public ScheduleServiceImpl(ScheduleMapper scheduleMapper) {
        this.scheduleMapper = scheduleMapper;

    }

    @Override
    public List<ScheduleDto> getSchedules(ScheduleDto scheduleDto) {
        log.info(scheduleDto.toString());
        return scheduleMapper.getSchedules(
                scheduleDto.getStartTerminalId(),
                scheduleDto.getDestinationTerminalId(),
                scheduleDto.getStartTime()

        );
    }

    @Override
    public ScheduleDto getScheduleDetail(int scheduleId) {
        return scheduleMapper.getScheduleDetail(scheduleId);
    }
}
