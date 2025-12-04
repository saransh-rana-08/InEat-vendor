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
    public ResponseEntity<List<OrderDTO>> createOrder(@RequestBody OrderDTO dto) {
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

    // GET ALL ORDERS BY CUSTOMER ID
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(orderService.getOrdersByCustomerId(customerId));
    }


    // UPDATE ORDER
    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(
            @PathVariable Long id,
            @RequestBody OrderDTO dto
    ) {
        return ResponseEntity.ok(orderService.updateOrder(id, dto));
    }

    // ⭐ PARTIAL UPDATE ORDER (PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<OrderDTO> patchOrder(
            @PathVariable Long id,
            @RequestBody OrderDTO dto
    ) {
        return ResponseEntity.ok(orderService.patchOrder(id, dto));
    }

    // DELETE ORDER
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.deleteOrder(id));
    }

    // GET ALL ITEMS BY ORDER ID
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrdersByOrderId(orderId));
    }


    @PatchMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDTO>> patchOrderByCustomer(
            @PathVariable Long customerId,
            @RequestBody OrderDTO dto
    ) {
        return ResponseEntity.ok(orderService.patchOrderByCustomerId(customerId, dto));
    }


}
