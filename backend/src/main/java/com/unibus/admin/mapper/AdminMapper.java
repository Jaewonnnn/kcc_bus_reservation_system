package com.unibus.admin.mapper;

import com.unibus.admin.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    public List<User> getUserList();
}
