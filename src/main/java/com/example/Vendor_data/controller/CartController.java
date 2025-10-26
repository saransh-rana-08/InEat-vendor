package com.example.Vendor_data.controller;

import com.example.Vendor_data.dto.CartDTO;
import com.example.Vendor_data.model.Cart;
import com.example.Vendor_data.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // ✅ Add, update, or delete (if quantity = 0)
    @PostMapping("/add")
    public Cart addToCart(@RequestBody CartDTO cartDTO) {
        Cart cart = new Cart(
                cartDTO.getUserId(),
                cartDTO.getItemId(),
                cartDTO.getName(),
                cartDTO.getQuantity(),
                cartDTO.getVendorId(),
                cartDTO.getVendorName()
        );
        return cartService.addToCart(cart);
    }

    // ✅ Get all cart items by user
    @GetMapping("/{userId}")
    public List<Cart> getCartByUserId(@PathVariable Long userId) {
        return cartService.getCartByUserId(userId);
    }

    // ✅ Delete a specific item
    @DeleteMapping("/{userId}/item/{itemId}")
    public String deleteCartItem(@PathVariable Long userId, @PathVariable Long itemId) {
        cartService.deleteCartItem(userId, itemId);
        return "Item removed successfully!";
    }

    // ✅ Clear entire cart for a user
    @DeleteMapping("/clear/{userId}")
    public String clearCart(@PathVariable Long userId) {
        cartService.clearCartByUserId(userId);
        return "Cart cleared successfully!";
    }
}
