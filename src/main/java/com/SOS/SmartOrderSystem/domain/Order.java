package com.SOS.SmartOrderSystem.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Order {
    private long id;
    private int quantity;
    private long menuId;
    private long storeId;
    private long tableId;

    public Order(long id, int quantity, long menuId, long storeId, long tableId) {
        this.id = id;
        this.quantity = quantity;
        this.menuId = menuId;
        this.storeId = storeId;
        this.tableId = tableId;
    }

    public Order(){}

}
