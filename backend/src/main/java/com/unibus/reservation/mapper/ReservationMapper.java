package com.unibus.reservation.mapper;

import com.unibus.reservation.dto.ReservationSummaryDTO;
import com.unibus.reservation.dto.ScheduleDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    public ScheduleDto getTicketByScheduleId(int scheduleId);

    // 회원 예약 조회
    public List<ReservationSummaryDTO> findReservationsByMember(String memberId);

    // 비회원 예약 조회
    public List<ReservationSummaryDTO> findReservationsByNonUser(String phoneNumber);

    // 에매내역 수정
    public int updateReservation(int reservationId);

    // 예매 상세 조회
    public List<ReservationSummaryDTO> finDetailReservation(String memberId);

}
