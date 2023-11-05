package com.SOS.SmartOrderSystem.domain;

import com.SOS.SmartOrderSystem.domain.dto.JoinRequest;
import com.SOS.SmartOrderSystem.domain.dto.LoginRequest;
import com.SOS.SmartOrderSystem.domain.entity.UserRole;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@Entity // entity의 default 이름 = 클래스 이름(=Owner)
@Table(name = "owner_table") // DB에 있는 해당 테이브로가 현재 클래스 매핑
@Getter @Setter
@Builder
@NoArgsConstructor // 파라미터 없는 기본 생성자 자동 생성
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 자동 생성
public class Owner {

    @Id //기본키를 의미. 반드시 기본키를 가져야함.
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동 생성 -> identity: mysql 에서 auto_increment 실행
    private String id;
    @Column(length = 15, nullable = false)
    private String password;
    private String name;
    private String gender;
    private String email;
    private String phoneNumber;
    private UserRole role;
    //@AllArgsConstructor 가 자동생성
    /*  public Owner(String id, String password, String name, String sex, String email, String phoneNumber) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.gender = sex;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
*/
   /*@NoArgsConstructor 가 자동생성
    public Owner(){};
*/
    public Owner(JoinRequest joinRequestDto){
        this.id = joinRequestDto.getId();
        this.password = joinRequestDto.getPassword();
        this.name = joinRequestDto.getName();
        this.gender = joinRequestDto.getGender();
        this.email = joinRequestDto.getEmail();
        this.phoneNumber = joinRequestDto.getPhoneNumber();
    }

}
