package com.unibus.user.mapper;

import com.unibus.user.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<Member> getUserList();
}
