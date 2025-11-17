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

    // POST — create order
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
        dto.setStatus("Pending");

        return dto;
    }

    // GET single order
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

    // GET all orders by customerId
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

    // PUT — update order
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

    // DELETE
    public String deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            return "Order not found";
        }
        orderRepository.deleteById(id);
        return "Order deleted successfully";
    }
}
