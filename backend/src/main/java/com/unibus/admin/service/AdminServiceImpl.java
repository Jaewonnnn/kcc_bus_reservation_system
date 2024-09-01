package com.unibus.admin.service;

import com.unibus.admin.domain.City;
import com.unibus.admin.domain.Terminal;
import com.unibus.admin.domain.User;
import com.unibus.admin.dto.AdminTerminalDto;
import com.unibus.admin.dto.UpdateTerminalDto;
import com.unibus.admin.dto.UserDto;
import com.unibus.admin.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}
