package com.SOS.SmartOrderSystem.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Owner {
    private String id;
    private String password;
    private String name;
    private String gender;
    private String email;
    private String phoneNumber;

    public Owner(String id, String password, String name, String sex, String email, String phoneNumber) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.gender = sex;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Owner(){};

}
