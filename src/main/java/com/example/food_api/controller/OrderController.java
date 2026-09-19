package com.example.food_api.controller;

import com.example.food_api.model.Order;
import com.example.food_api.repository.OrderRepository;
import com.example.food_api.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    public OrderController(
            OrderService orderService,
            OrderRepository orderRepository) {

        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    @PostMapping("/{cartId}")
    public Order placeOrder(@PathVariable Long cartId) {
        return orderService.placeOrder(cartId);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderRepository.findByNumericId(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @PostMapping("/{orderId}/reorder")
    public Order reorder(@PathVariable Long orderId) {
        return orderService.reorder(orderId);
    }
}