package com.SOS.SmartOrderSystem.domain;

public class Owner {
    private long ID;
    private String name;
    private String sex;
    private String email;
    private String phoneNumber;

    public Owner(long ID, String name, String sex, String email, String phoneNumber) {
        this.ID = ID;
        this.name = name;
        this.sex = sex;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
