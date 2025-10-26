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
        List<Cart> existingItems = cartRepository.findByUserId(cart.getUserId());

        // ✅ Clear previous vendor's cart if different vendor
        if (!existingItems.isEmpty() &&
                existingItems.stream().anyMatch(c -> !c.getVendorId().equals(cart.getVendorId()))) {
            cartRepository.deleteByUserId(cart.getUserId());
        }

        // ✅ Find if this item already exists
        Cart existingItem = cartRepository.findByUserIdAndItemId(cart.getUserId(), cart.getItemId());

        if (existingItem != null) {
            if (cart.getQuantity() <= 0) {
                // If quantity = 0 → delete the item
                cartRepository.delete(existingItem);
                return null;
            }
            // Update quantity
            existingItem.setQuantity(cart.getQuantity());
            return cartRepository.save(existingItem);
        } else {
            // Insert new item if not found and quantity > 0
            if (cart.getQuantity() > 0) {
                return cartRepository.save(cart);
            }
        }
        return null;
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
