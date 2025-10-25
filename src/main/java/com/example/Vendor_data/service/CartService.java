package com.example.Vendor_data.service;

import com.example.Vendor_data.model.Cart;
import com.example.Vendor_data.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // ✅ important

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getCartByUser(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Transactional  // ✅ ensures deleteByUserId runs inside a transaction
    public Cart addToCart(Long userId, Long vendorId, String vendorName,
                          Long itemId, String name, int quantity) {

        List<Cart> existingCart = cartRepository.findByUserId(userId);

        // If cart exists with a different vendor → clear it
        if (!existingCart.isEmpty() && !existingCart.get(0).getVendorId().equals(vendorId)) {
            cartRepository.deleteByUserId(userId); // ✅ now transactional
        }

        // If same item from same vendor already exists → update quantity
        for (Cart item : existingCart) {
            if (item.getItemId().equals(itemId) && item.getVendorId().equals(vendorId)) {
                item.setQuantity(item.getQuantity() + quantity);
                return cartRepository.save(item);
            }
        }

        // Add new item
        Cart cartItem = new Cart(userId, vendorId, vendorName, itemId, name, quantity);
        return cartRepository.save(cartItem);
    }

    @Transactional
    public void clearCart(Long userId) {
        cartRepository.deleteByUserId(userId);
    }
}
