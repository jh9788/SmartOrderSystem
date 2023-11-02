package com.SOS.SmartOrderSystem.domain.dto;
import com.SOS.SmartOrderSystem.domain.Owner;
import com.SOS.SmartOrderSystem.domain.entity.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Owner 만
@Getter
@Setter
@NoArgsConstructor
public class JoinRequest {
    @NotBlank(message = "아이디를 입력해주세요.")
    private String id;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
    private String passwordCheck;
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;
    @NotBlank(message = "성을 입력해주세요.")
    private String gender;
    @NotBlank(message = "전화번호를 입력해주세요.")
    private String phoneNumber;

    // 비밀번호 암호화 X
    public Owner toEntity() {
        return Owner.builder()
                .id(this.id)
                .password(this.password)
                .email(this.email)
                .gender(this.gender)
                .phoneNumber(this.phoneNumber)
                .role(UserRole.Owner)
                .build();
    }

    // 비밀번호 암호화
    public Owner toEntity(String encodedPassword) {
        return Owner.builder()
                .id(this.id)
                .password(encodedPassword)
                .email(this.email)
                .gender(this.gender)
                .phoneNumber(this.phoneNumber)
                .role(UserRole.Owner)
                .build();
    }
}
