package com.example.Vendor_data.service;

import com.example.Vendor_data.dto.MenuDTO;
import com.example.Vendor_data.model.Menu;
import com.example.Vendor_data.model.Vendor;
import com.example.Vendor_data.repository.MenuRepository;
import com.example.Vendor_data.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private VendorRepository vendorRepository;

    // Get all menus for a specific vendor (returns entities) - EXISTING
    public List<Menu> getMenusByVendor(Long vendorId) {
        return menuRepository.findByVendorId(vendorId);
    }

    // Get a specific menu by id and vendorId (returns entity) - EXISTING
    public Menu getMenuById(Long vendorId, Long menuId) {
        Optional<Menu> menu = menuRepository.findById(menuId);
        if (menu.isPresent() && menu.get().getVendorId().equals(vendorId)) {
            return menu.get();
        } else {
            throw new RuntimeException("Menu not found for this vendor");
        }
    }

    // Create a new menu for a vendor (returns entity) - UPDATED
    public Menu createMenu(Long vendorId, Menu menu) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        // Set vendor details in the menu
        menu.setVendorId(vendorId);
        menu.setVendorName(vendor.getName());
        menu.setVendor(vendor);

        // Always set available to true by default
        menu.setAvailable(true);

        return menuRepository.save(menu);
    }

    // Update a menu for a vendor (returns entity) - UPDATED
    public Menu updateMenu(Long vendorId, Long menuId, Menu menuDetails) {
        Menu menu = getMenuById(vendorId, menuId);

        if (menuDetails.getItemName() != null) {
            menu.setItemName(menuDetails.getItemName());
        }
        if (menuDetails.getPrice() != null) {
            menu.setPrice(menuDetails.getPrice());
        }
        // Don't update available from request, keep existing value
        // Only update if explicitly provided in PATCH request
        if (menuDetails.getAvailable() != null) {
            menu.setAvailable(menuDetails.getAvailable());
        }

        return menuRepository.save(menu);
    }

    // Delete a menu for a vendor - EXISTING
    public void deleteMenu(Long vendorId, Long menuId) {
        Menu menu = getMenuById(vendorId, menuId);
        menuRepository.delete(menu);
    }

    // Get all menus for a vendor as DTOs with vendor name and availability - UPDATED
    public List<MenuDTO> getMenuDTOsByVendor(Long vendorId) {
        List<Menu> menus = menuRepository.findByVendorId(vendorId);
        return menus.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get specific menu as DTO with vendor name and availability - UPDATED
    public MenuDTO getMenuDTOById(Long vendorId, Long menuId) {
        Menu menu = getMenuById(vendorId, menuId);
        return convertToDTO(menu);
    }

    // Create menu and return DTO with vendor name and availability - UPDATED
    public MenuDTO createMenuDTO(Long vendorId, Menu menu) {
        Menu savedMenu = createMenu(vendorId, menu);
        return convertToDTO(savedMenu);
    }

    // Update menu and return DTO with vendor name and availability - UPDATED
    public MenuDTO updateMenuDTO(Long vendorId, Long menuId, Menu menuDetails) {
        Menu updatedMenu = updateMenu(vendorId, menuId, menuDetails);
        return convertToDTO(updatedMenu);
    }

    // Helper method to convert Menu entity to MenuDTO - UPDATED
    private MenuDTO convertToDTO(Menu menu) {
        MenuDTO dto = new MenuDTO();
        dto.setId(menu.getId());
        dto.setItemName(menu.getItemName());
        dto.setPrice(menu.getPrice());
        dto.setVendorId(menu.getVendorId());
        dto.setVendorName(menu.getVendorName());
        dto.setAvailable(menu.getAvailable());
        return dto;
    }
}