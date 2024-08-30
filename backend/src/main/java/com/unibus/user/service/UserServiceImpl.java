package com.unibus.user.service;

import com.unibus.user.domain.Member;
import com.unibus.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;

    @Override
    public List<Member> getUserList() {
        return userMapper.getUserList();
    }
}
