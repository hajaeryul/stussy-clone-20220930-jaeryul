package com.stussy.stussyclone20220930jaeryul.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFailureHandler implements AuthenticationFailureHandler {
    //로그인실패
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (exception.getClass() == UsernameNotFoundException.class || exception.getClass() == BadCredentialsException.class) {
            response.sendRedirect("/account/login?error=auth");//sendRedirect를 서버에서 만나면 요청을 끝내버리고 요청을 다시날린다. location으로
        }else if(exception.getClass() == CredentialsExpiredException.class){
            response.sendRedirect("/account/login?error=passwordExpired");
        }else {
            response.sendRedirect("/account/login?error");
        }
    }
}
