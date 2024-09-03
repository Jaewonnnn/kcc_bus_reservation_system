package com.unibus.reservation.mapper;

import com.unibus.reservation.dto.ReservationTerminalDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface TerminalMapper {
    List<ReservationTerminalDto> findAllTerminals();
}
