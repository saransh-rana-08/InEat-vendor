package com.example.Vendor_data.repository;

import com.example.Vendor_data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByVendorId(Long vendorId);
    List<Order> findByCustomerId(Long customerId);


}
