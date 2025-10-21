package com.example.Vendor_data.dto;

public class MenuDTO {

    private Long id;
    private String itemName;
    private Double price;
    private Boolean available;
    private String vendorName;
    private Long vendorId;

    public MenuDTO() {}

    public MenuDTO(Long id, String itemName, Double price, Boolean available, String vendorName, Long vendorId) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.available = available;
        this.vendorName = vendorName;
        this.vendorId = vendorId;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean available) {
        this.available = available != null ? available : true;
    }

    public String getVendorName() { return vendorName; }
    public void setVendorName(String vendorName) { this.vendorName = vendorName; }

    public Long getVendorId() { return vendorId; }
    public void setVendorId(Long vendorId) { this.vendorId = vendorId; }
}