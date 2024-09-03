package com.unibus.reservation.controller;

import com.unibus.reservation.dto.ScheduleDto;
import com.unibus.reservation.service.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservation")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/schedule_list")
    public String getScheduleList(@ModelAttribute ScheduleDto scheduleDto, Model model) {
        List<ScheduleDto> schedules = scheduleService.getSchedules(scheduleDto);
        model.addAttribute("schedules", schedules);
        return "main";
    }

    @GetMapping("/schedule_list/{schedule_id}")
    public String getScheduleDetail(@PathVariable("schedule_id") int scheduleId, Model model) {
        ScheduleDto schedule = scheduleService.getScheduleDetail(scheduleId);
        model.addAttribute("schedule", schedule);
        return "reservation_detail";
    }

}
