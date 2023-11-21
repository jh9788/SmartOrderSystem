package com.SOS.SmartOrderSystem.config;

import com.SOS.SmartOrderSystem.auth.JwtTokenFilter;
// import com.SOS.SmartOrderSystem.domain.entity.UserRole;
import com.SOS.SmartOrderSystem.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;
    // Jwt 생성 및 검증을 위한 키
    //Spring Security 에서 로그인을 진행해주는 Filter(UsernamePasswordAuthenticationFilter)에 가기 전 JwtTokenFilter을 거치도록
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
             httpSecurity
                     .cors().and()
                     .csrf().disable() // csrf 대책(현재는 csrf 에 대한 대책 비활성화)
                     .httpBasic().disable()// basic 과 bearer 중 bearer token 인증 방법 쓰므로 basic 비활성화
                     // 현재는 세션 기반 인증 사용 x -> 비활성화
                     .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                     .requestMatchers("/api/login").permitAll() // 로그인 허용(인증 하지 않고 사용 가능)
                     .requestMatchers("/api/join").permitAll() // 로그인 허용(인증 하지 않고 사용 가능)
                .anyRequest().authenticated(); // 나머지 Request 에 대해서는 인증된 사용자만 사용 가능

        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
