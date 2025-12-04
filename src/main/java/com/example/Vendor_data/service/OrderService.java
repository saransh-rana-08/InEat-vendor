package com.example.Vendor_data.service;

import com.example.Vendor_data.dto.OrderDTO;
import com.example.Vendor_data.model.Menu;
import com.example.Vendor_data.model.Order;
import com.example.Vendor_data.repository.MenuRepository;
import com.example.Vendor_data.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MenuRepository menuRepository;

    // =====================================================
    // CREATE ORDER
    // =====================================================
    public OrderDTO createOrder(OrderDTO dto) {

        Menu menu = menuRepository.findById(dto.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        double total = menu.getPrice() * dto.getQuantity();

        Order order = new Order();
        order.setCustomerId(dto.getCustomerId());
        order.setCustomerName(dto.getCustomerName());
        order.setMenuId(dto.getMenuId());
        order.setMenuName(dto.getMenuName());
        order.setVendorId(dto.getVendorId());
        order.setVendorName(dto.getVendorName());
        order.setQuantity(dto.getQuantity());
        order.setTotalPrice(total);
        order.setStatus("Pending");

        Order saved = orderRepository.save(order);

        dto.setId(saved.getId());
        dto.setTotalPrice(total);
        dto.setStatus(saved.getStatus());

        return dto;
    }

    // =====================================================
    // GET SINGLE ORDER
    // =====================================================
    public OrderDTO getOrder(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setCustomerId(order.getCustomerId());
        dto.setCustomerName(order.getCustomerName());
        dto.setMenuId(order.getMenuId());
        dto.setMenuName(order.getMenuName());
        dto.setVendorId(order.getVendorId());
        dto.setVendorName(order.getVendorName());
        dto.setQuantity(order.getQuantity());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setStatus(order.getStatus());

        return dto;
    }

    // =====================================================
    // GET ALL ORDERS BY VENDOR ID (NEW)
    // =====================================================
    public List<OrderDTO> getOrdersByVendorId(Long vendorId) {

        List<Order> orders = orderRepository.findByVendorId(vendorId);
        List<OrderDTO> list = new ArrayList<>();

        for (Order order : orders) {
            OrderDTO dto = new OrderDTO();

            dto.setId(order.getId());
            dto.setCustomerId(order.getCustomerId());
            dto.setCustomerName(order.getCustomerName());
            dto.setMenuId(order.getMenuId());
            dto.setMenuName(order.getMenuName());
            dto.setVendorId(order.getVendorId());
            dto.setVendorName(order.getVendorName());
            dto.setQuantity(order.getQuantity());
            dto.setTotalPrice(order.getTotalPrice());
            dto.setStatus(order.getStatus());

            list.add(dto);
        }

        return list;
    }

    // =====================================================
    // UPDATE ORDER
    // =====================================================
    public OrderDTO updateOrder(Long id, OrderDTO dto) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Menu menu = menuRepository.findById(dto.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        double total = menu.getPrice() * dto.getQuantity();

        order.setCustomerId(dto.getCustomerId());
        order.setCustomerName(dto.getCustomerName());
        order.setMenuId(dto.getMenuId());
        order.setMenuName(dto.getMenuName());
        order.setVendorId(dto.getVendorId());
        order.setVendorName(dto.getVendorName());
        order.setQuantity(dto.getQuantity());
        order.setTotalPrice(total);
        order.setStatus(dto.getStatus());

        Order saved = orderRepository.save(order);

        OrderDTO response = new OrderDTO();
        response.setId(saved.getId());
        response.setCustomerId(saved.getCustomerId());
        response.setCustomerName(saved.getCustomerName());
        response.setMenuId(saved.getMenuId());
        response.setMenuName(saved.getMenuName());
        response.setVendorId(saved.getVendorId());
        response.setVendorName(saved.getVendorName());
        response.setQuantity(saved.getQuantity());
        response.setTotalPrice(saved.getTotalPrice());
        response.setStatus(saved.getStatus());

        return response;
    }



    // =====================================================
// PARTIAL UPDATE (PATCH)
// =====================================================
    public OrderDTO patchOrder(Long id, OrderDTO dto) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Update ONLY non-null fields

        if (dto.getCustomerId() != null)
            order.setCustomerId(dto.getCustomerId());

        if (dto.getCustomerName() != null)
            order.setCustomerName(dto.getCustomerName());

        if (dto.getMenuId() != null) {

            Menu menu = menuRepository.findById(dto.getMenuId())
                    .orElseThrow(() -> new RuntimeException("Menu not found"));

            order.setMenuId(dto.getMenuId());
            order.setMenuName(dto.getMenuName());

            // Recalculate price only if quantity exists
            if (order.getQuantity() != null)
                order.setTotalPrice(menu.getPrice() * order.getQuantity());
        }

        if (dto.getMenuName() != null)
            order.setMenuName(dto.getMenuName());

        if (dto.getVendorId() != null)
            order.setVendorId(dto.getVendorId());

        if (dto.getVendorName() != null)
            order.setVendorName(dto.getVendorName());

        if (dto.getQuantity() != null) {
            order.setQuantity(dto.getQuantity());

            // Recalculate price using existing menu
            Menu menu = menuRepository.findById(order.getMenuId())
                    .orElseThrow(() -> new RuntimeException("Menu not found"));

            order.setTotalPrice(menu.getPrice() * dto.getQuantity());
        }

        if (dto.getTotalPrice() != null)
            order.setTotalPrice(dto.getTotalPrice());

        if (dto.getStatus() != null)
            order.setStatus(dto.getStatus());

        Order saved = orderRepository.save(order);

        // Build response DTO (same style as your other methods)
        OrderDTO response = new OrderDTO();
        response.setId(saved.getId());
        response.setCustomerId(saved.getCustomerId());
        response.setCustomerName(saved.getCustomerName());
        response.setMenuId(saved.getMenuId());
        response.setMenuName(saved.getMenuName());
        response.setVendorId(saved.getVendorId());
        response.setVendorName(saved.getVendorName());
        response.setQuantity(saved.getQuantity());
        response.setTotalPrice(saved.getTotalPrice());
        response.setStatus(saved.getStatus());

        return response;
    }


    // =====================================================
    // DELETE ORDER
    // =====================================================
    public String deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            return "Order not found";
        }
        orderRepository.deleteById(id);
        return "Order deleted successfully";
    }

    // =====================================================
