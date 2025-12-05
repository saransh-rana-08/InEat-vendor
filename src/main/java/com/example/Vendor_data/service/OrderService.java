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
    // CREATE ORDER (MULTI-ITEM SUPPORT)
    // =====================================================
    public List<OrderDTO> createOrder(OrderDTO dto) {

        Long generatedOrderId = System.currentTimeMillis();

        List<OrderDTO> items = dto.getItems() != null ? dto.getItems() : List.of(dto);

        for (OrderDTO item : items) {

            Menu menu = menuRepository.findById(item.getMenuId())
                    .orElseThrow(() -> new RuntimeException("Menu not found"));

            double total = menu.getPrice() * item.getQuantity();

            Order order = new Order();
            order.setOrderId(generatedOrderId);

            order.setCustomerId(dto.getCustomerId());
            order.setCustomerName(dto.getCustomerName());

            order.setVendorId(dto.getVendorId());
            order.setVendorName(dto.getVendorName());

            order.setMenuId(item.getMenuId());
            order.setMenuName(item.getMenuName());

            order.setQuantity(item.getQuantity());
            order.setTotalPrice(total);
            order.setStatus("Pending");

            orderRepository.save(order);
        }

        return getOrdersByOrderId(generatedOrderId);
    }

    // =====================================================
    // GET ORDERS BY ORDER ID — GROUPED VIEW
    // =====================================================
    public List<OrderDTO> getOrdersByOrderId(Long orderId) {

        List<Order> orders = orderRepository.findByOrderId(orderId);
        if (orders.isEmpty()) return List.of();

        Order first = orders.get(0);

// Parent DTO (NO menuId, menuName, quantity in parent)
        OrderDTO parent = new OrderDTO();
        parent.setId(first.getId());          // ok
        parent.setOrderId(orderId);           // shared order ID

        parent.setCustomerId(first.getCustomerId());
        parent.setCustomerName(first.getCustomerName());

        parent.setVendorId(first.getVendorId());
        parent.setVendorName(first.getVendorName());

        parent.setStatus(first.getStatus());
        parent.setCreatedAt(first.getCreatedAt().toString());

// Total order price (sum of all item rows)
        double totalSum = orders.stream()
                .mapToDouble(Order::getTotalPrice)
                .sum();

        parent.setTotalPrice(totalSum);     // correct total amount


        // Child item list
        List<OrderDTO> itemDtos = new ArrayList<>();
        for (Order o : orders) {
            OrderDTO item = new OrderDTO();

            item.setId(o.getId());
            item.setOrderId(o.getOrderId());

            // FIX: these fields were missing for child
            item.setCustomerId(o.getCustomerId());
            item.setCustomerName(o.getCustomerName());
            item.setVendorId(o.getVendorId());
            item.setVendorName(o.getVendorName());

            item.setMenuId(o.getMenuId());
            item.setMenuName(o.getMenuName());
            item.setQuantity(o.getQuantity());
            item.setTotalPrice(o.getTotalPrice());
            item.setStatus(o.getStatus());
            item.setCreatedAt(o.getCreatedAt().toString());

            itemDtos.add(item);
        }


        parent.setItems(itemDtos);

        return List.of(parent);
    }

    // =====================================================
    // GET SINGLE ORDER (FLAT)
    // =====================================================
    public OrderDTO getOrder(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderId(order.getOrderId());
        dto.setCustomerId(order.getCustomerId());
        dto.setCustomerName(order.getCustomerName());
        dto.setMenuId(order.getMenuId());
        dto.setMenuName(order.getMenuName());
        dto.setVendorId(order.getVendorId());
        dto.setVendorName(order.getVendorName());
        dto.setQuantity(order.getQuantity());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setStatus(order.getStatus());
        dto.setCreatedAt(order.getCreatedAt().toString());

        return dto;
    }

    // =====================================================
    // GET ALL ORDERS BY VENDOR ID (FLAT)
    // =====================================================
    public List<OrderDTO> getOrdersByVendorId(Long vendorId) {

        List<Order> orders = orderRepository.findByVendorId(vendorId);
        List<OrderDTO> list = new ArrayList<>();

        for (Order order : orders) {
            OrderDTO dto = new OrderDTO();

            dto.setId(order.getId());
            dto.setOrderId(order.getOrderId());
            dto.setCustomerId(order.getCustomerId());
            dto.setCustomerName(order.getCustomerName());
            dto.setMenuId(order.getMenuId());
            dto.setMenuName(order.getMenuName());
            dto.setVendorId(order.getVendorId());
            dto.setVendorName(order.getVendorName());
            dto.setQuantity(order.getQuantity());
            dto.setTotalPrice(order.getTotalPrice());
            dto.setStatus(order.getStatus());
            dto.setCreatedAt(order.getCreatedAt().toString());

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
        response.setOrderId(saved.getOrderId());
        response.setCustomerId(saved.getCustomerId());
        response.setCustomerName(saved.getCustomerName());
        response.setMenuId(saved.getMenuId());
        response.setMenuName(saved.getMenuName());
        response.setVendorId(saved.getVendorId());
        response.setVendorName(saved.getVendorName());
        response.setQuantity(saved.getQuantity());
        response.setTotalPrice(saved.getTotalPrice());
        response.setStatus(saved.getStatus());
        response.setCreatedAt(saved.getCreatedAt().toString());

        return response;
    }

    // =====================================================
    // PATCH ORDER
    // =====================================================
    public OrderDTO patchOrder(Long id, OrderDTO dto) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (dto.getCustomerId() != null) order.setCustomerId(dto.getCustomerId());
        if (dto.getCustomerName() != null) order.setCustomerName(dto.getCustomerName());
        if (dto.getVendorId() != null) order.setVendorId(dto.getVendorId());
        if (dto.getVendorName() != null) order.setVendorName(dto.getVendorName());
        if (dto.getStatus() != null) order.setStatus(dto.getStatus());

        if (dto.getMenuId() != null) {
            Menu menu = menuRepository.findById(dto.getMenuId())
                    .orElseThrow(() -> new RuntimeException("Menu not found"));
            order.setMenuId(dto.getMenuId());
            order.setMenuName(dto.getMenuName());
            if (order.getQuantity() != null)
                order.setTotalPrice(menu.getPrice() * order.getQuantity());
        }

        if (dto.getQuantity() != null) {
            order.setQuantity(dto.getQuantity());
            Menu menu = menuRepository.findById(order.getMenuId())
                    .orElseThrow(() -> new RuntimeException("Menu not found"));
            order.setTotalPrice(menu.getPrice() * dto.getQuantity());
        }

        Order saved = orderRepository.save(order);

        OrderDTO response = new OrderDTO();
        response.setId(saved.getId());
        response.setOrderId(saved.getOrderId());
        response.setCustomerId(saved.getCustomerId());
        response.setCustomerName(saved.getCustomerName());
        response.setMenuId(saved.getMenuId());
        response.setMenuName(saved.getMenuName());
        response.setVendorId(saved.getVendorId());
        response.setVendorName(saved.getVendorName());
        response.setQuantity(saved.getQuantity());
        response.setTotalPrice(saved.getTotalPrice());
        response.setStatus(saved.getStatus());
        response.setCreatedAt(saved.getCreatedAt().toString());

        return response;
    }

    // =====================================================
    // DELETE
    // =====================================================
    public String deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) return "Order not found";
        orderRepository.deleteById(id);
        return "Order deleted successfully";
    }

    // =====================================================
    // GET ORDERS BY CUSTOMER ID
    // =====================================================
    public List<OrderDTO> getOrdersByCustomerId(Long customerId) {

        List<Order> orders = orderRepository.findByCustomerId(customerId);
        List<OrderDTO> list = new ArrayList<>();

        for (Order order : orders) {

            OrderDTO dto = new OrderDTO();
            dto.setId(order.getId());
            dto.setOrderId(order.getOrderId());
            dto.setCustomerId(order.getCustomerId());
            dto.setCustomerName(order.getCustomerName());
            dto.setMenuId(order.getMenuId());
            dto.setMenuName(order.getMenuName());
            dto.setVendorId(order.getVendorId());
            dto.setVendorName(order.getVendorName());
            dto.setQuantity(order.getQuantity());
            dto.setTotalPrice(order.getTotalPrice());
            dto.setStatus(order.getStatus());
            dto.setCreatedAt(order.getCreatedAt().toString());

            list.add(dto);
        }

        return list;
    }

    public List<OrderDTO> patchOrderByCustomerId(Long customerId, OrderDTO dto) {

        List<Order> orders = orderRepository.findByCustomerId(customerId);
        if (orders.isEmpty()) {
            throw new RuntimeException("No orders found for customerId = " + customerId);
        }

        List<OrderDTO> result = new ArrayList<>();

        for (Order order : orders) {

            // Skip already completed orders
            if ("Completed".equalsIgnoreCase(order.getStatus())) continue;

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

            OrderDTO response = new OrderDTO();
            response.setId(saved.getId());
            response.setOrderId(saved.getOrderId());
            response.setCustomerId(saved.getCustomerId());
            response.setCustomerName(saved.getCustomerName());
            response.setMenuId(saved.getMenuId());
            response.setMenuName(saved.getMenuName());
            response.setVendorId(saved.getVendorId());
            response.setVendorName(saved.getVendorName());
            response.setQuantity(saved.getQuantity());
            response.setTotalPrice(saved.getTotalPrice());
            response.setStatus(saved.getStatus());
            response.setCreatedAt(saved.getCreatedAt().toString()); // NEW

            result.add(response);
        }

        return result;
    }

}
