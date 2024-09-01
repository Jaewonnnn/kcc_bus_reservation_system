package com.unibus.admin.mapper;

import com.unibus.admin.domain.Terminal;
import com.unibus.admin.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    public List<User> getUserList();

    public User getUserById(int id);

    public int updateUser(User user);

    public int deleteUser(int memberSeq);

    public List<Terminal> getTerminalList();
}
