package com.example.Vendor_data.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long vendorId;
    private String vendorName;
    private Long itemId;
    private String name;
    private int quantity;

    public Cart() {}

    public Cart(Long userId, Long vendorId, String vendorName, Long itemId, String name, int quantity) {
        this.userId = userId;
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.itemId = itemId;
        this.name = name;
        this.quantity = quantity;
    }

    // Getters and setters
    public Long getId() { return id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getVendorId() { return vendorId; }
    public void setVendorId(Long vendorId) { this.vendorId = vendorId; }

    public String getVendorName() { return vendorName; }
    public void setVendorName(String vendorName) { this.vendorName = vendorName; }

    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
