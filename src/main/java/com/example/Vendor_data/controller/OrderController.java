package com.example.Vendor_data.controller;

import com.example.Vendor_data.dto.OrderDTO;
import com.example.Vendor_data.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // CREATE ORDER
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO dto) {
        return ResponseEntity.ok(orderService.createOrder(dto));
    }

    // GET ORDER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    // ✅ NEW : GET ALL ORDERS BY VENDOR ID
    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByVendor(@PathVariable Long vendorId) {
        return ResponseEntity.ok(orderService.getOrdersByVendorId(vendorId));
    }

    // UPDATE ORDER
    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(
            @PathVariable Long id,
            @RequestBody OrderDTO dto
    ) {
        return ResponseEntity.ok(orderService.updateOrder(id, dto));
    }

    // DELETE ORDER
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.deleteOrder(id));
    }
}
