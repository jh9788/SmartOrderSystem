package com.SOS.SmartOrderSystem.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OwnerParam {
    private String id;
    private String password;

    public OwnerParam(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public OwnerParam(){

    }
}
