package com.example.Vendor_data.dto;

public class OrderDTO {

    private Long menuId;
    private Integer quantity;

    public OrderDTO() {}

    public OrderDTO(Long menuId, Integer quantity) {
        this.menuId = menuId;
        this.quantity = quantity;
    }

    public Long getMenuId() { return menuId; }
    public void setMenuId(Long menuId) { this.menuId = menuId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
