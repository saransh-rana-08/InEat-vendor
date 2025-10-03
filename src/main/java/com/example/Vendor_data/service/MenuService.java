package com.example.Vendor_data.service;

import com.example.Vendor_data.model.Menu;
import com.example.Vendor_data.model.Vendor;
import com.example.Vendor_data.repository.MenuRepository;
import com.example.Vendor_data.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private VendorRepository vendorRepository;

    // Get all menus for a specific vendor
    public List<Menu> getMenusByVendor(Long vendorId) {
        return menuRepository.findByVendorId(vendorId);
    }

    // Get a specific menu by id and vendorId
    public Menu getMenuById(Long vendorId, Long menuId) {
        Optional<Menu> menu = menuRepository.findById(menuId);
        if (menu.isPresent() && menu.get().getVendor().getId().equals(vendorId)) {
            return menu.get();
        } else {
            throw new RuntimeException("Menu not found for this vendor");
        }
    }

    // Create a new menu for a vendor
    public Menu createMenu(Long vendorId, Menu menu) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));
        menu.setVendor(vendor);
        return menuRepository.save(menu);
    }

    // Update a menu for a vendor
    public Menu updateMenu(Long vendorId, Long menuId, Menu menuDetails) {
        Menu menu = getMenuById(vendorId, menuId);

        if (menuDetails.getItemName() != null) menu.setItemName(menuDetails.getItemName());
        if (menuDetails.getPrice() != null) menu.setPrice(menuDetails.getPrice());
        if (menuDetails.getQuantity() != null) menu.setQuantity(menuDetails.getQuantity());  // <-- update quantity

        return menuRepository.save(menu);
    }

    // Delete a menu for a vendor
    public void deleteMenu(Long vendorId, Long menuId) {
        Menu menu = getMenuById(vendorId, menuId);
        menuRepository.delete(menu);
    }
}
