package com.SOS.SmartOrderSystem.domain.dto;
import com.SOS.SmartOrderSystem.domain.Owner;
// import com.SOS.SmartOrderSystem.domain.entity.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

// Owner 만
@Data //getter, setter, constructor
@AllArgsConstructor
@NoArgsConstructor
public class JoinRequest {
    @NotBlank(message = "아이디를 입력해주세요.")
    private String id;
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
    private String passwordCheck;
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;
    @NotBlank(message = "성을 입력해주세요.")
    private String gender;
    @NotBlank(message = "전화번호를 입력해주세요.")
    private String phoneNumber;
   // @Enumerated(EnumType.STRING)
   // private UserRole userRole;

    // 비밀번호 암호화 X
    public Owner toEntity() {
        return Owner.builder()
                .id(this.id)
                .password(this.password)
                .email(this.email)
                .gender(this.gender)
                .phoneNumber(this.phoneNumber)
                //.role(UserRole.ROLE_USER)
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
                //.role(UserRole.ROLE_USER)
                .build();
    }
}
