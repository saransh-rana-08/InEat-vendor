package com.example.Vendor_data.repository;

import com.example.Vendor_data.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByVendorId(Long vendorId);
}