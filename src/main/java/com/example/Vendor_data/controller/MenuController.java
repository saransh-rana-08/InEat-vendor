package com.example.Vendor_data.controller;

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

    // Get all menus for a vendor
    @GetMapping
    public List<Menu> getMenusByVendor(@PathVariable Long vendorId) {
        return menuService.getMenusByVendor(vendorId);
    }

    // Get specific menu item for a vendor
    @GetMapping("/{menuId}")
    public Menu getMenuById(@PathVariable Long vendorId, @PathVariable Long menuId) {
        return menuService.getMenuById(vendorId, menuId);
    }

    // Create new menu item for a vendor
    @PostMapping
    public Menu createMenu(@PathVariable Long vendorId, @RequestBody Menu menu) {
        return menuService.createMenu(vendorId, menu);
    }

    // Update menu item
    @PatchMapping("/{menuId}")
    public Menu updateMenu(@PathVariable Long vendorId, @PathVariable Long menuId,
                           @RequestBody Menu menu) {
        return menuService.updateMenu(vendorId, menuId, menu);
    }

    // Delete menu item
    @DeleteMapping("/{menuId}")
    public void deleteMenu(@PathVariable Long vendorId, @PathVariable Long menuId) {
        menuService.deleteMenu(vendorId, menuId);
    }
}
