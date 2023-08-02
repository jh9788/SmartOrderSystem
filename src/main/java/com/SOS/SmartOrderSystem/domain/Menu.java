package com.SOS.SmartOrderSystem.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Menu {

    private long id;
    private String name;
    private long price;

    public Menu(long id, String name, long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

}
