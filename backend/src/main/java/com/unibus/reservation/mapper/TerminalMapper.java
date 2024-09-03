package com.unibus.reservation.mapper;

import com.unibus.reservation.dto.TerminalDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TerminalMapper {
    List<TerminalDto> findAllTerminals();
}
