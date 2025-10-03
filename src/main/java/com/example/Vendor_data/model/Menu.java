package com.example.Vendor_data.model;

import jakarta.persistence.*;

@Entity
@Table(name = "menus")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private Double price;

    private Integer quantity;  // <-- new field

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    // Constructors
    public Menu() {}

    public Menu(String itemName, Double price, Integer quantity, Vendor vendor) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.vendor = vendor;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Vendor getVendor() { return vendor; }
    public void setVendor(Vendor vendor) { this.vendor = vendor; }
}
