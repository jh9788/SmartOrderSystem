package com.SOS.SmartOrderSystem.config;

import com.SOS.SmartOrderSystem.auth.JwtTokenFilter;
import com.SOS.SmartOrderSystem.domain.entity.UserRole;
import com.SOS.SmartOrderSystem.service.OwnerService;
import lombok.RequiredArgsConstructor;
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
    private final OwnerService ownerService;
    private static String secretKey = "my-secret-key-123123";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtTokenFilter(ownerService, secretKey), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .requestMatchers("/jwt-login/info").authenticated()
                .requestMatchers("/jwt-login/Owner/**").hasAuthority(UserRole.Owner.name())
                .and().build();
    }
}
