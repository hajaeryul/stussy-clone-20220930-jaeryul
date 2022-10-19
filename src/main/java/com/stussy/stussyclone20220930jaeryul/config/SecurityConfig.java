package com.stussy.stussyclone20220930jaeryul.config;


import com.stussy.stussyclone20220930jaeryul.security.AuthFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable(); //로그인창 안쓰겠다.
        http.authorizeRequests() // 모든 요청이 들어오면
                .antMatchers("/account/mypage", "/index") // () 주소로 요청이 들어오면
                .authenticated() // 인증을 거쳐라
//                .antMatchers("/admin/**") // 이 주소의 하위로 어떤 주소가 들어오던간에
//                .hasRole("ADMIN") // Admin 이어야함!!
                .antMatchers("/admin/**", "/api/admin/**")
                .permitAll()
                .anyRequest() // 다른 요청들은
                .permitAll() // 권한을 부여해라 (허용해라)
                .and() // 이 앞까지가 authorizeRequest 설정임.
                .formLogin() // 여기서부터 formLogin 설정이야
                .usernameParameter("email") // username이 아니라 email로 받을것이다
                .loginPage("/account/login") // 인증이 필요하면 폼로그인 시키는데, () 페이지로 보내라...login page Get 요청
                .loginProcessingUrl("/account/login") // login service Post 요청
                .failureHandler(new AuthFailureHandler())
                .defaultSuccessUrl("/index");
    }
}
