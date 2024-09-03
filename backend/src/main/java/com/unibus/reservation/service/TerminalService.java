package com.unibus.reservation.service;

import com.unibus.reservation.dto.TerminalDto;

import java.util.List;

public interface TerminalService {
    List<TerminalDto> getAllTerminals();
}
