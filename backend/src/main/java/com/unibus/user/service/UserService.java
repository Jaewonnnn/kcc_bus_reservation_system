package com.unibus.user.service;

import com.unibus.user.domain.Member;

import java.util.List;

import org.springframework.security.config.annotation.web.configurers.UrlAuthorizationConfigurer.StandardInterceptUrlRegistry;

public interface UserService {
    public Member userInfo(String memberId);
    public Integer updateMember(Member member);
    public Integer updateWithdraw(Member member);
    public Boolean checkMemberId(String memberId);
    public boolean getMemberPass(String newPass, String memberId);
    public Boolean join(Member member);
}
