package com.example.Vendor_data.dto;

public class MenuDTO {

    private String itemName;
    private Double price;

    public MenuDTO() {}

    public MenuDTO(String itemName, Double price) {
        this.itemName = itemName;
        this.price = price;
    }

    // Getters and Setters
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}
