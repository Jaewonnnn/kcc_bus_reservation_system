package com.unibus.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;

    @Override
    public Member userInfo(String memberId) {
        return userMapper.getMemberByMemberId(memberId);
    }

    @Override
    @Transactional
    public Integer updateMember(Member member) {
        return userMapper.updateMemberByMemberId(member);
    }

    @Override
    @Transactional
    public Integer updateWithdraw(Member member) {
        Member member2 = userMapper.getMemberByMemberId(member.getMemberId());
        if(member2.getMemberPass().equals(member.getMemberPass())) {
            return userMapper.withdrawMemberByMemberPass(member);
        } else {
            return 0;
        }
    }

    @Override
    public Boolean checkMemberId(String memberId) {
        return userMapper.selectDuplicationByMemberId(memberId) == 1 ? false : true;
    }



}
