package com.example.Vendor_data.controller;

import com.example.Vendor_data.model.Cart;
import com.example.Vendor_data.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public List<Cart> getCart(@PathVariable Long userId) {
        return cartService.getCartByUser(userId);
    }

    @PostMapping("/add")
    public Cart addToCart(@RequestBody Cart cart) {
        return cartService.addToCart(
                cart.getUserId(),
                cart.getVendorId(),
                cart.getVendorName(),
                cart.getItemId(),
                cart.getName(),
                cart.getQuantity()
        );
    }


    @DeleteMapping("/{userId}")
    public void clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
    }
}
