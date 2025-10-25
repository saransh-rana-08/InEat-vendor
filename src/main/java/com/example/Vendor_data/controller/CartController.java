package com.example.Vendor_data.controller;

import com.example.Vendor_data.dto.CartDTO;
import com.example.Vendor_data.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("*")
public class CartController {

    @Autowired
    private CartService cartService;

    // Add to cart
    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody CartDTO cartDTO) {
        cartService.addToCart(cartDTO);
        return ResponseEntity.ok("Item added to cart successfully!");
    }

    // Get all items for user
    @GetMapping("/{userId}")
    public ResponseEntity<List<CartDTO>> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    // Clear full cart for user
    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<String> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok("Cart cleared successfully for userId: " + userId);
    }

    // Delete single item from cart
    @DeleteMapping("/{userId}/item/{itemId}")
    public ResponseEntity<String> deleteItem(@PathVariable Long userId, @PathVariable Long itemId) {
        cartService.deleteItem(userId, itemId);
        return ResponseEntity.ok("Item removed successfully from cart for userId: " + userId);
    }
}