// PARTIAL UPDATE (PATCH) BY CUSTOMER ID
// =====================================================
    public List<OrderDTO> patchOrderByCustomerId(Long customerId, OrderDTO dto) {

        List<Order> orders = orderRepository.findByCustomerId(customerId);
        if (orders.isEmpty()) {
            throw new RuntimeException("No orders found for customerId = " + customerId);
        }

        List<OrderDTO> result = new ArrayList<>();

        for (Order order : orders) {

            // ⭐ STEP 3 — skip already completed orders
            if ("Completed".equalsIgnoreCase(order.getStatus())) {
                continue;
            }

            // Update non-null fields
            if (dto.getStatus() != null)
                order.setStatus(dto.getStatus());

            if (dto.getQuantity() != null) {
                order.setQuantity(dto.getQuantity());

                Menu menu = menuRepository.findById(order.getMenuId())
                        .orElseThrow(() -> new RuntimeException("Menu not found"));

                order.setTotalPrice(menu.getPrice() * dto.getQuantity());
            }

            if (dto.getVendorId() != null)
                order.setVendorId(dto.getVendorId());

            if (dto.getVendorName() != null)
                order.setVendorName(dto.getVendorName());

            if (dto.getMenuId() != null)
                order.setMenuId(dto.getMenuId());

            Order saved = orderRepository.save(order);

            // Build DTO
            OrderDTO response = new OrderDTO();
            response.setId(saved.getId());
            response.setCustomerId(saved.getCustomerId());
            response.setCustomerName(saved.getCustomerName());
            response.setMenuId(saved.getMenuId());
            response.setMenuName(saved.getMenuName());
            response.setVendorId(saved.getVendorId());
            response.setVendorName(saved.getVendorName());
            response.setQuantity(saved.getQuantity());
            response.setTotalPrice(saved.getTotalPrice());
            response.setStatus(saved.getStatus());

            result.add(response);
        }

        return result;
    }

    // =====================================================
// GET ALL ORDERS BY CUSTOMER ID (NEW)
// =====================================================
    public List<OrderDTO> getOrdersByCustomerId(Long customerId) {

        List<Order> orders = orderRepository.findByCustomerId(customerId);
        List<OrderDTO> list = new ArrayList<>();

        for (Order order : orders) {

            OrderDTO dto = new OrderDTO();

            dto.setId(order.getId());
            dto.setCustomerId(order.getCustomerId());
            dto.setCustomerName(order.getCustomerName());
            dto.setMenuId(order.getMenuId());
            dto.setMenuName(order.getMenuName());
            dto.setVendorId(order.getVendorId());
            dto.setVendorName(order.getVendorName());
            dto.setQuantity(order.getQuantity());
            dto.setTotalPrice(order.getTotalPrice());
            dto.setStatus(order.getStatus());

            list.add(dto);
        }

        return list;
    }




}
