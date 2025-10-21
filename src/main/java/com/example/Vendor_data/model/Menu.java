package com.example.Vendor_data.model;

import jakarta.persistence.*;

@Entity
@Table(name = "menus")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name")
    private String itemName;

    private Double price;

    @Column(name = "vendor_id")
    private Long vendorId;

    @Column(name = "vendor_name")
    private String vendorName;

    private Boolean available = true;

    @ManyToOne
    @JoinColumn(name = "vendor_id", insertable = false, updatable = false)
    private Vendor vendor;

    // Constructors
    public Menu() {}

    // Existing constructor (backward compatible)
    public Menu(String itemName, Double price, Vendor vendor) {
        this.itemName = itemName;
        this.price = price;
        this.vendor = vendor;
        if (vendor != null) {
            this.vendorId = vendor.getId();
            this.vendorName = vendor.getName();
        }
        this.available = true; // Default true
    }

    // New constructor with all fields
    public Menu(String itemName, Double price, Long vendorId, String vendorName, Boolean available) {
        this.itemName = itemName;
        this.price = price;
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.available = available != null ? available : true;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Long getVendorId() { return vendorId; }
    public void setVendorId(Long vendorId) { this.vendorId = vendorId; }

    public String getVendorName() { return vendorName; }
    public void setVendorName(String vendorName) { this.vendorName = vendorName; }

    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean available) {
        this.available = available != null ? available : true;
    }

    public Vendor getVendor() { return vendor; }
    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
        if (vendor != null) {
            this.vendorId = vendor.getId();
            this.vendorName = vendor.getName();
        }
    }
}