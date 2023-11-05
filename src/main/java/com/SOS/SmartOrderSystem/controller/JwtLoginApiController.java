package com.SOS.SmartOrderSystem.controller;

import com.SOS.SmartOrderSystem.auth.JwtTokenProvider;
import com.SOS.SmartOrderSystem.config.SecurityConfig;
import com.SOS.SmartOrderSystem.domain.Owner;
import com.SOS.SmartOrderSystem.domain.dto.JoinRequest;
import com.SOS.SmartOrderSystem.domain.dto.LoginRequest;
import com.SOS.SmartOrderSystem.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class JwtLoginApiController {
    private final OwnerService ownerService;
    @PostMapping("/join")
    public String join(@RequestBody JoinRequest joinRequest){

        // loginId 중복 체크
        if(ownerService.checkLoginIdDuplicate(joinRequest.getId())) {
            return "로그인 아이디가 중복됩니다.";
        }
        // password 와 passwordCheck 가 같은지 체크
/*        if(!joinRequest.getPassword().equals(joinRequest.getPasswordCheck())) {
            return "바밀번호가 일치하지 않습니다.";
        }*/

        System.out.println("JwtLoginApiController.join");
        ownerService.join(joinRequest);
        System.out.println("JwtLoginApiController.join");
        return "회원가입 성공";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        String id = loginRequest.getId();

        Owner owner = ownerService.login(loginRequest);

        // 로그인 아이디나 비밀번호가 틀린 경우 global error return
        if(owner == null) {
            return "로그인 아이디 또는 비밀번호가 틀렸습니다.";
        }
        System.out.println("로그인 성공");

        // 로그인 성공 => Jwt Token 발급
        String secretKey = "my-secret-key!@";;
        long expireTimeMs = 1000 * 60 * 60;     // Token 유효 시간 = 360000ms => 60분

        String jwtToken = JwtTokenProvider.createToken(owner.getId(), secretKey, expireTimeMs);
        return jwtToken; // 토큰 생성
    }

    @GetMapping("/info")
    public String userInfo(@AuthenticationPrincipal String ownerId) {
        return "로그인된 사용자는 " + ownerId +"입니다.";
    }

    @GetMapping("/owner")
    public String ownerPage() {
        return "Owner 페이지 접근 성공";
    }
}
