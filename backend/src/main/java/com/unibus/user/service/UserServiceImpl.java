package com.unibus.user.service;

import com.unibus.user.domain.Member;
import com.unibus.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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
    public Boolean checkMemberId(String memberId) {
        return userMapper.selectDuplicationByMemberId(memberId) == 1 ? false : true;
    }

    @Override
    public Boolean getMemberPass(String newPass, String memberId) {

        String pass = userMapper.getMemberPassByMemberId(memberId);

        if(encoder.matches(newPass, pass))
            return true;
        else return false;
    }

    @Override
    public Boolean join(Member member) {
        return userMapper.save(member) == 1;

    }

    @Override
    public Boolean updatePassword(Member member) {
        Member m = userMapper.getMemberByMemberId(member.getMemberId());
        m.setMemberPass(member.getMemberPass());
        return userMapper.changeMemberPassByMemberId(m) > 0 ? true : false;
    }


}
