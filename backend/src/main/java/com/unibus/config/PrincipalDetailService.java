package com.unibus.config;

import com.unibus.user.domain.Member;
import com.unibus.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalDetailService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, AuthenticationCredentialsNotFoundException {
        Member member = userService.userInfo(username);
        log.info("member ={}",  member);
        boolean flag = member.isWithdraw();
        if(!member.isWithdraw())
            throw new AuthenticationCredentialsNotFoundException("dddddddddddddddddddddddddddddddddddddddddddddddddd");

        log.info("flag : {}" + flag);

        if (member != null) {
            return new PrincipalDetail(member);
        } else if (member == null) {
            throw new UsernameNotFoundException("사용자가 존재하지 않습니다.");
        }
        return null;
    }
}
