package com.SOS.SmartOrderSystem.domain;

import com.SOS.SmartOrderSystem.domain.entity.UserRole;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "owner_table")
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Owner {

    @Id //기본키를 의미. 반드시 기본키를 가져야함.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
