package com.SOS.SmartOrderSystem.domain;

public class Order {
    private long id;
    private int quantity;
    private long menuId;
    private long storeId;
    private long TableId;

    public Order(long id, int quantity, long menuId, long storeId, long tableId) {
        this.id = id;
        this.quantity = quantity;
        this.menuId = menuId;
        this.storeId = storeId;
        TableId = tableId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public long getTableId() {
        return TableId;
    }

    public void setTableId(long tableId) {
        TableId = tableId;
    }
}
