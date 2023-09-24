package com.SOS.SmartOrderSystem.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Table {
    private long id;
    private String name;
    private String url;

    public Table(long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }
}
