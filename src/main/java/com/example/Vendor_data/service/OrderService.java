package com.example.Vendor_data.service;

import com.example.Vendor_data.model.Menu;
import com.example.Vendor_data.model.Order;
import com.example.Vendor_data.model.Vendor;
import com.example.Vendor_data.repository.MenuRepository;
import com.example.Vendor_data.repository.OrderRepository;
import com.example.Vendor_data.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private MenuRepository menuRepository;

    // Get all orders for a vendor
    public List<Order> getOrdersByVendor(Long vendorId) {
        return orderRepository.findByVendorId(vendorId);
    }

    // Get specific order
    public Order getOrderById(Long vendorId, Long orderId) {
        return orderRepository.findById(orderId)
                .filter(order -> order.getVendor().getId().equals(vendorId))
                .orElseThrow(() -> new RuntimeException("Order not found for vendor"));
    }

    // Create order
    public Order createOrder(Long vendorId, Order order) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        Menu menu = menuRepository.findById(order.getMenu().getId())
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        // Calculate total price
        double totalPrice = order.getQuantity() * menu.getPrice();
        order.setTotalPrice(totalPrice);
        order.setVendor(vendor);
        order.setMenu(menu);

        return orderRepository.save(order);
    }

    // Update order
    public Order updateOrder(Long vendorId, Long orderId, Order updatedOrder) {
        Order order = getOrderById(vendorId, orderId);

        if (updatedOrder.getQuantity() != null) {
            order.setQuantity(updatedOrder.getQuantity());
            double newTotal = order.getQuantity() * order.getMenu().getPrice();
            order.setTotalPrice(newTotal);
        }

        return orderRepository.save(order);
    }

    // Delete order
    public void deleteOrder(Long vendorId, Long orderId) {
        Order order = getOrderById(vendorId, orderId);
        orderRepository.delete(order);
    }
}
