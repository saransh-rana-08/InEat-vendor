package com.example.Vendor_data.controller;

import com.example.Vendor_data.model.Order;
import com.example.Vendor_data.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendors/{vendorId}/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Get all orders for vendor
    @GetMapping
    public List<Order> getOrdersByVendor(@PathVariable Long vendorId) {
        return orderService.getOrdersByVendor(vendorId);
    }

    // Get single order
    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Long vendorId, @PathVariable Long orderId) {
        return orderService.getOrderById(vendorId, orderId);
    }

    // Create new order
    @PostMapping
    public Order createOrder(@PathVariable Long vendorId, @RequestBody Order order) {
        return orderService.createOrder(vendorId, order);
    }

    // Update order
    @PatchMapping("/{orderId}")
    public Order updateOrder(@PathVariable Long vendorId, @PathVariable Long orderId,
                             @RequestBody Order updatedOrder) {
        return orderService.updateOrder(vendorId, orderId, updatedOrder);
    }

    // Delete order
    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable Long vendorId, @PathVariable Long orderId) {
        orderService.deleteOrder(vendorId, orderId);
    }
}
