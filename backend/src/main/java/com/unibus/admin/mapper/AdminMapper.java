package com.unibus.admin.mapper;

import com.unibus.admin.domain.City;
import com.unibus.admin.domain.Terminal;
import com.unibus.admin.domain.User;
import com.unibus.admin.dto.AdminTerminalDto;
import com.unibus.admin.dto.CompanyDto;
import com.unibus.admin.dto.UpdateTerminalDto;
import com.unibus.admin.dto.UserDto;
import com.unibus.admin.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    public List<User> getUserList();

    public User getUserById(int id);

    public int updateUser(UserDto userDto);

    public int deleteUser(int memberSeq);

    public List<Terminal> getTerminalList();

    public AdminTerminalDto getTerminalById(String id);

    public List<City> getCityList();

    public int createTerminal(AdminTerminalDto terminalDto);

    public int updateTerminal(UpdateTerminalDto updateTerminalDto);

    public int deleteTerminal(String id);

    public List<CompanyDto> getCompanyList();

    public CompanyDto getCompanyById(int id);

    public int createCompany(CompanyDto companyDto);

    public int updateCompany(CompanyDto companyDto);

    public int deleteCompany(int id);

    public List<RouteDto> getRouteList();

    public String getTerminalId(String terminalName);

    public int createRoute(RouteCreateDto routeCreateDto);

    public int updateRoute(RouteCreateDto routeCreateDto);

    public int deleteRoute(String routeId);

    public List<AdminScheduleDto> getScheduleList();

    public AdminScheduleDto getScheduleById(int id);
}
