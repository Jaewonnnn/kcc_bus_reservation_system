package com.unibus;

import com.unibus.reservation.domain.Payment;
import com.unibus.reservation.domain.Reservation;
import com.unibus.reservation.dto.ReservationSummaryDTO;
import com.unibus.reservation.mapper.PaymentMapper;
import com.unibus.reservation.mapper.ReservationMapper;
import com.unibus.reservation.service.ReservationService;
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
	private ReservationService reservationService;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PaymentMapper paymentMapper;

	@Test
	void contextLoads() {
		ReservationSummaryDTO dto = reservationService.finDetailReservation(242,"1111");
		log.info("dto = {}", dto);
		Assertions.assertNotNull(dto);
	}

}
