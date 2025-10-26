package com.example.Vendor_data.service;

import com.example.Vendor_data.model.Cart;
import com.example.Vendor_data.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Transactional
    public Cart addToCart(Cart cart) {
        List<Cart> existingCartItems = cartRepository.findByUserId(cart.getUserId());

        // Remove all items if vendor changes
        if (!existingCartItems.isEmpty() &&
                existingCartItems.stream().anyMatch(c -> !c.getVendorId().equals(cart.getVendorId()))) {
            cartRepository.deleteByUserId(cart.getUserId());
        }

        // Check if same item already exists
        Cart existingItem = cartRepository.findByUserIdAndItemId(cart.getUserId(), cart.getItemId());

        if (existingItem != null) {
            // ✅ Increase quantity if same item
            existingItem.setQuantity(existingItem.getQuantity() + cart.getQuantity());
            return cartRepository.save(existingItem);
        } else {
            // ✅ Add new item
            return cartRepository.save(cart);
        }
    }

    public List<Cart> getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Transactional
    public void clearCartByUserId(Long userId) {
        cartRepository.deleteByUserId(userId);
    }

    @Transactional
    public void deleteCartItem(Long userId, Long itemId) {
        Cart cart = cartRepository.findByUserIdAndItemId(userId, itemId);
        if (cart != null) {
            cartRepository.delete(cart);
        }
    }
}
