package com.SOS.SmartOrderSystem.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Store {
    private long id;
    private String name;

    public Store(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
