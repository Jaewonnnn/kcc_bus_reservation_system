package com.unibus.admin.service;

import com.unibus.admin.domain.City;
import com.unibus.admin.domain.Terminal;
<<<<<<< Updated upstream
import com.unibus.admin.dto.AdminTerminalDto;
import com.unibus.admin.dto.UpdateTerminalDto;
import com.unibus.admin.dto.UserDto;
=======
import com.unibus.admin.dto.*;
>>>>>>> Stashed changes

import java.util.List;

public interface AdminService {
    public List<UserDto> getUserList();

    public UserDto getUserById(int id);

    public int updateUser(int id, UserDto userDto);

    public int deleteUser(int id);

    public List<Terminal> getTerminalList();

    public AdminTerminalDto getTerminalById(String id);

    public int createTerminal(AdminTerminalDto terminalDto);

    public List<City> getCityList();

    public int updateTerminal(String id, UpdateTerminalDto updateTerminalDto);

    public int deleteTerminal(String id);
<<<<<<< Updated upstream
=======

    public List<CompanyDto> getCompanyList();

    public CompanyDto getCompanyById(int id);

    public int createCompany(CompanyDto companyDto);

    public int updateCompany(int id, CompanyDto companyDto);

    public int deleteCompany(int id);

    public List<RouteDto> getRouteList();
>>>>>>> Stashed changes
}
