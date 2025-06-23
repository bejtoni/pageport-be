package com.pageport.pageport.controller;

import com.pageport.pageport.dto.OrderDto;
import com.pageport.pageport.dto.OrderStatusUpdateRequest;
import com.pageport.pageport.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{userId}")
    public OrderDto createOrder(@PathVariable UUID userId) {
        return orderService.createOrder(userId);
    }

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable UUID id) {
        return orderService.getOrder(id);
    }

    @GetMapping("/user/{userId}")
    public List<OrderDto> getOrders(@PathVariable UUID userId) {
        return orderService.getAllOrders(userId);
    }

    @PatchMapping("/{id}/status")
    public OrderDto updateOrderStatus(@PathVariable UUID id, @RequestBody OrderStatusUpdateRequest request) {
        return orderService.updateOrderStatus(id, request.getStatus());
    }
}
