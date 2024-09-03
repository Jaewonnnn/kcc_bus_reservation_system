package com.unibus.admin.controller;

import com.unibus.admin.dto.AdminTerminalDto;
import com.unibus.admin.dto.CompanyDto;
import com.unibus.admin.dto.UpdateTerminalDto;
import com.unibus.admin.dto.UserDto;
import com.unibus.admin.dto.*;
import com.unibus.admin.service.AdminService;
import com.unibus.admin.service.AdminServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/")
@Slf4j
public class AdminController {

    private final AdminService adminService;
    private final AdminServiceImpl adminServiceImpl;

    @GetMapping("/")
    public String getAdminPage() {
        return "admin_user";
    }

    @GetMapping("/users")
    @ResponseBody
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<List<UserDto>>(adminService.getUserList(), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    @ResponseBody
    public ResponseEntity<UserDto> getOneUser(@PathVariable int userId) {
        return new ResponseEntity<UserDto>(adminService.getUserById(userId), HttpStatus.OK);
    }

    @PatchMapping("/user/{userId}")
    public String updateUser(@PathVariable int userId, @RequestBody UserDto userDto) throws Exception{
        int result = adminService.updateUser(userId, userDto);
        if(result == 1){
            return "redirect:/admin/";
        }else {
            throw new Exception("update fail");
        }
    }

    @PatchMapping("/user/delete/{userId}")
    public String deleteUser(@PathVariable int userId) throws Exception{
        int result = adminService.deleteUser(userId);
        if(result == 1) {
            return "redirect:/admin/";
        }else{
            throw new Exception("delete fail");
        }
    }

    @GetMapping("/terminal")
    public String getTerminalPage(Model model){
        model.addAttribute("terminalList", adminService.getTerminalList());
        return "admin_terminal";
    }

    @GetMapping("/terminal/{terminalId}")
    @ResponseBody
    public AdminTerminalDto getTerminal(@PathVariable String terminalId){
        return adminService.getTerminalById(terminalId);
    }

    @PostMapping("/terminal")
    public String createTerminal(@RequestBody AdminTerminalDto terminalDto){
        adminService.createTerminal(terminalDto);
        return "redirect:/admin/terminal";
    }

    @PatchMapping("/terminal/{terminalId}")
    public String updateTerminal(@PathVariable String terminalId, @RequestBody UpdateTerminalDto updateTerminalDto){
        log.info("updateTerminalDto = {}", updateTerminalDto + " " + new Date());
        adminService.updateTerminal(terminalId, updateTerminalDto);
        return "redirect:/admin/terminal";
    }

    @PatchMapping("/terminal/delete/{terminalId}")
    public String deleteTerminal(@PathVariable String terminalId) throws Exception{
        int result = adminService.deleteTerminal(terminalId);
        if(result == 1)
            return "redirect:/admin/terminal";
        else
            throw new Exception("delete terminal failed");
    }

    @GetMapping("/company")
    public String getCompanyList(Model model){
        model.addAttribute("companyList", adminService.getCompanyList());
        return "admin_company";
    }

    @GetMapping("/company/{companId}")
    @ResponseBody
    public CompanyDto getCompanyById(@PathVariable int companId){
        return adminService.getCompanyById(companId);
    }

    @PostMapping("/company")
    public String createCompany(@RequestBody CompanyDto companyDto){
        adminServiceImpl.createCompany(companyDto);
        return "redirect:/admin/company";
    }

    @PatchMapping("/company/{companId}")
    public String updateCompany(@PathVariable int companId, @RequestBody CompanyDto companyDto){
        adminService.updateCompany(companId, companyDto);
        return "redirect:/admin/company";
    }

    @DeleteMapping("/company/delete/{companId}")
    public String deleteCompany(@PathVariable int companId){
        adminService.deleteCompany(companId);
        return "redirect:/admin/company";
    }

    @GetMapping("/route")
    public String getRouteList(){
        return "admin_route";
    }

    @PostMapping("/route")
    public String createRoute(@RequestBody RouteCreateDto routeCreateDto){
        adminService.createRoute(routeCreateDto);
        return "redirect:/admin/route";
    }

    @PatchMapping("/route/{routeId}")
    public String updateRoute(@PathVariable String routeId, @RequestBody RouteCreateDto routeCreateDto){
        adminService.updateRoute(routeId, routeCreateDto);
        return "redirect:/admin/route";
    }

    @PatchMapping("/route/delete/{routeId}")
    public String deleteRoute(@PathVariable String routeId) throws Exception{
        int result = adminService.deleteRoute(routeId);
        if(result == 1)
            return "redirect:/admin/route";
        else
            throw new Exception("delete route failed");
    }

    @GetMapping("/schedule")
    public String getScheduleList(Model model){
        model.addAttribute("scheduleList", adminService.getScheduleList());
        return "admin_schedule";
    }

    @GetMapping("/schedule/{scheduleId}")
    @ResponseBody
    public AdminScheduleDto getScheduleById(@PathVariable int scheduleId){
        return adminService.getScheduleById(scheduleId);
    }

}
