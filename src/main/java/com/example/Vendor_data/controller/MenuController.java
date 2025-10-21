package com.example.Vendor_data.controller;

import com.example.Vendor_data.dto.MenuDTO;
import com.example.Vendor_data.model.Menu;
import com.example.Vendor_data.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendors/{vendorId}/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    // Get all menus for a vendor - returns DTOs with vendor name and availability
    @GetMapping
    public List<MenuDTO> getMenusByVendor(@PathVariable Long vendorId) {
        return menuService.getMenuDTOsByVendor(vendorId);
    }

    // Get specific menu item for a vendor - returns DTO with vendor name and availability
    @GetMapping("/{menuId}")
    public MenuDTO getMenuById(@PathVariable Long vendorId, @PathVariable Long menuId) {
        return menuService.getMenuDTOById(vendorId, menuId);
    }

    // Create new menu item for a vendor - returns DTO with vendor name and availability
    @PostMapping
    public MenuDTO createMenu(@PathVariable Long vendorId, @RequestBody Menu menu) {
        return menuService.createMenuDTO(vendorId, menu);
    }

    // Update menu item - returns DTO with vendor name and availability
    @PatchMapping("/{menuId}")
    public MenuDTO updateMenu(@PathVariable Long vendorId, @PathVariable Long menuId,
                              @RequestBody Menu menu) {
        return menuService.updateMenuDTO(vendorId, menuId, menu);
    }

    // Delete menu item
    @DeleteMapping("/{menuId}")
    public void deleteMenu(@PathVariable Long vendorId, @PathVariable Long menuId) {
        menuService.deleteMenu(vendorId, menuId);
    }
}