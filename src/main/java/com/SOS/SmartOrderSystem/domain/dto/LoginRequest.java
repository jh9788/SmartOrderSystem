package com.SOS.SmartOrderSystem.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Owner 만
@Getter @Setter
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "아이디를 입력해주세요.")
    private String id;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}