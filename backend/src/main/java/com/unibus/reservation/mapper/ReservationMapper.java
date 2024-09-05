package com.unibus.reservation.mapper;

import com.unibus.reservation.domain.Reservation;
import com.unibus.reservation.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {
    public ReservationTicketDto findBus(int scheduleId);

    // 회원 예약 조회
    public List<ReservationSummaryDTO> findReservationsByMember(@Param("memberId") String memberId);

    // 비회원 예약 조회
    public List<ReservationSummaryDTO> findReservationsByNonUser(String phoneNumber);

    // 에매내역 수정
    public int updateReservation(int reservationId);

    // 예매 상세 조회
    public ReservationSummaryDTO finDetailReservation(@Param("paymentImpUid") int paymentImpUid, @Param("memberId") String memberId);

    public ReservationTicketDto getTicketByScheduleId(int scheduleId) ;
    public int memberSaveReservation(Ticket reservation);
    public int nonMemberSaveReservation(Ticket reservation);
    public ReservationFinishDto getReservationByPaymentImpUid(int paymentImpUid);
    public int findTotalPriceByPaymentImpUid(int paymentImpUid);
    public List<Integer> findSeatNumberByPayment(@Param("paymentImpUid") int paymentImpUid);
    public int findTicketCountByPaymentId(int paymentImpUid);
    public List<Integer> findDetailTicket(@Param("paymentImpUid") int paymentImpUid, @Param("memberId") String memberId);

}
