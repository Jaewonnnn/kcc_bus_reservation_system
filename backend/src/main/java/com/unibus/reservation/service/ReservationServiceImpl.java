package com.unibus.reservation.service;

import com.unibus.reservation.domain.Payment;
import com.unibus.reservation.domain.Reservation;
import com.unibus.reservation.dto.ReservationTicketDto;
import com.unibus.reservation.mapper.PaymentMapper;
import com.unibus.reservation.mapper.ReservationMapper;
import com.unibus.user.domain.Member;
import com.unibus.user.domain.NonMember;
import com.unibus.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.unibus.reservation.dto.ScheduleSeatNumber;
import com.unibus.reservation.dto.ReservationSummaryDTO;
import com.unibus.reservation.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService{

    private final ReservationMapper reservationMapper;
    public ReservationMapper mapper;
    private final UserMapper userMapper;
    private final PaymentMapper paymentMapper;

    @Override
    public ReservationTicketDto getTicket(int scheduleId) {
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




    @Override
    @Transactional
    public Boolean reservationSave(Reservation reservation, String memberId, NonMember nonmember, Payment payment) {
        Member member = userMapper.getMemberByMemberId(memberId);
        paymentMapper.paymentSave(payment);
        reservation.setPaymentImpUid(payment.getPaymentImpUid());
        if (member == null) {
            userMapper.nonMemberSave(nonmember);
            int code = userMapper.getNonUserCode();
            reservation.setNonUserCode(code);
            int result = reservationMapper.nonMemberSaveReservation(reservation);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            reservation.setMemberSeq(member.getMemberSeq());
            int result = reservationMapper.memberSaveReservation(reservation);
            if(result > 0) {
                return true;
            } else {
                return false;
            }
        }
    }


}
