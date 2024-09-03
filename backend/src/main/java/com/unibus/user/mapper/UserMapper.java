package com.unibus.user.mapper;

import com.unibus.user.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    public Member getMemberByMemberId(@Param("memberId") String memberId);
    public Integer updateMemberByMemberId(Member member);
    public Integer withdrawMemberByMemberPass(@Param("memberPass") String password, @Param("memberId") String memberId);
    public Integer selectDuplicationByMemberId(String memberId);
    public String getMemberPassByMemberId(String memberId);
    public int save(Member member);
    public Integer changeMemberPassByMemberId(Member member);
}
