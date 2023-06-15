package com.SOS.SmartOrderSystem.domain;

public class Owner {
    private String id;
    private String name;
    private String sex;
    private String email;
    private String phoneNumber;

    public Owner(String id, String name, String sex, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
