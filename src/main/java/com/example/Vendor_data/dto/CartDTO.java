package com.example.Vendor_data.dto;

public class CartDTO {
    private Long userId;
    private Long itemId;
    private String name;
    private int quantity;
    private Long vendorId;
    private String vendorName;

    public CartDTO() {}

    public CartDTO(Long userId, Long itemId, String name, int quantity, Long vendorId, String vendorName) {
        this.userId = userId;
        this.itemId = itemId;
        this.name = name;
        this.quantity = quantity;
        this.vendorId = vendorId;
        this.vendorName = vendorName;
    }

    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Long getVendorId() { return vendorId; }
    public void setVendorId(Long vendorId) { this.vendorId = vendorId; }

    public String getVendorName() { return vendorName; }
    public void setVendorName(String vendorName) { this.vendorName = vendorName; }
}
