package com.unibus.reservation.mapper;

import com.unibus.reservation.dto.ScheduleDto;
import com.unibus.reservation.dto.ScheduleSeatNumber;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScheduleMapper {
//    List<ScheduleDto> findSchedulesByCriteria(ScheduleDto scheduleDto);
//
//    ScheduleDto findScheduleById(int scheduleId);

    List<ScheduleDto> getSchedules(@Param("startTerminalId") String startTerminalId,
                                   @Param("destinationTerminalId") String destinationTerminalId,
                                   @Param("startTime") String startTime);

    ScheduleDto getScheduleDetail(@Param("scheduleId") int scheduleId);
    ScheduleDto findScheduleById(int scheduleId);

    // 노선별 버스 좌석 번호 확인
    public List<ScheduleSeatNumber> busSeatNumber(int reservationId);
}
