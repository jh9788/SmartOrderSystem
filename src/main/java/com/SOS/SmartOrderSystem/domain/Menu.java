package com.SOS.SmartOrderSystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "menu_table")
@Getter @Setter
public class Menu {

    @Id
    private long id;
    private String name;
    private long price;
    private long storeId;
    private String imagePath;

    public Menu(long id, String name, long price, long storeId, String imagePath) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.storeId = storeId;
        this.imagePath = imagePath;
    }

    public Menu(){};
}
