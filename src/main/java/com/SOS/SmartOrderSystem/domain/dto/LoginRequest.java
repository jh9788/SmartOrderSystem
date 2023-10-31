package com.SOS.SmartOrderSystem.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Owner 만
@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {
    private String id;
    private String password;

}