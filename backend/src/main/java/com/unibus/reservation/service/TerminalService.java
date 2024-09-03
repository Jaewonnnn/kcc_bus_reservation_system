package com.unibus.reservation.service;

import com.unibus.reservation.dto.ReservationTerminalDto;

import java.util.List;

public interface TerminalService {
    List<ReservationTerminalDto> getAllTerminals();
}
