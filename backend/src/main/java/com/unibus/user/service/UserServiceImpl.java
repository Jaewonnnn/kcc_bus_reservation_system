package com.unibus.user.service;

import com.unibus.user.domain.Member;
import com.unibus.user.domain.NonMember;
import com.unibus.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder;

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
    public Boolean updateWithdraw(String password, String memberId) {
        Member member2 = userMapper.getMemberByMemberId(memberId);
        String pass = member2.getMemberPass();

        if(encoder.matches(password, pass)) {
            return userMapper.withdrawMemberByMemberPass(pass, memberId) == 1;
        } else {
            return false;
        }
    }

    @Override
    public Boolean getMemberPass(String newPass, String memberId) {

        String pass = userMapper.getMemberPassByMemberId(memberId);

        if(encoder.matches(newPass, pass))
            return true;
        else return false;
    }

    @Override
    public int join(Member member) {


        if(isMemberIdDuplicate(member.getMemberId())) {
            member.setMemberRole("ROLE_USER");
            member.setWithdraw(true);
            member.setCreateDate(new Date());
            member.setMemberPass(encoder.encode(member.getMemberPass()));
            member.setMemberBirth(new Date());

            return userMapper.save(member);
        }else
            return 0;

    }

    @Override
    public boolean isMemberIdDuplicate(String memberId) {
        return userMapper.getMemberByMemberId(memberId) == null ? true : false;
    }

    @Override
    public Boolean nonUserSave(NonMember nonMember) {
        return userMapper.nonMemberSave(nonMember) > 0 ? true : false;
    }


    @Override
    public Boolean updatePassword(Member member) {
        Member m = userMapper.getMemberByMemberId(member.getMemberId());
        m.setMemberPass(member.getMemberPass());
        return userMapper.changeMemberPassByMemberId(m) > 0 ? true : false;
    }
}
