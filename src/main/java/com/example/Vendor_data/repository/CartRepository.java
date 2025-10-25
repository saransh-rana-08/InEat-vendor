package com.example.Vendor_data.repository;

import com.example.Vendor_data.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUserId(Long userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Cart c WHERE c.userId = :userId")
    void deleteByUserId(Long userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Cart c WHERE c.userId = :userId AND c.itemId = :itemId")
    void deleteItemFromCart(Long userId, Long itemId);
}
