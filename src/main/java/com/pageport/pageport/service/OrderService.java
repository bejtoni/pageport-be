package com.pageport.pageport.service;

import com.pageport.pageport.dto.OrderDto;
import com.pageport.pageport.entity.CartEntity;
import com.pageport.pageport.entity.OrderEntity;
import com.pageport.pageport.entity.UserEntity;
import com.pageport.pageport.repository.CartRepository;
import com.pageport.pageport.repository.OrderRepository;
import com.pageport.pageport.repository.UserRepository;
import com.pageport.pageport.type.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public OrderDto createOrder(UUID userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow();
        List<CartEntity> cartItems = cartRepository.findByUser_Uid(userId);

        double totalPrice = cartItems.stream()
                .mapToDouble(item -> item.getBook().getPrice() * item.getQuantity())
                .sum();

        OrderEntity order = new OrderEntity();
        order.setUser(user);
        order.setTotalPrice(totalPrice);
        order.setStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDateTime.now());
        orderRepository.save(order);

        LocalDateTime orderDate = order.getOrderDate();
        LocalDateTime deliveryDate = orderDate.plusDays(3); // Assuming 3 days delivery
        int daysLeft = (int) ChronoUnit.DAYS.between(LocalDateTime.now(), deliveryDate);

        return new OrderDto(
            order.getId(),
            userId,
            totalPrice,
            order.getStatus().toString(),
            orderDate.toString(),
            daysLeft,
            deliveryDate.toString()
        );
    }

    public List<OrderDto> getAllOrders(UUID userId) {
        return orderRepository.findByUser_Uid(userId).stream()
            .map(order -> {
                LocalDateTime orderDate = order.getOrderDate();
                LocalDateTime deliveryDate = orderDate.plusDays(3);
                int daysLeft = (int) ChronoUnit.DAYS.between(LocalDateTime.now(), deliveryDate);

                return new OrderDto(
                    order.getId(),
                    order.getUser().getUid(),
                    order.getTotalPrice(),
                    order.getStatus().toString(),
                    orderDate.toString(),
                    daysLeft,
                    deliveryDate.toString()
                );
            })
            .collect(Collectors.toList());
    }

    public OrderDto getOrder(UUID orderId) {
        OrderEntity order = orderRepository.findById(orderId).orElseThrow();
        LocalDateTime orderDate = order.getOrderDate();
        LocalDateTime deliveryDate = orderDate.plusDays(3);
        int daysLeft = (int) ChronoUnit.DAYS.between(LocalDateTime.now(), deliveryDate);

        return new OrderDto(
            order.getId(),
            order.getUser().getUid(),
            order.getTotalPrice(),
            order.getStatus().toString(),
            orderDate.toString(),
            daysLeft,
            deliveryDate.toString()
        );
    }

    public OrderDto updateOrderStatus(UUID orderId, OrderStatus status) {
        OrderEntity order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus(status);
        orderRepository.save(order);

        LocalDateTime orderDate = order.getOrderDate();
        LocalDateTime deliveryDate = orderDate.plusDays(3);
        int daysLeft = (int) ChronoUnit.DAYS.between(LocalDateTime.now(), deliveryDate);

        return new OrderDto(
            order.getId(),
            order.getUser().getUid(),
            order.getTotalPrice(),
            order.getStatus().toString(),
            orderDate.toString(),
            daysLeft,
            deliveryDate.toString()
        );
    }
}
