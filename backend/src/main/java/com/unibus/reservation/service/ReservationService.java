package com.unibus.reservation.service;

import com.unibus.reservation.dto.ReservationDto;

public interface ReservationService {
    ReservationDto reserveSeat(ReservationDto reservationDto);
}
