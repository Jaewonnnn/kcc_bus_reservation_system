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

    @Override
    public boolean getMemberPass(String newPass, String memberId) {
        log.info("newPass = {}", newPass);

        String pass = userMapper.getMemberPassByMemberId(memberId);

        log.info("pass = {}", pass);
        log.info("newpass = {}", encoder.encode(newPass));

        log.info("matches = {}", encoder.matches(newPass, pass));

        if(encoder.matches(newPass, pass))
            return true;
        else return false;
    }

    @Override
    public Boolean join(Member member) {
        return userMapper.save(member) == 1;

    }


}
