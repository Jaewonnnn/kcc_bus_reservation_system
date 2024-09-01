package com.unibus.admin.service;

import com.unibus.admin.domain.User;
import com.unibus.admin.dto.UserDto;

import java.util.List;

public interface AdminService {
    public List<UserDto> getUserList();

    public UserDto getUserById(int id);

    public int updateUser(int id, UserDto userDto);

    public int deleteUser(int id);
}
