package com.unibus.Exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.net.URLEncoder;

//@Configuration
public class LoginFailureHandler implements AuthenticationFailureHandler {

    private static final Logger log = LoggerFactory.getLogger(LoginFailureHandler.class);

    @Override
    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMsg = "";

        log.info(exception.getClass().toString());

        if(exception instanceof AuthenticationCredentialsNotFoundException ){
            errorMsg = "탈퇴한 계정입니다. 다른 계정으로 로그인해주세요";
        } else if(exception instanceof UsernameNotFoundException) {
            errorMsg = "존재하지 않는 아이디입니다.";
        } else if(exception instanceof BadCredentialsException|| exception instanceof InternalAuthenticationServiceException){
            errorMsg = "아이디 혹은 비밀번호를 잘못 입력하셨습니다.";
        }

        if(!StringUtils.isEmpty(errorMsg)){
            request.setAttribute("errorMsg", errorMsg);

        }
        log.info(exception.getClass().toString());
        log.info("errorMsg = {}", errorMsg);

        errorMsg = URLEncoder.encode(errorMsg, "UTF-8");
        response.sendRedirect("/user/login?error=true&exception=" +errorMsg);
    }
}
