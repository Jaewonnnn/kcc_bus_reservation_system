package com.unibus.reservation.service;

import com.unibus.reservation.dto.ReservationTerminalDto;
import com.unibus.reservation.mapper.TerminalMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TerminalServiceImpl implements TerminalService {
    private final TerminalMapper terminalMapper;

    public TerminalServiceImpl(TerminalMapper terminalMapper) {
        this.terminalMapper = terminalMapper;
    }

    @Override
    public List<ReservationTerminalDto> getAllTerminals() {
        log.info("getAllTerminals");
        return terminalMapper.findAllTerminals();
    }
}
