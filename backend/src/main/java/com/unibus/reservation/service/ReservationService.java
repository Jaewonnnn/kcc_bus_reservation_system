package com.unibus.reservation.service;

import com.unibus.reservation.domain.Payment;
import com.unibus.reservation.domain.Reservation;
import com.unibus.reservation.dto.ReservationTicketDto;
import com.unibus.user.domain.Member;
import com.unibus.user.domain.NonMember;

import com.unibus.reservation.dto.ScheduleSeatNumber;
import com.unibus.reservation.dto.ReservationSummaryDTO;

import java.util.List;
public interface ReservationService {
    
    public ScheduleDto getTicket(int scheduleId);

    // 회원 예약 조회
    public  List<ReservationSummaryDTO> findReservationsByMember(String memberId);

    // 비회원 예약 조회
    public List<ReservationSummaryDTO> findReservationsByNonUser(String phoneNumber);

    // 에매내역 수정
    public int updateReservation(int reservationId);

    // 예매 상세 조회
    public List<ReservationSummaryDTO> finDetailReservation(String memberId);


    public ReservationTicketDto getTicket(int scheduleId);
    public Boolean reservationSave(Reservation reservation, String memberId, NonMember nonmember, Payment payment);
}
