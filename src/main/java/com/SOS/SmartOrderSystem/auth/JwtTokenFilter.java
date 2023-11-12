package com.SOS.SmartOrderSystem.auth;

import com.SOS.SmartOrderSystem.domain.Owner;
import com.SOS.SmartOrderSystem.service.OwnerService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

// 사용자의 요청에서 Jwt Token 을 추출해 통과하면 권한을 부여하고 실패하면 권한을 부여하지 않고 다음 필터 진행

// OncePerRequestFilter : 매번 들어갈 때 마다 체크 해주는 필터
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter { //OncePerRequestFilter 을 상속받아
    // Request가 들어 올 때 Request Header의 Authorization 필드의 Bearer Token 가져옴
    // 가져온 토큰을 검증하고 검증 결과를 SecurityContext에 추가
    private final JwtTokenProvider jwtTokenProvider;
    private final OwnerService ownerService;
    @Override // 추상 메소드로 구현되어있는 doFilterInternal 를 구현
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Header의 Authorization 값이 비어있으면 => Jwt Token을 전송하지 않음 => 로그인 하지 않음
        if(authorizationHeader == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // Header의 Authorization 값이 'Bearer '로 시작하지 않으면 => 잘못된 토큰
        if(!authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 전송받은 값에서 'Bearer ' 뒷부분(Jwt Token) 추출
        String token = authorizationHeader.split(" ")[1];

        // 전송받은 Jwt Token이 만료되었으면 => 다음 필터 진행(인증 X)
        if(jwtTokenProvider.isExpired(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        // Jwt Token에서 loginId 추출
        String loginId = jwtTokenProvider.getValidatedId(token);

        // 추출한 loginId로 User 찾아오기
        Owner owner = ownerService.getLoginOwnerById(loginId);

        // loginUser 정보로 UsernamePasswordAuthenticationToken 발급
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                owner.getId(), null, List.of(new SimpleGrantedAuthority(owner.getRole().name())));
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // 권한 부여
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
