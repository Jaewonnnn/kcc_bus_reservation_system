package com.unibus.reservation.service;

import com.unibus.reservation.domain.Payment;
import com.unibus.reservation.domain.Reservation;
import com.unibus.reservation.dto.ReservationFinishDto;
import com.unibus.reservation.dto.ReservationSummaryDTO;
import com.unibus.reservation.dto.ReservationTicketDto;
import com.unibus.reservation.dto.Ticket;
import com.unibus.reservation.mapper.PaymentMapper;
import com.unibus.reservation.mapper.ReservationMapper;
import com.unibus.user.domain.Member;
import com.unibus.user.domain.NonMember;
import com.unibus.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService{

    private final ReservationMapper reservationMapper;
    private final UserMapper userMapper;
    private final PaymentMapper paymentMapper;
    private final ReservationMapper mapper;

    @Override
    public ReservationTicketDto getTicket(int scheduleId) {
        return reservationMapper.getTicketByScheduleId(scheduleId);
    }

    public ReservationTicketDto findBus(int scheduleId) {
        log.info("schduleId = {}", scheduleId);
        log.info("ScheduleDto = {}", reservationMapper.findBus(scheduleId));
      return reservationMapper.findBus(scheduleId);
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
    public ReservationSummaryDTO finDetailReservation(int paymentImpUid, String memberId) {
        ReservationSummaryDTO dto = mapper.finDetailReservation(paymentImpUid, memberId);
        dto.setSeatNumber(mapper.findDetailTicket(paymentImpUid, memberId));
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info("dto = {}", dto);
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        return dto;
    }




    @Override
    @Transactional
    public Boolean reservationSave(Reservation reservation, String memberId, NonMember nonmember, Payment payment) {
        Member member = userMapper.getMemberByMemberId(memberId);
        paymentMapper.paymentSave(payment);
        reservation.setPaymentImpUid(payment.getPaymentImpUid());
        String[] seat = reservation.getSeatNumber().split(","); // 71025
        List<Integer> seatList = new ArrayList<>();

        boolean flag = false;

        for(String seatNum : seat){
            seatList.add(Integer.parseInt(seatNum.trim()));
        }

        Ticket ticket =new Ticket();
        for (int i : seatList){
            ticket.setReservationId(reservation.getReservationId());
            ticket.setPaymentImpUid(payment.getPaymentImpUid());
            ticket.setScheduleId(reservation.getScheduleId());
            ticket.setSeatNumber(i);
            ticket.setPrice(reservation.getPrice());



            if (member == null) {
                userMapper.nonMemberSave(nonmember);
                int code = userMapper.getNonUserCode();
                ticket.setNonUserCode(code);
                int result = reservationMapper.nonMemberSaveReservation(ticket);
                if (result > 0) {
                    flag = true;
                } else {
                    return false;
                }
            } else {
                ticket.setMemberSeq(member.getMemberSeq());
                int result = reservationMapper.memberSaveReservation(ticket);
                if (result > 0) {
                    flag = true;
                } else {
                    return false;
                }

            }

        }
        return flag;
    }

    @Override
    public ReservationFinishDto finishReservation(int paymentImpUid) {
        ReservationFinishDto dto = reservationMapper.getReservationByPaymentImpUid(paymentImpUid);
        dto.setSeatNumber(reservationMapper.findSeatNumberByPayment(paymentImpUid));
        dto.setTicketCount(reservationMapper.findTicketCountByPaymentId(paymentImpUid));
        dto.setTotalPrice(reservationMapper.findTotalPriceByPaymentImpUid(paymentImpUid));
        return dto;
    }
}
