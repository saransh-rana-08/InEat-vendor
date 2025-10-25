package com.example.Vendor_data.service;

import com.example.Vendor_data.dto.CartDTO;
import com.example.Vendor_data.model.Cart;
import com.example.Vendor_data.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    // Add item to cart using DTO
    public void addToCart(CartDTO dto) {
        List<Cart> existingCart = cartRepository.findByUserId(dto.getUserId());

        // Clear previous vendor items if vendor changed
        if (!existingCart.isEmpty()) {
            Long existingVendorId = existingCart.get(0).getVendorId();
            if (!existingVendorId.equals(dto.getVendorId())) {
                cartRepository.deleteByUserId(dto.getUserId());
            }
        }

        Cart cart = new Cart(
                dto.getUserId(),
                dto.getItemId(),
                dto.getName(),
                dto.getQuantity(),
                dto.getVendorId(),
                dto.getVendorName()
        );

        cartRepository.save(cart);
    }

    // Get cart items and return as DTO
    public List<CartDTO> getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId)
                .stream()
                .map(c -> new CartDTO(
                        c.getUserId(),
                        c.getItemId(),
                        c.getName(),
                        c.getQuantity(),
                        c.getVendorId(),
                        c.getVendorName()))
                .collect(Collectors.toList());
    }

    // Clear all cart items
    public void clearCart(Long userId) {
        cartRepository.deleteByUserId(userId);
    }

    // Delete single item
    public void deleteItem(Long userId, Long itemId) {
        cartRepository.deleteItemFromCart(userId, itemId);
    }
}
