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

    // Get all menus for a specific vendor
    public List<Menu> getMenusByVendor(Long vendorId) {
        return menuRepository.findByVendorId(vendorId);
    }

    // Get specific menu
    public Menu getMenuById(Long vendorId, Long menuId) {
        Optional<Menu> menu = menuRepository.findById(menuId);
        if (menu.isPresent() && menu.get().getVendorId().equals(vendorId)) {
            return menu.get();
        } else {
            throw new RuntimeException("Menu not found for this vendor");
        }
    }

    // Create menu for vendor
    public Menu createMenu(Long vendorId, Menu menu) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        menu.setVendorId(vendorId);
        menu.setVendorName(vendor.getName());
        menu.setVendor(vendor);

        // Default availability remains true
        menu.setAvailable(true);

        return menuRepository.save(menu);
    }

    // Update menu
    public Menu updateMenu(Long vendorId, Long menuId, Menu menuDetails) {
        Menu menu = getMenuById(vendorId, menuId);

        if (menuDetails.getItemName() != null) {
            menu.setItemName(menuDetails.getItemName());
        }
        if (menuDetails.getPrice() != null) {
            menu.setPrice(menuDetails.getPrice());
        }
        if (menuDetails.getAvailable() != null) {
            menu.setAvailable(menuDetails.getAvailable());
        }

        // ✅ NEW — update category if sent
        if (menuDetails.getCategory() != null) {
            menu.setCategory(menuDetails.getCategory());
        }

        // ✅ NEW — update description if sent
        if (menuDetails.getDescription() != null) {
            menu.setDescription(menuDetails.getDescription());
        }

        return menuRepository.save(menu);
    }

    // Delete menu
    public void deleteMenu(Long vendorId, Long menuId) {
        Menu menu = getMenuById(vendorId, menuId);
        menuRepository.delete(menu);
    }

    // Get all menus as DTOs
    public List<MenuDTO> getMenuDTOsByVendor(Long vendorId) {
        List<Menu> menus = menuRepository.findByVendorId(vendorId);
        return menus.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get specific menu as DTO
    public MenuDTO getMenuDTOById(Long vendorId, Long menuId) {
        Menu menu = getMenuById(vendorId, menuId);
        return convertToDTO(menu);
    }

    // Create menu DTO response
    public MenuDTO createMenuDTO(Long vendorId, Menu menu) {
        Menu savedMenu = createMenu(vendorId, menu);
        return convertToDTO(savedMenu);
    }

    // Update menu DTO response
    public MenuDTO updateMenuDTO(Long vendorId, Long menuId, Menu menuDetails) {
        Menu updatedMenu = updateMenu(vendorId, menuId, menuDetails);
        return convertToDTO(updatedMenu);
    }

    // Convert Menu entity to MenuDTO
    private MenuDTO convertToDTO(Menu menu) {
        MenuDTO dto = new MenuDTO();
        dto.setId(menu.getId());
        dto.setItemName(menu.getItemName());
        dto.setPrice(menu.getPrice());
        dto.setVendorId(menu.getVendorId());
        dto.setVendorName(menu.getVendorName());
        dto.setAvailable(menu.getAvailable());

        // ✅ NEW — Add category & description to DTO
        dto.setCategory(menu.getCategory());
        dto.setDescription(menu.getDescription());

        return dto;
    }
}
