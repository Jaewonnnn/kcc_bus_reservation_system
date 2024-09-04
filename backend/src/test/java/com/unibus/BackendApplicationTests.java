package com.unibus;

import com.unibus.reservation.domain.Payment;
import com.unibus.reservation.domain.Reservation;
import com.unibus.reservation.mapper.PaymentMapper;
import com.unibus.reservation.mapper.ReservationMapper;
import com.unibus.user.domain.Member;
import com.unibus.user.domain.NonMember;
import com.unibus.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@SpringBootTest
@Slf4j
class BackendApplicationTests {

	@Autowired
	private ReservationMapper reservationMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PaymentMapper paymentMapper;

	@Test
	void contextLoads() {
		Member member = userMapper.getMemberByMemberId("dnjstmddjs1234");

		Payment payment = new Payment(1091192182, "kakaoPay", "card");
		paymentMapper.paymentSave(payment);
		Reservation reservation = new Reservation();
		reservation.setPaymentImpUid(payment.getPaymentImpUid());

		if(member != null) {
			reservation.setMemberSeq(member.getMemberSeq());
			reservation.setPrice(111111);
			reservation.setScheduleId(1);
			reservation.setSeatNumber(25);

			log.info("=================== user ================");
			log.info("member = {}", member);
			log.info("payment = {}", payment);
			log.info("reservation = {}", reservation);
			log.info("=================== user ================");
			Assertions.assertNotNull(reservationMapper.memberSaveReservation(reservation));
		} else {
//			Payment payment = new Payment(1203165, "kakaoPay", "card");
//			paymentMapper.paymentSave(payment);
//			Reservation reservation = new Reservation();
//			reservation.setPaymentImpUid(payment.getPaymentImpUid());
			NonMember nonMember = new NonMember();
			nonMember.setNonUserTel("012-3123-1232");
			userMapper.nonMemberSave(nonMember);
			int code =userMapper.getNonUserCode();
			reservation.setNonUserCode(code);
			reservation.setPrice(111111);
			reservation.setScheduleId(1);
			reservation.setSeatNumber(25);

			log.info("=================== NONuser ================");
			log.info("nonMember = {}", nonMember);
			log.info("reservation = {}", reservation);
			log.info("=================== NONuser ================");
			Assertions.assertNotNull(reservationMapper.nonMemberSaveReservation(reservation));
		}
	}

}
