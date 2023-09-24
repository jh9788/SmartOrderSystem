package com.SOS.SmartOrderSystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_table")
@Getter @Setter
public class Order {
    @Id
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
