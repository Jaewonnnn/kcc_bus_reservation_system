package com.unibus.admin.service;

import com.unibus.admin.dto.UserDto;

import java.util.List;

public interface AdminService {
    public List<UserDto> getUserList();

    public UserDto getUserById(int id);
}
