package com.example.Vendor_data.dto;

public class CartDTO {
    private Long itemId;
    private String name;
    private int quantity;

    public CartDTO() {}

    public CartDTO(Long itemId, String name, int quantity) {
        this.itemId = itemId;
        this.name = name;
        this.quantity = quantity;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
