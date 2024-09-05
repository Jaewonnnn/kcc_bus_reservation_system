package com.unibus.reservation.mapper;

import com.unibus.reservation.domain.Reservation;
import com.unibus.reservation.dto.ReservationSummaryDTO;
import com.unibus.reservation.dto.ReservationTicketDto;
import com.unibus.reservation.dto.ScheduleDto;
import com.unibus.reservation.dto.Ticket;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    public ReservationTicketDto findBus(int scheduleId);

    // 회원 예약 조회
    public List<ReservationSummaryDTO> findReservationsByMember(String memberId);

    // 비회원 예약 조회
    public List<ReservationSummaryDTO> findReservationsByNonUser(String phoneNumber);

    // 에매내역 수정
    public int updateReservation(int reservationId);

    // 예매 상세 조회
    public List<ReservationSummaryDTO> finDetailReservation(String memberId);

    public ReservationTicketDto getTicketByScheduleId(int scheduleId) ;
    public int memberSaveReservation(Ticket reservation);
    public int nonMemberSaveReservation(Ticket reservation);
}
