package com.example.Vendor_data.dto;

public class OrderDTO {

    private Long id;

    private Long customerId;
    private String customerName;

    private Long menuId;
    private String menuName;

    private Long vendorId;
    private String vendorName;

    private Integer quantity;
    private Double totalPrice;

    private String status;

    // =============================
    // GETTERS & SETTERS
    // =============================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public Long getMenuId() { return menuId; }
    public void setMenuId(Long menuId) { this.menuId = menuId; }

    public String getMenuName() { return menuName; }
    public void setMenuName(String menuName) { this.menuName = menuName; }

    public Long getVendorId() { return vendorId; }
    public void setVendorId(Long vendorId) { this.vendorId = vendorId; }

    public String getVendorName() { return vendorName; }
    public void setVendorName(String vendorName) { this.vendorName = vendorName; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // =============================
    // ⭐ OPTIONAL: PATCH HELPER
    // =============================
    // This will merge only non-null fields from dto into this object
    public void merge(OrderDTO dto) {
        if (dto.getCustomerId() != null) this.customerId = dto.getCustomerId();
        if (dto.getCustomerName() != null) this.customerName = dto.getCustomerName();
        if (dto.getMenuId() != null) this.menuId = dto.getMenuId();
        if (dto.getMenuName() != null) this.menuName = dto.getMenuName();
        if (dto.getVendorId() != null) this.vendorId = dto.getVendorId();
        if (dto.getVendorName() != null) this.vendorName = dto.getVendorName();
        if (dto.getQuantity() != null) this.quantity = dto.getQuantity();
        if (dto.getTotalPrice() != null) this.totalPrice = dto.getTotalPrice();
        if (dto.getStatus() != null) this.status = dto.getStatus();
    }
}
