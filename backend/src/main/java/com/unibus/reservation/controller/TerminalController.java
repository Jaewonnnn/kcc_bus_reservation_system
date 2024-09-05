package com.unibus.reservation.controller;

import com.unibus.admin.dto.TerminalDto;
import com.unibus.reservation.dto.ReservationTerminalDto;
import com.unibus.reservation.service.TerminalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/reservation")
@Slf4j
public class TerminalController {
    private final TerminalService terminalService;

    public TerminalController(TerminalService terminalService) {
        this.terminalService = terminalService;
    }

    @GetMapping("/terminal_list")
    @ResponseBody
//    public String getTerminalList(Model model) {
//        List<TerminalDto> terminals = terminalService.getAllTerminals();
//        model.addAttribute("terminals", terminals);
//        return "main";
//    }
    public List<ReservationTerminalDto> getTerminalList() {
        return terminalService.getAllTerminals();
    }
}
