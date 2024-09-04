package com.unibus.admin.service;

import com.unibus.admin.domain.Bus;
import com.unibus.admin.domain.City;
import com.unibus.admin.domain.Terminal;
import com.unibus.admin.domain.User;
import com.unibus.admin.dto.AdminTerminalDto;
import com.unibus.admin.dto.CompanyDto;
import com.unibus.admin.dto.UpdateTerminalDto;
import com.unibus.admin.dto.UserDto;
import com.unibus.admin.dto.*;
import com.unibus.admin.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService{

    private final AdminMapper adminMapper;

    @Override
    public List<UserDto> getUserList() {
        List<User> users = adminMapper.getUserList();
        List<UserDto> userDto = new ArrayList<>();

        for(User user : users) {
            userDto.add(UserDto.toUserDto(user));
        }

        return userDto;
    }

    @Override
    public UserDto getUserById(int id) {
        return UserDto.toUserDto(adminMapper.getUserById(id));
    }

    @Override
    public int updateUser(int id, UserDto userDto) {
        userDto.setId(id);
        return adminMapper.updateUser(userDto);
    }

    @Override
    public int deleteUser(int id) {
        return adminMapper.deleteUser(id);
    }

    @Override
    public List<Terminal> getTerminalList() {
        return adminMapper.getTerminalList();
    }

    @Override
    public AdminTerminalDto getTerminalById(String id) {
        return adminMapper.getTerminalById(id);
    }

    @Override
    public int createTerminal(AdminTerminalDto terminalDto) {
        String[] address = terminalDto.getAddress().split(" ");
        List<City> cityList = getCityList();

        boolean flag = false;

        log.info("address = {}", address[0] + " " + address[1]);
        for(City city : cityList) {
            if(city.getCityName().contains(address[0]) || city.getCityName().contains(address[1])) {
                terminalDto.setCityId(city.getCityId());
                log.info("cityId = {}", city.getCityId());
                flag = true;
                break;
            }
        }
        if(!flag) log.info("ERROR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        log.info("terminalDto = {}", terminalDto);

        return adminMapper.createTerminal(terminalDto);
    }

    @Override
    public List<City> getCityList() {
        log.info("cityList = {}", adminMapper.getCityList().get(0).getCityName());
        return adminMapper.getCityList();
    }

    @Override
    public int updateTerminal(String id, UpdateTerminalDto updateTerminalDto) {
        updateTerminalDto.setTerminalId(id);
        return adminMapper.updateTerminal(updateTerminalDto);
    }

    @Override
    public int deleteTerminal(String id) {
        return adminMapper.deleteTerminal(id);
    }

    @Override
    public List<CompanyDto> getCompanyList() {
        return adminMapper.getCompanyList();
    }

    @Override
    public CompanyDto getCompanyById(int id) {
        return adminMapper.getCompanyById(id);
    }

    @Override
    public int createCompany(CompanyDto companyDto) {
        return adminMapper.createCompany(companyDto);
    }

    @Override
    public int updateCompany(int id, CompanyDto companyDto) {
        companyDto.setCompanyId(id);

        return adminMapper.updateCompany(companyDto);
    }

    @Override
    public int deleteCompany(int id) {
        return adminMapper.deleteCompany(id);
    }

    @Override
    public List<RouteDto> getRouteList() {
        return adminMapper.getRouteList();
    }

    @Override
    public int createRoute(RouteCreateDto routeCreateDto) {
        String startTerminalId = adminMapper.getTerminalId(routeCreateDto.getRouteStartTerminal());
        log.info("startTerminalId = {}", startTerminalId);

        String endTerminalId = adminMapper.getTerminalId(routeCreateDto.getRouteEndTerminal());
        log.info("endTerminalId = {}", endTerminalId);

        routeCreateDto.setRouteStartTerminal(startTerminalId);
        routeCreateDto.setRouteEndTerminal(endTerminalId);
        log.info("routeRequiredTime = {}", routeCreateDto.getRequiredTime());

        return adminMapper.createRoute(routeCreateDto);
    }

    @Override
    public int updateRoute(String routeId, RouteCreateDto routeCreateDto) {

        routeCreateDto.setRouteId(routeId);

        String startTerminalId = adminMapper.getTerminalId(routeCreateDto.getRouteStartTerminal());
        log.info("startTerminalId = {}", startTerminalId);

        String endTerminalId = adminMapper.getTerminalId(routeCreateDto.getRouteEndTerminal());
        log.info("endTerminalId = {}", endTerminalId);

        routeCreateDto.setRouteStartTerminal(startTerminalId);
        routeCreateDto.setRouteEndTerminal(endTerminalId);
        log.info("routeRequiredTime = {}", routeCreateDto.getRequiredTime());

        return adminMapper.updateRoute(routeCreateDto);
    }

    @Override
    public int deleteRoute(String routeId) {
        log.info("routeId = {}", routeId);
        return adminMapper.deleteRoute(routeId);
    }

    @Override
    public List<AdminScheduleDto> getScheduleList() {
        return adminMapper.getScheduleList();
    }

    @Override
    public AdminScheduleDto getScheduleById(int scheduleId) {
        return adminMapper.getScheduleById(scheduleId);
    }

    @Override
    public int createSchedule(AdminScheduleDto adminScheduleDto) {
        List<RouteDto> routeList = adminMapper.getRouteList();


        return 0;
    }

    @Override
    public List<BusDto> getBusList() {
        return adminMapper.getBusList();
    }
}
