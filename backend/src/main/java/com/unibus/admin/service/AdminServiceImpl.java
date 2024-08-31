package com.unibus.admin.service;

import com.unibus.admin.domain.User;
import com.unibus.admin.dto.UserDto;
import com.unibus.admin.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
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
}
