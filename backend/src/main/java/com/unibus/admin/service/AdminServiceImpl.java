package com.unibus.admin.service;

import com.unibus.admin.domain.Terminal;
import com.unibus.admin.domain.User;
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
        User user = adminMapper.getUserById(id);

        user.setMemberName(userDto.getName());
        user.setMemberTel(userDto.getPhoneNumber());
        user.setMemberEmail(userDto.getEmail());

        return adminMapper.updateUser(user);
    }

    @Override
    public int deleteUser(int id) {
        return adminMapper.deleteUser(id);
    }

    @Override
    public List<Terminal> getTerminalList() {
        return adminMapper.getTerminalList();
    }
}
