package com.SOS.SmartOrderSystem.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
//jwt: 전자 서명이 된 토큰(json 형태)
//header: typ(토큰 타입), alg(토믄 서명에 사용된 해시 alg)
//payload: sub(토큰 주인), iat(토큰 발행된 시간, exp(토큰 만료된 시간)
@Service
public class JwtTokenProvider {
    private static final String SECRET_KEY = "Q4NSl604sgyHJj1qwEkR3ycUeR4uUAt7WJraD7EN3O9DVM4yyYuHxMEbSF4XXyYJkal13eqgB0F7Bq4H!@";
    //Jwt Token 발급
    private long refreshTokenValidTime = 1000 * 60 * 60;
    public String createToken(String id){
        // Claim = Jwt Token 에 들어갈 정보
        // Claim 에 id를 넣어 줌으로써 나중에 Id를 꺼냄
        Date now = new Date(System.currentTimeMillis());
        //Jwt 생성
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 암호화에 사용할 alg + secret key
                .setSubject(id).setIssuedAt(now).setExpiration(new Date(System.currentTimeMillis()+ refreshTokenValidTime))
                .compact(); // 생성

        return jwt;
    }

    // JWT 검증
    private Claims extractClaims(String token) {
        // 매개변수로 받은 Token 을 키를 사용해 복호화(디코딩)
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims;
    }

    // 검증 후 복호화된 Claims 에서 loginId 꺼내기
    public String getValidatedId(String token) {
        String ownerId = extractClaims(token).getSubject();
        return ownerId;
    }

    // 발급된 Token 이 만료 시간이 지났는지 체크
    public  boolean isExpired(String token) {
        Date expiredDate = extractClaims(token).getExpiration();
        // Token 의 만료 날짜가 지금보다 이전인지 check
        return expiredDate.before(new Date());
    }
}
