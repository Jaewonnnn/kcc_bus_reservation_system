package com.unibus.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    public Member getMemberByMemberId(@Param("memberId") String memberId);
    public Integer updateMemberByMemberId(Member member);
    public Integer withdrawMemberByMemberPass(Member member);
    public Integer selectDuplicationByMemberId(String memberId);
}
