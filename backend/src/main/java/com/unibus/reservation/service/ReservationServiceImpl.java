package com.unibus.reservation.service;

import com.unibus.reservation.dto.ScheduleDto;
import com.unibus.reservation.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.unibus.reservation.dto.ScheduleSeatNumber;
import com.unibus.reservation.dto.ReservationSummaryDTO;
import com.unibus.reservation.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImpl implements  ReservationService{

    private final ReservationMapper reservationMapper;
    public ReservationMapper mapper;

    @Override
    public ScheduleDto getTicket(int scheduleId) {
        log.info("schduleId = {}", scheduleId);
        log.info("ScheduleDto = {}", reservationMapper.getTicketByScheduleId(scheduleId));
      return reservationMapper.getTicketByScheduleId(scheduleId);
    }

    // 회원 예약 조회
    @Override
    public List<ReservationSummaryDTO> findReservationsByMember(String memberId) {
        return mapper.findReservationsByMember(memberId);
    }

    // 비회원 예약 조회
    @Override
    public List<ReservationSummaryDTO> findReservationsByNonUser(String phoneNumber) {
        return mapper.findReservationsByNonUser(phoneNumber);
    }

    @Override
    public int updateReservation(int reservationId) {
        return mapper.updateReservation(reservationId);
    }

    @Override
    public List<ReservationSummaryDTO> finDetailReservation(String memberId) {
        return mapper.finDetailReservation(memberId);
    }




}
