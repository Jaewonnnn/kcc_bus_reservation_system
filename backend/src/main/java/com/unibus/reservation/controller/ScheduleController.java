package com.unibus.reservation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibus.reservation.dto.ScheduleDto;
import com.unibus.reservation.dto.ScheduleSeatNumber;
import com.unibus.reservation.service.ScheduleService;
import com.unibus.reservation.service.TerminalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final TerminalService terminalService;

//    public ScheduleController(ScheduleService scheduleService) {
//        this.scheduleService = scheduleService;
//        this.terminalService = terminalService;
//    }


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
            jsonBuilder.append("{");
            jsonBuilder.append("\"scheduleStartTime\":\"").append(schedule.getScheduleStartTime()).append("\",");
            jsonBuilder.append("\"scheduleEndTime\":\"").append(schedule.getScheduleEndTime()).append("\",");
            jsonBuilder.append("\"companyName\":\"").append(schedule.getCompanyName()).append("\",");
            jsonBuilder.append("\"gradeName\":\"").append(schedule.getGradeName()).append("\"");
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

        //-------추가
        model.addAttribute("startTerminalName", startTerminalName);
        model.addAttribute("destinationTerminalName", destinationTerminalName);

        return "reservation_detail"; // 해당 페이지로 리턴
    }

    @GetMapping("/schedule/seat/{reservationId}")
    public String getSeat(Model model,@PathVariable("reservationId") int scheduleId) {
        List<ScheduleSeatNumber> list = getBusNumber(scheduleId);
        if (list.isEmpty()) {
            return "errorPage"; // 리스트가 비어있으면 에러 페이지 반환
        }

        String busGrade = list.get(0).getBusGrade();
        model.addAttribute("seats", list.get(0).getScheduleId());
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(list);
        System.out.println(busGrade);
        switch (busGrade) {
            case "고속":
            case "심야고속":
            case "일반":
            case "일반심야":
                return "reservation_seat1";

            case "우등" :
            case "심야우등":
                return "reservation_seat2";


            case "프리미엄":
            case "심야프리미엄":
                return "reservation_seat3";


            default:
                return "default_seat"; // 기본적으로 반환할 뷰 이름
        }
    }

    // 노선별 좌석 조회
    @GetMapping("/busNumber/{reservationId}")
    @ResponseBody
    public List<ScheduleSeatNumber> getBusNumber(@PathVariable int reservationId) {
        return scheduleService.busSeatNumber(reservationId);
    }


}
