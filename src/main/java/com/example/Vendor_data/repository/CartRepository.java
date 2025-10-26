package com.example.Vendor_data.repository;

import com.example.Vendor_data.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUserId(Long userId);

    Cart findByUserIdAndItemId(Long userId, Long itemId);

    @Transactional
    @Modifying
    void deleteByUserId(Long userId);
}
