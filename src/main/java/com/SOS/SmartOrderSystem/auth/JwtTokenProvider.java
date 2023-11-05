package com.SOS.SmartOrderSystem.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
//jwt: 전자 서명이 된 토큰(json 형태)
//header: typ(토큰 타입), alg(토믄 서명에 사용된 해시 alg)
//payload: sub(토큰 주인), iat(토큰 발행된 시간, exp(토큰 만료된 시간)
@Service
public class JwtTokenProvider {
    //Jwt Token 발급
    public static String createToken(String id, String key, long expireTimeMs){
        // Claim = Jwt Token 에 들어갈 정보
        // Claim 에 id를 넣어 줌으로써 나중에 Id를 꺼냄
        Claims claims = Jwts.claims();
        claims.put("id",id);

        //Jwt 생성
        return Jwts.builder()
                .setClaims(claims) // Owner id 주입
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs)) // 만료일 지정(현재 날짜 + 1시간)
                .signWith(SignatureAlgorithm.HS256, key) // 암호화에 사용할 alg + secret key
                .compact(); // 생성
    }

    // JWT 검증
    private static Claims extractClaims(String token, String secretKey) {
        // 매개변수로 받은 Token을 키를 사용해 복호화 (디코딩)
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    // 검증 후 복호화된 Claims 에서 loginId 꺼내기
    public static String getValidatedId(String token, String secretKey) {
        return extractClaims(token, secretKey).get("id").toString();
    }

    // 발급된 Token 이 만료 시간이 지났는지 체크
    public static boolean isExpired(String token, String secretKey) {
        Date expiredDate = extractClaims(token, secretKey).getExpiration();
        // Token 의 만료 날짜가 지금보다 이전인지 check
        return expiredDate.before(new Date());
    }
}
