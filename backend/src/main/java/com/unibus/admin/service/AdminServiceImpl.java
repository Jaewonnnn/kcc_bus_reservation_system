package com.unibus.admin.service;

import com.unibus.admin.domain.AdminSchedule;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public List<SchedulePageDto> getScheduleList() {
        List<AdminSchedule> list = adminMapper.getScheduleList();
        log.info("list = {}", list);
        List<SchedulePageDto> newList = new ArrayList<>();

        for (AdminSchedule adminScheduleDto : list) {
            SchedulePageDto schedulePageDto = new SchedulePageDto();
            GetRouteIdVo getRouteIdVo = adminMapper.getTerminalName(adminScheduleDto.getRouteId());

            schedulePageDto.setScheduleId(adminScheduleDto.getScheduleId());
            schedulePageDto.setStartName(getRouteIdVo.getDepartureName());
            schedulePageDto.setEndName(getRouteIdVo.getArrivalName());
            schedulePageDto.setRouteId(adminScheduleDto.getRouteId());
            schedulePageDto.setArrivalTime(adminScheduleDto.getScheduleEndTime());
            schedulePageDto.setDepartureTime(adminScheduleDto.getScheduleStartTime());
            schedulePageDto.setPrice(adminScheduleDto.getSchedulePrice());

            newList.add(schedulePageDto);
        }
        log.info("newList = {}", newList);

        return newList;
    }


    @Override
    public SchedulePageDto getScheduleById(int scheduleId) {
        log.info("schedule = {}", adminMapper.getScheduleById(scheduleId));
        AdminSchedule adminScheduleDto = adminMapper.getScheduleById(scheduleId);
        log.info("adminScheduleDto = {}", adminScheduleDto);

        SchedulePageDto schedulePageDto = new SchedulePageDto();
        GetRouteIdVo getRouteIdVo = adminMapper.getTerminalName(adminScheduleDto.getRouteId());

        schedulePageDto.setScheduleId(adminScheduleDto.getScheduleId());
        schedulePageDto.setStartName(getRouteIdVo.getDepartureName());
        schedulePageDto.setEndName(getRouteIdVo.getArrivalName());
        schedulePageDto.setRouteId(adminScheduleDto.getRouteId());
        schedulePageDto.setArrivalTime(adminScheduleDto.getScheduleEndTime());
        schedulePageDto.setDepartureTime(adminScheduleDto.getScheduleStartTime());
        schedulePageDto.setPrice(adminScheduleDto.getSchedulePrice());

        CompanyAndBusDto companyAndBusDto = adminMapper.getCompanyAndBus(adminScheduleDto.getBusId());
        schedulePageDto.setCompanyName(companyAndBusDto.getCompanyName());
        schedulePageDto.setBusNumber(companyAndBusDto.getBusNumber());

        return schedulePageDto;
    }

    @Override
    @Transactional
    public int createSchedule(AdminScheduleDto adminScheduleDto) {
        List<CreateScheduleDto> scheduleList = new ArrayList<>();
        String[] route = adminScheduleDto.getRoute().split(" ");
        String startTerminal = route[0];
        String endTerminal = route[2];

        String routeId = adminMapper.getRouteId(new GetRouteIdVo(startTerminal, endTerminal));
        Integer busId = adminMapper.getBusId(adminScheduleDto.getBusNumber());
        String price = adminScheduleDto.getPrice();

        // 현재 날짜
        LocalDateTime now = LocalDateTime.now();
        now = now.withHour(Integer.parseInt(adminScheduleDto.getDepartureTime().substring(0, 2)))
                .withMinute(Integer.parseInt(adminScheduleDto.getDepartureTime().substring(3)));

        int requiredHour = Integer.parseInt(adminScheduleDto.getDepartureTime().substring(0,2))
        - Integer.parseInt(adminScheduleDto.getArrivalTime().substring(0,2));

        int requiredMinute = Integer.parseInt(adminScheduleDto.getDepartureTime().substring(3))
                - Integer.parseInt(adminScheduleDto.getArrivalTime().substring(3));

        if(requiredMinute < 0){
            requiredMinute += 60;
            if(requiredHour > 0)
                requiredHour -= 1;
        }

        // 한 달 뒤 날짜
        LocalDateTime oneMonthLater = now.plusMonths(1);

        // 날짜 수 계산 (현재 날짜 포함)
        int daysBetween = (int) (oneMonthLater.toLocalDate().toEpochDay() - now.toLocalDate().toEpochDay()) + 1;

        // LocalDateTime 배열 생성
        LocalDateTime[] dateTimeArray = new LocalDateTime[daysBetween];

        // 배열에 날짜 저장
        for (int i = 0; i < daysBetween; i++) {
            dateTimeArray[i] = now.plusDays(i);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        CreateScheduleDto createScheduleDto = new CreateScheduleDto();
        createScheduleDto.setRouteId(routeId);
        createScheduleDto.setBusId(busId);
        createScheduleDto.setPrice(price);

        for(LocalDateTime dateTime : dateTimeArray) {
            createScheduleDto.setStartTime(dateTime.format(formatter));
            createScheduleDto.setEndTime(dateTime.plusHours(requiredHour).plusMinutes(requiredMinute).format(formatter));
            scheduleList.add(createScheduleDto);
            adminMapper.createSchedule(createScheduleDto);
        }

        return 0;
    }


    @Override
    public List<BusDto> getBusList() {
        return adminMapper.getBusList();
    }

    @Override
    public int deleteSchedule(int scheduleId) {
        return adminMapper.deleteSchedule(scheduleId);
    }
}
