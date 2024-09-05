package com.unibus.user.service;

import com.unibus.user.domain.Member;

import java.util.List;

import com.unibus.user.domain.NonMember;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.config.annotation.web.configurers.UrlAuthorizationConfigurer.StandardInterceptUrlRegistry;

public interface UserService {
    public Member userInfo(String memberId);
    public Integer updateMember(Member member);
    public Boolean updateWithdraw(String password, String memberId);
    public Boolean getMemberPass(String newPass, String memberId);
    public int join(Member member);
    public Boolean updatePassword(Member member);
    public boolean isMemberIdDuplicate(String memberId);
    public Boolean nonUserSave(NonMember nonMember);
    public String getMemberRole(String memberId);
}
