package com.unibus.reservation.service;

import com.unibus.reservation.dto.ReservationTerminalDto;

import java.util.List;

public interface TerminalService {

    List<TerminalDto> getAllTerminals();

    // 터미널 ID로 터미널 이름 조회
    String getTerminalNameById(String terminalId);

}
