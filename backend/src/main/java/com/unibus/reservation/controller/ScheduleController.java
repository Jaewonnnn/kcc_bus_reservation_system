package com.unibus.reservation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibus.reservation.dto.ReservationTicketDto;
import com.unibus.reservation.dto.ScheduleDto;
import com.unibus.reservation.dto.ScheduleSeatNumber;
import com.unibus.reservation.service.ReservationService;
import com.unibus.reservation.service.ScheduleService;
import com.unibus.reservation.service.TerminalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final TerminalService terminalService;
    private final ReservationService reservationService;


    @PostMapping("/schedule_list")
    @ResponseBody
    public List<ScheduleDto> getScheduleList(@RequestBody ScheduleDto scheduleDto) {
        System.out.println("Received ScheduleDto: " + scheduleDto);
        List<ScheduleDto> schedules = scheduleService.getSchedules(scheduleDto);
        System.out.println("Schedules: " + schedules);
        return schedules;
    }


    @GetMapping("/schedule_list")
    public String getScheduleList(
            @RequestParam("startTerminalId") String startTerminalId,
            @RequestParam("destinationTerminalId") String destinationTerminalId,
            @RequestParam("startTime") String startTime,
            Model model) {


        // ScheduleDto를 생성하여 서비스에 전달
        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setStartTerminalId(startTerminalId);
        scheduleDto.setDestinationTerminalId(destinationTerminalId);
        scheduleDto.setStartTime(startTime);

        // 스케줄 리스트를 가져오는 서비스 호출
        List<ScheduleDto> schedules = scheduleService.getSchedules(scheduleDto);
        log.info("Schedules: " + schedules);

        // JSON 문자열로 변환
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        for (int i = 0; i < schedules.size(); i++) {
            ScheduleDto schedule = schedules.get(i);
            List<ScheduleSeatNumber> seatNumbers = scheduleService.busSeatNumber(schedule.getScheduleId());

            jsonBuilder.append("{");
            jsonBuilder.append("\"scheduleId\":\"").append(schedule.getScheduleId()).append("\",");
            jsonBuilder.append("\"scheduleStartTime\":\"").append(schedule.getScheduleStartTime()).append("\",");
            jsonBuilder.append("\"scheduleEndTime\":\"").append(schedule.getScheduleEndTime()).append("\",");
            jsonBuilder.append("\"companyName\":\"").append(schedule.getCompanyName()).append("\",");
            jsonBuilder.append("\"gradeName\":\"").append(schedule.getGradeName()).append("\",");
            if (!seatNumbers.isEmpty()){

                jsonBuilder.append("\"seatCount\":\"").append(seatNumbers.size()).append("\"");
            }else {

                jsonBuilder.append("\"seatCount\":\"").append("0").append("\"");
            }
            jsonBuilder.append("}");
            if (i < schedules.size() - 1) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("]");

        // Terminal Name 조회
        String startTerminalName = terminalService.getTerminalNameById(startTerminalId);
        String destinationTerminalName = terminalService.getTerminalNameById(destinationTerminalId);

        model.addAttribute("schedulesJson", jsonBuilder.toString());
        log.info("jsonBuilder: " + jsonBuilder);
        //-------추가
        model.addAttribute("startTerminalName", startTerminalName);
        model.addAttribute("destinationTerminalName", destinationTerminalName);

        return "reservation_detail"; // 해당 페이지로 리턴
    }


    @GetMapping("/schedule/seat/{reservationId}")
    public String getSeat(Model model, @PathVariable("reservationId") int scheduleId) {
        List<ScheduleSeatNumber> seatNumbers = getBusNumber(scheduleId);

        // 데이터 가져오기 및 뷰 결정
        String busGrade;
        if (seatNumbers.isEmpty()) {
            ReservationTicketDto reservationTicket = reservationService.findBus(scheduleId);
            log.info(reservationTicket.toString());
            log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            busGrade = reservationTicket.getBusGradeName();
            // 리스트가 비어 있을 경우도 처리
        } else {
            ScheduleSeatNumber seat = seatNumbers.get(0);
            busGrade = seat.getBusGrade();
            log.info(seat.toString());
            log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            model.addAttribute("seats", seat.getScheduleId());

        }

        return determineViewName(busGrade);
    }

    private String determineViewName(String busGrade) {
        switch (busGrade) {
            case "고속":
            case "심야고속":
            case "일반":
            case "일반심야":
                return "reservation_seat1";
            case "우등":
            case "심야우등":
                return "reservation_seat2";
            case "프리미엄":
            case "심야프리미엄":
                return "reservation_seat3";
            default:
                return "errorPage"; // 적합한 등급이 없을 경우 에러 페이지 반환
        }
    }


    // 노선별 좌석 조회
    @GetMapping("/busNumber/{reservationId}")
    @ResponseBody
    public List<ScheduleSeatNumber> getBusNumber(@PathVariable int reservationId) {
        return scheduleService.busSeatNumber(reservationId);
    }

    @GetMapping("busNumber2/{scheduleId}")
    @ResponseBody
    public ReservationTicketDto getBusNumber2(@PathVariable int scheduleId) {
        return  reservationService.findBus(scheduleId);
    }

    //결제 동의 페이지
    @GetMapping("/payment-accept")
    public String paymentAccept(
            @RequestParam("seatIds") String seatIds,
            @RequestParam("passengerCount") int passengerCount,
            @RequestParam("totalAmount") String totalAmount,
            @RequestParam("busGrade") String busGrade,
            @RequestParam("startTerminal") String startTerminal,
            @RequestParam("endTerminal") String endTerminal,
            @RequestParam("busCompany")String busCompany,
            @RequestParam("adults")String adults,
            @RequestParam("students")String students,
            @RequestParam("children")String children,
            @RequestParam("scheduleId")String scheduleId,
            Model model) {

        List<String> seatIdList = Arrays.asList(seatIds.split(",")); // 문자열을 배열로 변환

        model.addAttribute("seatIds", seatIdList);
        model.addAttribute("passengerCount", passengerCount);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("busGrade", busGrade);
        model.addAttribute("startTerminal", startTerminal);
        model.addAttribute("endTerminal", endTerminal);
        model.addAttribute("busCompany", busCompany);
        model.addAttribute("adults", adults);
        model.addAttribute("students", students);
        model.addAttribute("children", children);
        model.addAttribute("scheduleId", scheduleId);


        return "payment_accept"; // 해당 뷰 이름으로 데이터와 함께 리턴
    }




}
